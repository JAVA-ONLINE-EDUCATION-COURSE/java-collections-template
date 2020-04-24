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
        List<String> words = getWords(text);
        return words.stream()
                .reduce(0, (countSymbol, word) -> countSymbol += word.length(), Integer::compareTo);
    }

    @Override
    public int countNumberOfWords(String text) {
        List<String> words = getWords(text);
        return words.stream()
                .reduce(0, (countWords, word) -> countWords + 1, Integer::compareTo);
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return getUniqueWords(text).size();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(text.split("\\W+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return new HashSet<>(getWords(text));
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        Set<String> wordsSet = getUniqueWords(text);
        List<String> wordsList = getWords(text);
        Map<String, Integer> mapList = new HashMap<>();
        wordsSet.forEach(word -> {
            long count = wordsList.stream().filter(word::equals).count();
            mapList.put(word, (int) count);
        });
        return mapList;
    }
    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> listWords = getWords(text);
        if (direction.equals(Direction.ASC)) {
            listWords.sort(Comparator.comparingInt(String::length));
        } else {
            listWords.sort(Comparator.comparingInt(String::length).reversed());
        }
        return listWords;
    }
}
