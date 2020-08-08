package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return new HashSet<>(getWords(text)).size();
    }

    @Override
    public List<String> getWords(String text) {
        return new SimpleTextStatisticsAnalyzer().getWords(text);
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return new HashSet<>(getWords(text));
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return new SimpleTextStatisticsAnalyzer().countNumberOfWordsRepetitions(text);
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> list = getWords(text);
        list = list.stream()
            .sorted(!direction.equals(Direction.ASC) ? Comparator.comparing(String::length).reversed() : Comparator.comparing(String::length))
        .collect(Collectors.toList());
        return list;
    }
}
