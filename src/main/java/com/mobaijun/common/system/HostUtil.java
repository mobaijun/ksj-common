/*
 * Copyright (C) 2022 [www.mobaijun.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mobaijun.common.system;

import com.mobaijun.common.collection.CollectionUtil;
import com.mobaijun.common.constant.JdkConstant;
import com.mobaijun.common.file.FileUtil;
import com.mobaijun.common.text.Charsets;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * software：IntelliJ IDEA 2022.2.3<br/>
 * class name: HostUtil<br/>
 * class description: host 工具类<br/>
 * > Linux 下和 Mac Os 待完善
 * 如果遇到 host 文件没有权限写入，请先右键host文件 --> 安全 --> 当前用户 --> 编辑 --> 当前用户 --> 勾选完全控制，如下图<br/>
 * <img src="https://tencent.cos.mobaijun.com//xxbj_img/image-20221210004329990.png"/>
 *
 * @author MoBaiJun 2022/12/9 23:15
 */
@Slf4j
public class HostUtil {

    /**
     * Windows host file path
     */
    private static final Path WIN_HOSTS_PATH = Paths.get("C:\\Windows\\System32\\drivers\\etc\\hosts");

    /**
     * linux host file path
     */
    private static final Path LINUX_HOSTS_PATH = Paths.get("/etc/hosts");

    /**
     * Windows
     */
    private static final String WINDOWS = "Windows";

    /**
     * Windows flush dns
     */
    private static final String FLUSH_DNS = "ipconfig /flushdns";

    /**
     * Line break character
     */
    private static final String END = String.format("%n");

    /**
     * 获取操作系统类型返回 host 文件地址
     *
     * @return host 文件地址
     */
    public static String getOsName() {
        String osName = java.lang.System.getProperty(JdkConstant.OS_NAME);
        if (WINDOWS.regionMatches(1, osName, 1, 1)) {
            return WIN_HOSTS_PATH.toString();
        }
        return LINUX_HOSTS_PATH.toString();
    }

    /**
     * 读取 host 文件内容
     *
     * @return list 集合
     */
    public static List<String> readHosts() {
        return FileUtil.readLines(new File(getOsName()), Charsets.UTF_8)
                .stream()
                .filter(it -> !it.trim().matches("(^#.*)|(\\s*)"))
                .map(it -> it.replaceAll("#.*", "").trim()
                        .replaceAll("\\s+", "\t"))
                .collect(Collectors.toList());
    }

    /**
     * 追加写入 host
     *
     * @param ip  IP 地址
     * @param url 映射地址
     * @return 是否成功
     */
    public static boolean append(String ip, String url) {
        // 存在返回 false
        if (exists(ip, url)) {
            return false;
        }
        List<String> sets = CollectionUtil.newArrayList();
        sets.add(String.format("%s\t%s", ip, url));
        return FileUtil.appendToFile(sets, getOsName(), true) && flushDns();
    }

    /**
     * 当前 IP 地址是否存在 host 文件中
     *
     * @param ip  IP 地址
     * @param url 映射地址
     * @return 是否存在
     */
    public static boolean exists(String ip, String url) {
        return readHosts().contains(String.format("%s\t%s", ip, url));
    }

    /**
     * 刷新 dns
     *
     * @return 是否成功
     */
    public static boolean flushDns() {
        try {
            Runtime.getRuntime().exec(FLUSH_DNS);
            return true;
        } catch (IOException e) {
            log.error("An error occurred while refreshing DNS:{}", e.getMessage());
            return false;
        }
    }
}