package com.epam.izh.rd.online.service;

import java.util.Comparator;

class SimpleStringLengthComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if (o1 == null) {
            return -1;
        }

        if (o2 == null) {
            return 1;
        }

        if (o1.length() == o2.length()) {
            return 0;
        }

        return o1.length() - o2.length();
    }
}
