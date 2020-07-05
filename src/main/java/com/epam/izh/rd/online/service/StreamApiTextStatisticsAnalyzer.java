package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return Arrays.stream(text.split("\\s*\\W\\s*")).filter(x -> !x.equals("")).mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) Arrays.stream(text.split("\\s*\\W\\s*")).filter(x -> !x.equals("")).count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) Arrays.stream(text.split("\\s*\\W\\s*")).filter(x -> !x.equals("")).distinct().count();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(text.split("\\s*\\W\\s*")).filter(x -> !x.equals("")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text).stream().collect(Collectors.toMap(key -> key, value -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        return direction.name().contains("ASC") ?
                getWords(text).stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList()) :
                getWords(text).stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
    }
}
