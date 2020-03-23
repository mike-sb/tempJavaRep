package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static Scanner scanner;


    public static void main(String[] args) {

        try {
            System.out.println("Client side is running. Enter str: ");
            socket = new Socket("localhost", 8888);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out   = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            out.write(str+"\n");
            out.flush();
            String serverStr = in.readLine();
            System.out.println(serverStr);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
