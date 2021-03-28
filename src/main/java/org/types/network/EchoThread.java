package org.types.network;

import java.io.*;
import java.net.Socket;

public class EchoThread extends Thread {

    private final int port = 3345;
    protected Socket socket;

    private InputStream inputStream;
    private DataInputStream dataInputStream;

    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;

    private String words;

    private String client_name;

    public EchoThread(Socket clientSocket, String _words, String client_name) {
        this.socket = clientSocket;
        this.words = _words;
        this.client_name = client_name;

        try {
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(words);

        } catch (IOException e) {
            System.out.println("Connection Failed.");
        }
    }

    public void run() {
        while (true) {
            try {
                String data = dataInputStream.readUTF();
                System.out.println("Me: "+client_name+"\t"+data);

                if(data.split(",").length == 3){
                    for (EchoThread connectionHandler : Server.connectionHandlers) {
                        if(connectionHandler != this)
                            connectionHandler.updateProgress(data);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateProgress(String opponent){
        try{
            dataOutputStream.writeUTF(opponent);
        } catch (IOException e) {
            System.out.println("updating progress failed.");
        }
    }

    public String getClient_name() {
        return client_name;
    }
}
