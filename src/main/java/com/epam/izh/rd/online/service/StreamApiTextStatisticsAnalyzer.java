package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    private SimpleTextStatisticsAnalyzer analyzer;

    @Override
    public int countSumLengthOfWords(String text) {
        analyzer = new SimpleTextStatisticsAnalyzer();
        List<String> strings = analyzer.getWords(text);
        return strings.stream().map(String::length)
                .reduce(0, (totalLength, wordLength) -> totalLength + wordLength);
    }

    @Override
    public int countNumberOfWords(String text) {
        analyzer = new SimpleTextStatisticsAnalyzer();
        List<String> strings = analyzer.getWords(text);
        return (int) strings.stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        analyzer = new SimpleTextStatisticsAnalyzer();
        List<String> strings = analyzer.getWords(text);
        return (int) strings.stream().distinct().count();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(text.split("[\\p{Punct}\\s]+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        analyzer = new SimpleTextStatisticsAnalyzer();
        List<String> strings = analyzer.getWords(text);
        return new HashSet<>(strings);
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        analyzer = new SimpleTextStatisticsAnalyzer();
        List<String> strings = analyzer.getWords(text);
        return strings.stream().collect(Collectors.toMap(Function.identity(), s -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        analyzer = new SimpleTextStatisticsAnalyzer();
        Comparator<String> comparator = Comparator.comparingInt(String::length);
        return analyzer.getWords(text)
                .stream()
                .sorted(direction.equals(Direction.ASC) ? comparator : comparator.reversed())
                .collect(Collectors.toList());

    }
}
