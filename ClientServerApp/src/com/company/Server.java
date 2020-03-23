package com.company;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("Server is running");

            clientSocket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String s = in.readLine();
            System.out.println(s);

            out.write(s + ", world!\n");
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                serverSocket.close();
                clientSocket.close();
                in.close();
                out.close();
                System.out.println("Server shuttered down");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
