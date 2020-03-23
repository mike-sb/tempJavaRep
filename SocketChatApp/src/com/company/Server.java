package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    public static LinkedList<ClientServerThread> threads;

    public static void main(String[] args) {
        threads = new LinkedList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                threads.add(new ClientServerThread(socket));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
