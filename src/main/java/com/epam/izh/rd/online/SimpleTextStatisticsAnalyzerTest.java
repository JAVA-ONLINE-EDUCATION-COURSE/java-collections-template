package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleTextStatisticsAnalyzer;
import com.epam.izh.rd.online.helper.Direction;

public class SimpleTextStatisticsAnalyzerTest {
    public static void main(String[] args) {
        SimpleTextStatisticsAnalyzer simpleTextStatisticsAnalyzer = new SimpleTextStatisticsAnalyzer();
//        System.out.println(simpleTextStatisticsAnalyzer.getWords("One, two, three, three - one, tWo, tWo!!"));
//        System.out.println(simpleTextStatisticsAnalyzer.getWords("Hello, Hi, mother, father - good, cat, c,,, c,,,, c!!"));
//        System.out.println(simpleTextStatisticsAnalyzer.getWords(""));
//        System.out.println(simpleTextStatisticsAnalyzer.sortWordsByLength("Hello, Hi, mother, father - good, cat, c,,, c ,, c!!", Direction.ASC));
//        System.out.println(simpleTextStatisticsAnalyzer.sortWordsByLength("Hello, Hi, mother, father - good, cat, c,,, c ,, c!!", Direction.DESC));
        System.out.println(simpleTextStatisticsAnalyzer.countSumLengthOfWords("One, I - tWo!!"));
    }


}
