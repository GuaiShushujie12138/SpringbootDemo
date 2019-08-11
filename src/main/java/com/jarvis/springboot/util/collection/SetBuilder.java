package com.jarvis.springboot.util.collection;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 支持链式编程的 set builder
 *
 * @param <E>
 */
public class SetBuilder<E> {

    private Set<E> innerSet;

    private SetBuilder() {
    }

    public static <E> SetBuilder<E> buildSet(Class<E> eClass) {
        return new SetBuilder<>();
    }

    public SetBuilder<E> add(E element) {
        if (innerSet == null) innerSet = Sets.newHashSet();

        innerSet.add(element);

        return this;
    }

    public Set<E> get() {
        return innerSet;
    }
}
