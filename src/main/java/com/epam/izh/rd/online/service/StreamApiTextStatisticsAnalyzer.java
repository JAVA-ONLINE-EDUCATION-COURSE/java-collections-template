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
        return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) getWords(text).stream().distinct().count();

    }

    @Override
    public List<String> getWords(String text) {
        ArrayList<String> words = new ArrayList<>();
        String[] wordsArray = text.split("\\W+");
        Arrays.stream(wordsArray).forEach(words::add);
        return words;

    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());

    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getUniqueWords(text).stream().collect(Collectors.toMap(e -> e,
                e -> Collections.frequency(getWords(text), e)));

    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        return getWords(text).stream().sorted((o1, o2) -> direction == Direction.ASC ? o1.length() - o2.length() : o2.length() - o1.length())
                .collect(Collectors.toList());

    }
}
