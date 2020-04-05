package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        List<String> list = getWords(text);
        return list.stream().reduce(0, (sum, s) -> sum += s.length(), Integer::sum);
    }

    @Override
    public int countNumberOfWords(String text) {
        List<String> list = getWords(text);
        return list.stream().reduce(0, (sum, s) -> sum += 1, Integer::sum);
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        List<String> list = getWords(text);
        List<String> listApi = list.stream().distinct().collect(Collectors.toList());
        return listApi.size();
    }

    @Override
    public List<String> getWords(String text) {
        Pattern pattern1 = Pattern.compile("[^A-Z a-z\n]");
        Matcher matcher = pattern1.matcher(text);
        String str = matcher.replaceAll("");
        String strList[];
        strList=str.split("[ \n]+");
        List<String> list  = Arrays.stream(strList).collect(Collectors.toList());
        return list;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        Set<String> set = getWords(text).stream().collect(Collectors.toSet());
        return set;
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        List<String> list = getWords(text);
        Set<String> set = list.stream().collect(Collectors.toSet());
        Map<String, Integer> map = new HashMap<>();
        set.stream().forEach((s) -> map.put(s, counter(s, list)));
        return map;
    }

    private int counter(String word, List<String> list) {
        return (int) list.stream().filter(s -> s.equals(word)).count();
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        Comparator<String> comp = Comparator.comparing(String::length);

        if (direction==Direction.ASC) {
            return getWords(text).stream().sorted(comp).collect(Collectors.toList());
        }
        else if (direction==Direction.DESC) {
            return getWords(text).stream().sorted(comp.reversed()).collect(Collectors.toList());
        }
        return null;
    }
}
