package org.types.network;

import org.types.RandomWord;
import org.types.Singleton;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private final int port = 3345;

    private Socket socket;
    private ServerSocket serverSocket;

    private static int total_clients = 0;

    private String words = "";

    public static ArrayList<EchoThread> connectionHandlers = new ArrayList<>();

    public Server(){
        try{
            serverSocket = new ServerSocket(port);
            generate_words();
        } catch (Exception e) {
            System.out.println("Creating a server failed");
        }

        while(true){
            try{
                socket = serverSocket.accept();

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                String client_name = dataInputStream.readUTF();

                EchoThread inComing = new EchoThread(socket, words,client_name);

                for (EchoThread connectionHandler : connectionHandlers) {
                    connectionHandler.updateProgress(client_name+",0.0,0");
                    inComing.updateProgress(connectionHandler.getClient_name()+",0.0,0");
                }
                inComing.start();

                connectionHandlers.add(inComing);
                total_clients++;
                System.out.println(client_name+" connected to the server.\t"+total_clients+" Clients");
            } catch (IOException e) {
                System.out.println("I/O error "+e);
            }
        }
        // new thread for a client.
    }

    public void generate_words(){
        RandomWord r = new RandomWord();
        words = r.getRandomWord();
        for(int i = 1; i<70; i++){
            words = words.concat(",".concat(r.getRandomWord()));
        }
    }

    public void shutDown_server(){
        try {
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
