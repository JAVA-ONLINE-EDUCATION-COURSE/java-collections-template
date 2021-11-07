package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
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

        String[] wordsOfString = text.split("[\\s\",.?!-]+");
        int countSum = Arrays.stream(wordsOfString).mapToInt(String::length).sum();
        return countSum;
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

        String[] wordsOfString = text.split("[\\s\",.?!-]+");
        List<String> completedList = Stream.of(wordsOfString).collect(Collectors.toList());
        return completedList;
    }

    @Override
    public Set<String> getUniqueWords(String text) {

        String[] wordsOfString = text.split("[\\s\",.?!-]+");
        Set<String> listOfUniqueWords = Stream.of(wordsOfString).collect(Collectors.toSet());
        return listOfUniqueWords;
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {

        String[] wordsOfString = text.split("[\\s\",.?!-]+");
        Map<String, Integer> countMap = new HashMap<>();
        Arrays.stream(wordsOfString).forEach(e->countMap.put(e, countMap.getOrDefault(e, 0) + 1));
        return countMap;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {

        String[] wordsOfString = text.split("[\\s\",.?!-]+");
        List<String> sortedListASC = Stream.of(wordsOfString).collect(Collectors.toList());
        List<String> sortedListDESC = Stream.of(wordsOfString).collect(Collectors.toList());

        switch(direction) {
            case ASC:
                sortedListASC.sort(Comparator.comparing(String::length));
                return sortedListASC;
            case DESC:
                sortedListDESC.sort(Comparator.comparing(String::length));
                Collections.reverse(sortedListDESC);
                return sortedListDESC;
        }
        return emptyList();
    }
}
