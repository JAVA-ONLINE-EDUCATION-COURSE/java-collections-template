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
        return getWords(text).stream().collect(Collectors.toSet()).size();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.asList(text.split("\\W+"));
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text).stream()
                .collect(HashMap::new, (k, v) -> {
                    if (k.containsKey(v)) {
                        k.put(v, k.get(v) + 1);
                    } else {
                        k.put(v, 1);
                    }

                    }, HashMap::putAll);
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List <String> sortWords = getWords(text);
        if (direction == Direction.DESC) {
            sortWords.sort((o1, o2) -> o2.length() - o1.length());
        } else if (direction == Direction.ASC) {
            sortWords.sort((o1, o2) -> o1.length() - o2.length());
        }
        return sortWords;
    }
}
