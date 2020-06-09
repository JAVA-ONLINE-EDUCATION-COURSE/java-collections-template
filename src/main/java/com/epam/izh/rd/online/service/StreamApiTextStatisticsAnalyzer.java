package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;
import static com.epam.izh.rd.online.helper.Direction.ASC;
import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {

    @Override
    public int countSumLengthOfWords(String text) {
        if (text == null || text.length() == 0) return 0;
        return getWords(text).stream().mapToInt(String::length).reduce(Integer::sum).orElse(0);
    }

    @Override
    public int countNumberOfWords(String text) {
        if (text == null || text.length() == 0) return 0;
        return getWords(text).size();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        if (text == null || text.length() == 0) return 0;
        return getUniqueWords(text).size();
    }

    @Override
    public List<String> getWords(String text) {
        if (text == null || text.length() == 0) return emptyList();
        return Arrays.stream(text.split("\\W")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        if (text == null || text.length() == 0) return emptySet();
        return new HashSet<>(getWords(text));
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        if (text == null || text.length() == 0) return emptyMap();
        return getWords(text).stream().collect(Collectors.toMap(
                s -> s,
                s -> 1,
                (a, b) -> a + 1
                )
        );
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        if (text == null || text.length() == 0) return emptyList();
        return getWords(text).stream().sorted((o1, o2) -> direction == ASC ? o1.length() - o2.length() : o2.length() - o1.length())
                .collect(Collectors.toList());
    }
}
