package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text)
                .stream()
                .mapToInt(String::length)
                .sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text)
                .size();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return getWords(text)
                .stream()
                .distinct()
                .collect(Collectors.toList())
                .size();
    }

    @Override
    public List<String> getWords(String text) {
        final List<String> listOfWords = Stream.of(text.split("\\W+"))
                .collect(Collectors.toList());
        return listOfWords;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return Stream.of(getWords(text))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return Stream.of(getWords(text))
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        Comparator<String> comparator = Comparator.comparingInt(String::length);
        return getWords(text)
                .stream()
                .sorted((direction.equals(Direction.ASC) ? comparator : comparator.reversed()))
                .collect(Collectors.toList());
    }
}
