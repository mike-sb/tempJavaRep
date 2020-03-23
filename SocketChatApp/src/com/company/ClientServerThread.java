package com.company;

import java.io.*;
import java.net.Socket;
import java.util.ServiceConfigurationError;

public class ClientServerThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String name;

    public ClientServerThread(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            name = in.readLine();
            System.out.println(name +" entered the chat.");
            out.write("Welcome to the chat!\n");
            out.flush();

            while (true) {
                String command = in.readLine();
                System.out.println(command);
                if (command.equals("stop")) {
                    exit();
                    interrupt();
                } else if (command.equals("\\w <> kdkpshoi")) {

                } else if (command.startsWith("send_all")) {
                    for (ClientServerThread serverThread: Server.threads) {
                        if (serverThread!= this)
                        serverThread.send(command.substring(8));
                    }
                }
            }
        } catch (IOException e) {
            //  e.printStackTrace();
        }
    }
    private void send(String msg)
    {
        try {
            out.write(msg+"\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void exit() {

        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Server.threads.remove(this);
    }
}
