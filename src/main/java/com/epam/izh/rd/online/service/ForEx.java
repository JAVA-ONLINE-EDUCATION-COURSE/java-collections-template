package com.epam.izh.rd.online.service;

import java.util.ArrayList;
import java.util.List;

public class ForEx {
    public static void main(String[] args) {
        String text ="первое слово, дороже второго";
        List<String> list = new ArrayList<>();
        String[] subStr;
        String delimeter = "\\."; // Разделитель
        subStr = text.split(delimeter);
        // Вывод результата на экран
        for(int i = 0; i < subStr.length; i++) {
            list.add(subStr[i]);
        }
        System.out.println(list);
    }
}
