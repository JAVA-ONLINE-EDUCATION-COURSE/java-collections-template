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
        return getWords(text).stream().mapToInt(el -> el.length()).sum();
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
        if (text.length() == 0) {
            return emptyList();
        }
        return Arrays.asList(text.split("\\W+"));
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().distinct().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text).stream().collect(Collectors.toMap(el -> el, el -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };

        if (direction.toString().equals("DESC")) {
            return getWords(text).stream().sorted(comparator.reversed()).collect(Collectors.toList());
        }
        return getWords(text).stream().sorted(comparator).collect(Collectors.toList());
    }
}













