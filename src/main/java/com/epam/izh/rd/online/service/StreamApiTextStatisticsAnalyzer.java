package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.*;
import static java.util.stream.Collectors.counting;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        int count =getWords(text).stream().collect(Collectors.summingInt(s->s.length()));
        return count;
    }

    @Override
    public int countNumberOfWords(String text) {
        long count = getWords(text).stream().count();
        return (int) count;
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        int count = (int) getWords(text).stream().distinct().count();
        return count;
    }

    @Override
    public List<String> getWords(String text) {
        String[] splStr = text.replaceAll("\\W+", " ").split(" ");
        List<String> words = Arrays.stream(splStr).collect(Collectors.toList());
        return words;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        Set<String> uniq = getWords(text).stream().collect(Collectors.toSet());
        return uniq;
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {

        Map keyValue = getWords(text).stream()
                .collect(Collectors.groupingBy(String::valueOf,Collectors.counting()));
        keyValue.values().stream().collect(Collectors.toMap(Function.identity(), i -> (int) i.in));





        return cc;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        ArrayList<String> wordSort = new ArrayList<>();
        wordSort.addAll(getWords(text).stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList()));
        if (direction.equals(Direction.ASC)) {
            return wordSort;
        } else {
            reverse(wordSort);
            return wordSort;
        }
    }
}
