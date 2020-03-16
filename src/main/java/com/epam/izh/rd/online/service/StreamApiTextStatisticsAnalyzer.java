package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        StringBuilder strBuilder = new StringBuilder();
        getWords(text).forEach(strBuilder::append);
        return strBuilder.length();
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return getUniqueWords(text).size();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(text.split("\\PL+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return new HashSet<>(getWords(text));
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        Set<String> words = getUniqueWords(text);
        List<String> wordsList = getWords(text);
        Map<String, Integer> wordsMap = new HashMap<>();
        words.forEach(w -> {
            long count = wordsList.stream()
                    .filter(w::equals)
                    .count();
            wordsMap.put(w, (int) count);
        });
        return wordsMap;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> words = new ArrayList<>(getWords(text));
        words.sort(getComparator(direction));
        return words;
    }

    private Comparator<String> getComparator(Direction direction) {
        if (direction.equals(Direction.ASC)) {
            return Comparator.comparingInt(String::length);
        }
        return (w1, w2) -> Integer.compare(w2.length(), w1.length());
    }
}
