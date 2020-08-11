package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;
import static java.util.stream.Collectors.reducing;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream().mapToInt(String :: length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {

        return (int) getWords(text).stream().collect(Collectors.toSet()).stream().count();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(text.split("\\W")).filter(x -> x.length() > 0).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {

        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text).stream().collect(Collectors.groupingBy(s -> s, reducing(0, e -> 1, Integer::sum)));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {

        if (direction == Direction.DESC) {
            return getWords(text).stream().sorted(Comparator.comparing(String :: length).thenComparing(String :: compareTo).reversed()).collect(Collectors.toList());
        } else
        if (direction == Direction.ASC) {

            return getWords(text).stream().sorted(Comparator.comparing(String :: length).thenComparing(String :: compareTo)).collect(Collectors.toList());
        } else { throw new IllegalArgumentException();}
    }


    }

