package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static Scanner scanner;
    private String serverAddress;
    private Integer serverPort;


    public Client(String address, Integer port) {
        serverAddress = address;
        serverPort = port;
        scanner = new Scanner(System.in);

    }

    public void start() {
        try {
            socket = new Socket(serverAddress, serverPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            inputName();

            new ReadMessage().start();
            new WriteMessage().start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inputName() {
        try {
            out.write(scanner.nextLine() + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ReadMessage extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    String command = in.readLine();
                    if (command.equals("stop")) {
                        Client.this.stop();
                        break;
                    } else {
                        System.out.println(command);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public class WriteMessage extends Thread {

        @Override
        public void run() {
            while (true) {

                try {
                    out.write(scanner.nextLine() + "\n");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void stop() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost",8888);
        client.start();

    }
}
