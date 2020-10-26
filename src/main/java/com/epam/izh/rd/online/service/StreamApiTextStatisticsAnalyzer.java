package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream()
                .mapToInt(i -> i.length())
                .sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text).stream()
                .count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) getUniqueWords(text).stream()
                .count();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(text.split("\\s"))
                .map(s -> s.replaceAll("\\W",""))
                .filter(s -> !s.equals(""))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream()
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text).stream().collect(Collectors.toMap(s -> s,s -> 1,Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        switch (direction){
            case DESC:return getWords(text).stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());
            case ASC:return getWords(text).stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        }
        return emptyList();
    }
}
