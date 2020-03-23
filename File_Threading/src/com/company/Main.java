package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        HashMap<String,Integer> hm =  new HashMap<>();
        for (int i = 0; i < 4; i++) {
            FThread f = new FThread("thread: "+i,hm);
            f.run("sometext"+i);
        }
        System.out.println(hm);
    }
}
