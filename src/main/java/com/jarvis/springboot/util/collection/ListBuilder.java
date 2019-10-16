package com.jarvis.springboot.util.collection;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 支持链式编程的 list builder
 *
 * @param <E>
 */
public class ListBuilder<E> {

    private List<E> innerList;

    private ListBuilder() {
    }

    public static <T> ListBuilder<T> buildList(Class<T> tClass) {
        return new ListBuilder<>();
    }

    public ListBuilder<E> add(E element) {
        if (innerList == null) innerList = Lists.newArrayList();

        innerList.add(element);

        return this;
    }

    public List<E> get() {
        return innerList;
    }
}
