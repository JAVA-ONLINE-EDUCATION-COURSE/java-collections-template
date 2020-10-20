package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;

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

        return emptyList();
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return emptySet();
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return emptyMap();
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> rez = new ArrayList<>();
        rez.addAll(getWords(text));
        if (direction.equals(Direction.DESC)){
            rez.sort(Comparator.comparing(String::length));
            return rez;
        }else if (direction.equals(Direction.ASC)){
            rez.sort(Comparator.comparing(String::length));
            Collections.reverse(rez);
            return rez;
        }
        return emptyList();
    }
}
