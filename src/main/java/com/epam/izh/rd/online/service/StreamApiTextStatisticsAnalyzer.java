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
        return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).stream().mapToInt(a -> 1).sum();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet()).size();
    }

    @Override
    public List<String> getWords(String text) {
        return Stream.of(text.split("\\W+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text).stream().collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        return getWords(text).stream().sorted(getStringComparator(direction)).collect(Collectors.toList());
    }

    private Comparator<String> getStringComparator(Direction direction) {
        return direction.equals(Direction.ASC) ?
                Comparator.comparing(String::length) :
                Comparator.comparing(String::length).reversed();
    }
}
