package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        return getWords(text).stream().mapToInt(word -> word.length()).reduce(0, (lengthWords, word) -> lengthWords += word);
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
        return Stream.of(text.split("(\\,|\\.|\\!|\\?|\\s|\\'|\\\"|\\-)+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return Stream.of(text.split("(\\,|\\.|\\!|\\?|\\s|\\'|\\\"|\\-)+")).collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        //getWords(text).stream().collect(Collectors.toMap(k -> k, v -> Math.toIntExact(getWords(text).stream().filter(v::equals).count()), (k, v) -> v));
        return getWords(text).stream().collect(Collectors.toMap(key -> key, valueMain -> (int)getWords(text).stream().filter(valueMain::equals).count(), (key, value) -> key));
        //return  getWords(text).stream().collect(Collectors.groupingBy(word,Collectors.counting()));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        if (direction.equals(Direction.ASC)) {
            return getWords(text).stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        } else {
            return getWords(text).stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());
        }
    }

}
