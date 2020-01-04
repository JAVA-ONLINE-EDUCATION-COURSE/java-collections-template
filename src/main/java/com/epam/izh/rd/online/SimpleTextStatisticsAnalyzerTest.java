package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleTextStatisticsAnalyzer;

public class SimpleTextStatisticsAnalyzerTest {
    public static void main(String[] args) {
        SimpleTextStatisticsAnalyzer simpleTextStatisticsAnalyzer = new SimpleTextStatisticsAnalyzer();
        System.out.println(simpleTextStatisticsAnalyzer.getWords("One, two, three, three - one, tWo, tWo!!"));
        System.out.println(simpleTextStatisticsAnalyzer.getWords("One* two= three! three. - one!! tWo... tWo!!"));
        System.out.println(simpleTextStatisticsAnalyzer.getWords(""));
    }


}
