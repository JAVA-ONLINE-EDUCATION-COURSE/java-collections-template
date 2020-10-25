package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {

    @Override
    public int countSumLengthOfWords(String text) {
        return this.getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return this.getWords(text).stream().map(String::length).reduce(0, (a, e) -> ++a);
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return this.getUniqueWords(text).stream().mapToInt(e -> 1).sum();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(text.split(SimpleTextStatisticsAnalyzer.PUNCT_WORDS_PATTERN))
            .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return this.getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return this.getWords(text).stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        if (direction.equals(Direction.DESC)) {
            return this.getWords(text).stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .collect(Collectors.toList());
        }
        return this.getWords(text).stream().sorted(Comparator.comparing(String::length))
            .collect(Collectors.toList());

    }
}
