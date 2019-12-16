package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        List<String> words = getWords(text);
        return words.stream().reduce(0, (sum, s) -> sum += s.length(), Integer::sum);
    }


    @Override
    public int countNumberOfWords(String text) {
        List<String> words = getWords(text);
        return words.stream().reduce(0, (sum, s) -> sum += 1, Integer::sum);

    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        List<String> words = getWords(text);
        List<String> repeatedWords = new ArrayList<>();
        return (int) words.stream().filter((o1) -> !repeatedWords.contains(o1)).map(repeatedWords::add).count();
    }

    @Override
    public List<String> getWords(String text) {
        Pattern pattern = Pattern.compile("[\\W+]");
        Matcher matcher = pattern.matcher(text);

        String textVer2 = matcher.replaceAll("1");
        String[] wordsArray = textVer2.split("1+");
        return Arrays.stream(wordsArray).collect(Collectors.toList());


    }

    @Override
    public Set<String> getUniqueWords(String text) {
        List<String> words = getWords(text);
        return words.stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        List<String> words = getWords(text);
        Set<String> wordSet = new HashSet<>(words);
        Map<String, Integer> countedWords = new HashMap<>();
        wordSet.stream().forEach((s) -> countedWords.put(s, countRepeats(s, words)));
        return countedWords;
    }

    private int countRepeats(String word, List<String> list) {
        return (int) list.stream().filter(s -> s.equals(word)).count();
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> words = getWords(text);
        Comparator<String> comparator = Comparator.comparing(String::length);
        comparator = direction == Direction.ASC ? comparator : comparator.reversed();
        return words.stream().sorted(comparator).collect(Collectors.toList());

    }
}
