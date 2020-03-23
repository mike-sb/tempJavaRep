package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;

public class Main {

    static int sockMerchant(int n, int[] ar) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
        map.put(ar[i], map.get(ar[i])+1);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (ar[i] == ar[j] && i != j) {
                    count += 1;
                    break;
                }
            }

        }
        return count % 2 == 0 ? (int) count / 2 : (int) (count / 2) - 1;
    }

    public static void main(String[] args) {


        int[] ar = new int[]{10,20,20,10,10,30,50,10,20};

        int result = sockMerchant(9, ar);
        System.out.println(result);
    }
}
