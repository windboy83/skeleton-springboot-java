package com.sample.core.common.utils;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamUtils {

    public static <E> List<E> filter(List<E> list, Predicate<E> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        return list.stream()
                .filter(predicate)
                .toList();
    }

    public static <E, T> List<E> map(T[] arrays, Function<T, E> function) {
        return map(List.of(arrays), function);
    }

    public static <E, T> List<E> map(List<T> list, Function<T, E> function) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        return list.stream()
                .map(function)
                .toList();
    }

    public static <E, T> Map<E, List<T>> toMap(List<T> list, Function<T, E> keyFunction) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }

        return list.stream().collect(Collectors.groupingBy(keyFunction));
    }

    public static <E, T, R> Map<E, R> toMap(List<T> list, Function<T, E> keyFunction, Function<T, R> valueFuction) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }

        return list.stream().collect(Collectors.toMap(keyFunction, valueFuction));
    }

    public static <E, T, R> Map<E, List<R>> toMapGroupingAndMapping(List<T> list, Function<T, E> keyFunction, Function<T, R> valueFuction) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }

        return list.stream()
                .collect(
                        Collectors.groupingBy(
                                keyFunction,
                                Collectors.mapping(valueFuction, Collectors.toList())
                        )
                );
    }
}
