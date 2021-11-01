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
        return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).stream().reduce(0, (sum, st) -> sum += 1, Integer::sum);
    }

    @Override
    public int countNumberOfUniqueWords(String text) {

        return getUniqueWords(text).size();
    }

    @Override
    public List<String> getWords(String text) {
        return  Arrays.stream(text.split("[\\p{Punct}\\s]+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {

        return getWords(text)
                .stream()
                .collect(Collectors.toMap(str -> str, str1 -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        Comparator<String> comparator = direction == Direction.ASC
                ? Comparator.comparing(String::length)
                : Comparator.comparing(String::length).reversed();

        return getWords(text).stream().sorted(comparator).collect(Collectors.toList());
    }
}
