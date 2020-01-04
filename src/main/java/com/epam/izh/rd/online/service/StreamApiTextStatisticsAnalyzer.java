package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    //Надеюсь, я правильно понял, чего от меня хотят в этом задании, просто какие-то Stream'ы получаются
    // слишком избыточными (неуместными), не знаю, как еще сказать.

    private SimpleRegExp simpleRegExp = new SimpleRegExp();

    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text)
                .stream()
                .count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) getWords(text)
                .stream()
                .distinct()
                .count();
    }

    @Override
    public List<String> getWords(String text) {
        if (text == null) {
            return emptyList();
        }

        return Arrays.stream(text.split(simpleRegExp.REG_EXP_WORD()))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        //Вот тут IDEA вабще говорит, давай-ка HashSet
        return getWords(text)
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return emptyMap();
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        if (direction == Direction.ASC) {
            return getWords(text)
                    .stream()
                    .sorted(new SimpleStringLengthComparator())
                    .collect(Collectors.toList());
        } else {
            return getWords(text)
                    .stream()
                    .sorted(new SimpleStringLengthComparator().reversed())
                    .collect(Collectors.toList());
        }
    }
}
