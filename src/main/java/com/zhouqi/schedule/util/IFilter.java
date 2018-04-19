package com.zhouqi.schedule.util;

public interface IFilter<S, T> {
    T filter(S s, int index);
}
