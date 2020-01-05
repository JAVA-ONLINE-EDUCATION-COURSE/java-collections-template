package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    //Надеюсь, я правильно понял, чего от меня хотят в этом задании, просто какие-то Stream'ы получаются
    // слишком избыточными (неуместными), не знаю, как еще сказать.

    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text)
                .stream()
                .peek(System.out::println) //Добавлено, что бы убрать предложение IDEA сделать Collection.size()
                .count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) getWords(text)
                .stream()
                .distinct()
                .count();
    }

    @Override
    public List<String> getWords(String text) {
        if (text == null) {
            return emptyList();
        }

        //Вариант 2 - окончательный
        return Pattern.compile(new SimpleRegExp().REG_EXP_WORD())
                .splitAsStream(text)
                .collect(Collectors.toList());

        //Вариант 1 - начальный
//        return Arrays.stream(text.split(new SimpleRegExp().REG_EXP_WORD()))
//                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text)
                .stream()
                .peek(System.out::println) //Добавлено, что бы убрать предложение IDEA сделать HashSet.
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        Map<String, Integer> map = new HashMap<>();
        getWords(text)
                .stream()
                .distinct()
                .sequential() //Специально добавил, т.к. в лекции Сергея Куценко, было сказано, что forEach - зло,
                // когда он многопоточный (нужно самому следить за правильность работы), поэтому
                // высталяю признак работы в одном потоке
                .forEach(s -> map.put(s, (int) getWords(text)
                        .stream()
                        .filter(s::equals)
                        .count()));
        return map;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        if (direction == Direction.ASC) {
            return getWords(text)
                    .stream()
                    .sorted(new SimpleStringLengthComparator())
                    .collect(Collectors.toList());
        } else {
            return getWords(text)
                    .stream()
                    .sorted(new SimpleStringLengthComparator().reversed())
                    .collect(Collectors.toList());
        }
    }
}
