package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        List<String> listText = getWords(text);
        return listText.stream().reduce(0, (sum, s) -> sum += s.length(), Integer::sum);
    }

    @Override
    public int countNumberOfWords(String text) {

        return  (int)getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text)
    {
        return (int)getWords(text).stream().distinct().count();
    }

    @Override
    public List<String> getWords(String text) {
        Pattern pattern = Pattern.compile("[^A-Z a-z\n]");
        Matcher matcher = pattern.matcher(text);
        String s = matcher.replaceAll("");

        String strings[];
        strings=s.split("[ \n]+");
        List<String> list  = Arrays.stream(strings).collect(Collectors.toList());
        return list;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        Pattern pattern = Pattern.compile("[^A-Z a-z\n]");
        Matcher matcher = pattern.matcher(text);
        String s = matcher.replaceAll("");

        String strings[];
        strings=s.split("[ \n]+");
        Set<String> set = Arrays.stream(strings).collect(Collectors.toSet());
        return set;
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        List<String> stringList = getWords(text);
        Set<String> stringSet = new HashSet<>(stringList);
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        stringSet.stream().forEach((m) -> stringIntegerMap.put(m,getRepeat(m, stringList)));
        return stringIntegerMap;
    }
    private int getRepeat(String word, List<String> list) {
        return (int) list.stream().filter(s -> s.equals(word)).count();
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        if(direction==Direction.ASC){
            return getWords(text).stream().sorted((o1, o2) -> o1.length() - o2.length()).collect(Collectors.toList());
        }
        if(direction==Direction.DESC){
            return getWords(text).stream().sorted((o1, o2) -> -(o1.length() - o2.length())).collect(Collectors.toList());
        }
        return null;

    }
}
