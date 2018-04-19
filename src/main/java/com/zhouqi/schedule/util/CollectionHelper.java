package com.zhouqi.schedule.util;

import java.util.Collection;

public class CollectionHelper {
    public static <T, S, C extends Collection<T>, D extends Collection<S>> C filter(D source, C target, IFilter<S, T> filter) {
        if (source == null || source.size() == 0)
            return target;
        int index = 0;
        for (S v : source) {
            T tv = filter.filter(v, index++);
            if (tv != null) {
                target.add(tv);
            }
        }
        return target;
    }
}
