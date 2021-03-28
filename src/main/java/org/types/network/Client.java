package org.types.network;

import org.types.Singleton;
import org.types.models.Opponent;

import java.io.*;
import java.net.Socket;

public class Client {

    private final int port = 3345;

    private Socket connection;


    private InputStream inputStream;
    private DataInputStream dataInputStream;

    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;

    private String words;

    public Thread thread;

    public Client(){
    }

    public void connect(String name) {
        try {
            connection = new Socket("192.168.100.23", port);
            inputStream = connection.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

            outputStream = connection.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(name);
            words = dataInputStream.readUTF();

            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            String data = dataInputStream.readUTF();
                            System.out.println(data);
                            if(data.split(",").length == 3){
                                String[] op = data.split(",");
                                Opponent opponent = opponentExists(op[0]);
                                if(opponent != null){
                                    opponent.setProgress(Float.parseFloat(op[1]));
                                    opponent.setWpm(Integer.parseInt(op[2]));
                                }else {
                                    opponent = new Opponent();
                                    opponent.setName(op[0]);
                                    opponent.setProgress(Float.parseFloat(op[1]));
                                    opponent.setWpm(Integer.parseInt(op[2]));

                                    Singleton.getInstance().opponents.add(opponent);
                                }
                                System.out.println(Singleton.getInstance().opponents.toString());
                            }
                        } catch (IOException e) {
                            System.err.println("err in client thread");
                        }
                    }
                }
            });
            thread.start();

            System.out.println("Connected to the server.");

        } catch (IOException e) {
            System.out.println("server connection failed.");
            e.printStackTrace();
        }
    }

    private Opponent opponentExists(String _name){
        for (Opponent opponent : Singleton.getInstance().opponents) {
            if(opponent.getName().equals(_name)){
                return opponent;
            }
        }
        return null;
    }

    public void update_opponent(String _data){
        try{
            dataOutputStream.writeUTF(_data);
            System.out.println("Sending data to server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String getWords() {
        return words;
    }
}
