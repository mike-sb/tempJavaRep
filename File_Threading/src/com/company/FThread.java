package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FThread extends Thread {

    HashMap<String,Integer> hm;

    public FThread(String name, HashMap<String,Integer> hm) {
        super(name);
        this.hm = hm;
    }

    public void run(String fname) throws IOException {
        long time = System.currentTimeMillis();
        synchronized (hm) {
            System.out.println("File " + fname + " opened");
            String[] s;

            FileReader fr = new FileReader("C:\\Users\\on_de\\IdeaProjects\\File_Threading\\out\\" + fname + ".txt");
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {

                s = scan.nextLine().split(" ");
                for (int i = 0; i < s.length; i++) {
                    if ( hm.containsKey(s[i])) {
                        hm.put(s[i],hm.get(s[i])+1);
                    } else {
                        hm.put(s[i], 1);
                    }
                }
            }


            fr.close();
            System.out.print("Time: ");
            System.out.println(System.currentTimeMillis()-time);
        }
    }
}
