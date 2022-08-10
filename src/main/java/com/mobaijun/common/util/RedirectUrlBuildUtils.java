/*
 * Copyright (C) 2022 www.mobaijun.com
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
package com.mobaijun.common.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;

import java.util.Map;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: RedirectUrlBuildUtil
 * class description：构建redirect url的工具类
 *
 * @author MoBaiJun 2022/5/12 11:38
 */
public class RedirectUrlBuildUtils {

    /**
     * 构建redirect url
     *
     * @param originUrl 原始的url
     * @param paramsMap url上要拼接的参数信息
     * @return String
     */
    public static String createRedirectUrl(String originUrl, Map<String, ?> paramsMap) {
        if (StrUtil.isBlank(originUrl)) {
            return null;
        }
        if (ObjectUtil.isEmpty(paramsMap)) {
            return originUrl;
        } else {
            return originUrl + "?" + URLUtil.buildQuery(paramsMap, CharsetUtil.CHARSET_UTF_8);
        }
    }
}