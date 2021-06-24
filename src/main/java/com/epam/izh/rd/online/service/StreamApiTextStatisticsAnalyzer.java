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

        return getWords(text).stream().mapToInt((w) -> w.length()).sum();
    }

    @Override
    public int countNumberOfWords(String text) {

        return (int) getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {

        return (int) getUniqueWords(text).stream().count();

    }

    @Override
    public List<String> getWords(String text) {

        return Arrays.stream(text.split("\\W+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {

        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {

        return  getUniqueWords(text).stream().collect(Collectors.toMap(wk->wk,
                wc-> Collections.frequency(getWords(text),wc)));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {

        return direction == Direction.ASC ?
                getWords(text).stream().sorted((o1, o2) -> o1.length() - o2.length()).collect(Collectors.toList()) :
                getWords(text).stream().sorted((o1, o2) -> o2.length() - o1.length()).collect(Collectors.toList());
    }

}
