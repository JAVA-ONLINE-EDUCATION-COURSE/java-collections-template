package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return Stream.of(text.split("[^\\w]+"))
                .mapToInt((w)->w.length())
                .sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return Stream.of(text.split("[^\\w]+"))
                .mapToInt((w)->1)
                .sum();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) Stream.of(text.split("[^\\w]+"))
                .distinct()
                .count();
    }

    @Override
    public List<String> getWords(String text) {
        return Stream.of(text.split("[^\\w]+"))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return  Stream.of(text.split("[^\\w]+"))
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return Stream.of(text.split("[^\\w]+"))
                .collect(Collectors.groupingBy(w->w,Collectors.summingInt(w->1)));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        return Stream.of(text.split("[^\\w]+"))
                .sorted(direction.equals(Direction.ASC)?
                        Comparator.comparing(String::length)
                        : Comparator.comparing(String::length).reversed())
                .collect(Collectors.toList());
    }
}
