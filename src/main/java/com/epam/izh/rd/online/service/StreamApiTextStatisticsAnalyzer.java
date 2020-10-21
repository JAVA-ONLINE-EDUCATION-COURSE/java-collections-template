package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return 0;
    }

    @Override
    public int countNumberOfWords(String text) {
        return 0;
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return 0;
    }

    @Override
    public List<String> getWords(String text) {
        String[] splStr = text.replaceAll("\\W+", " ").split(" ");
        List<String> words = Arrays.stream(splStr).collect(Collectors.toList());
        return words;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        Set<String> uniq = getWords(text).stream().collect(Collectors.toSet());
        return uniq;
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        Map keyValue = getWords(text).stream()
                .collect(Collectors.toMap(elem -> Collections.frequency(getWords(text),elem)), Collectors.joining(":"),);

        return emptyMap();
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        return emptyList();
    }
}
