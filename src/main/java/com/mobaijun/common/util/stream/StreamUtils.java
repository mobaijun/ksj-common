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
package com.mobaijun.common.util.stream;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.stream.StreamUtil;
import com.mobaijun.common.util.StringUtils;
import com.mobaijun.common.util.collection.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Software：IntelliJ IDEA 2021.3.2
 * ClassName: StreamUtils
 * 类描述： Stream工具类
 *
 * @author MoBaiJun 2022/4/24 11:27
 */
public class StreamUtils extends StreamUtil {

    /**
     * 映射
     *
     * @param data 不能为空
     * @param fun  fun
     * @param <T>  未定义
     * @param <R>  未定义
     * @return data为空抛出异常IllegalArgumentException
     */
    public static <T, R> List<R> map(List<T> data, Function<T, R> fun) {
        if (CollectionUtils.isNull(data)) {
            return null;
        }
        return data.stream().map(fun).collect(Collectors.toList());
    }

    /**
     * 过滤
     *
     * @param data data
     * @param pre  pre
     * @param <T>  List
     * @return data为空返回data
     */
    public static <T> List<T> filter(List<T> data, Predicate<T> pre) {
        if (CollectionUtils.isNull(data)) {
            return null;
        }
        return data.stream().filter(pre).collect(Collectors.toList());
    }

    /**
     * 排序
     *
     * @param data       data
     * @param comparator comparator
     * @param <T>        List
     * @return data为空返回data
     */
    public static <T> List<T> sorted(List<T> data, Comparator<T> comparator) {
        if (CollectionUtils.isNull(data)) {
            return null;
        }
        return data.stream().sorted(comparator).collect(Collectors.toList());
    }

    /**
     * 去重
     *
     * @param data data
     * @param <T>  List
     * @return data为空返回data
     */
    public static <T> List<T> distinct(List<T> data) {
        if (CollectionUtils.isNull(data)) {
            return null;
        }
        return data.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 根据主键去重
     *
     * @param keyExtractor key
     * @param <T>          类型
     * @return 去重后的值
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>(10);
        // putIfAbsent方法添加键值对，如果map集合中没有该key对应的值，则直接添加，并返回null，如果已经存在对应的值，则依旧为原来的值。
        // 如果返回null表示添加数据成功(不重复)，不重复(null==null :TRUE)
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 判断是否包含匹配元素
     *
     * @param data data
     * @param pre  pre
     * @param <T>  未定义
     * @return boolean
     */
    public static <T> boolean anyMatch(List<T> data, Predicate<T> pre) {
        if (CollectionUtils.isNull(data)) {
            return false;
        }
        return data.parallelStream().anyMatch(pre);
    }

    /**
     * 将list进行join操作
     *
     * @param data data
     * @param join join
     * @return 返回join之后的字符串, data为空返回null
     */
    public static <T> String join(List<String> data, String join) {
        if (CollectionUtils.isNull(data)) {
            return null;
        }
        return data.stream().collect(Collectors.joining(join == null ? "" : join));
    }

    /**
     * 生成 list 集合
     *
     * @param data 数据对象
     * @param <T>  未定义类型
     * @return List 集合
     */
    public static <T> List<T> singletonList(T data) {
        return Collections.singletonList(data);
    }

    /**
     * 生成list
     *
     * @param elements 元素
     * @param <T>      泛型
     * @return List
     */

    @SafeVarargs
    public static <T> List<T> toList(T... elements) {
        List<T> list = new ArrayList<>();
        if (Objects.nonNull(elements)) {
            Arrays.stream(elements).forEach(item -> {
                if (Objects.nonNull(item)) {
                    list.add(item);
                }
            });
        }
        return list;
    }

    /**
     * 添加到 list 集合
     *
     * @param collection 集合
     * @param add        待添加的集合
     * @param <T>        未定义类型
     */
    public static <T> void addAll(Collection<T> collection, Collection<T> add) {
        if (StringUtils.isNotEmpty(add)) {
            collection.addAll(add);
        }
    }

    /**
     * 两个Map进行合并
     *
     * @param map 原始 map
     * @param add 要合并的 map
     * @param <K> key 泛型
     * @param <V> 值泛型
     */
    public static <K, V> void addAll(Map<K, V> map, Map<K, V> add) {
        if (!CollectionUtils.isEmpty(add) && !CollectionUtils.isEmpty(map)) {
            map.putAll(add);
        }
    }

    /**
     * set 转 list
     *
     * @param set set 集合
     * @param <T> 未定义类型
     * @return list 集合
     */
    public static <T> List<T> setToList(Set<T> set) {
        if (!CollectionUtils.isEmpty(set)) {
            return new ArrayList<>(set);
        } else {
            return CollUtil.newArrayList();
        }
    }

    /**
     * 返回空集合
     *
     * @param <T> 泛型
     * @return List
     */
    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    /**
     * 返回空 map
     *
     * @param <K> 键
     * @param <V> 值
     * @return 空 map
     */
    public static <K, V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }

    /**
     * 返回空集合
     *
     * @param <T> 泛型
     * @return set
     */
    public static <T> Set<T> emptySet() {
        return Collections.emptySet();
    }
}