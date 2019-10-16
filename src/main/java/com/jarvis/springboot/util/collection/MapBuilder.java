package com.jarvis.springboot.util.collection;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 支持链式编程的 map builder
 *
 * @param <K>
 * @param <V>
 */
public class MapBuilder<K, V> {

    private Map<K, V> innerMap;

    private MapBuilder() {
    }

    public static <K, V> MapBuilder<K, V> buildHashMap(Class<K> kClass, Class<V> vClass) {
        return new MapBuilder<>();
    }

    public MapBuilder<K, V> put(K key, V value) {
        if (innerMap == null) innerMap = Maps.newHashMap();

        innerMap.put(key, value);

        return this;
    }

    public Map<K, V> get() {
        return innerMap;
    }
}
