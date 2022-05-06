package com.ysupreme.resume.utils;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-26 00:09
 **/

public class ValueComparator implements Comparator<Integer> {

    private TreeMap<Integer, Float> map;

    public ValueComparator(TreeMap<Integer, Float> map) {
        super();
        this.map = map;
    }

    //按照降序排列
    @Override
    public int compare(Integer o1, Integer o2) {
        if (map.get(o1) > map.get(o2)) {
            return -1;
        } else {
            return 1;
        }
    }
}