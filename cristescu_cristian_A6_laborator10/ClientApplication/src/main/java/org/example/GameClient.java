package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private String host;
    private Integer port;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private boolean running = true;
    public GameClient(String host, Integer port){
        this.host = host;
        this.port = port;
    }

    public void startClient(){
        try{
            socket = new Socket(host, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Conectat la server la " + host + ": " + port);
            System.out.println("Comenzi: ");
            System.out.println("-'exit' pentru a inchide clientul");
            System.out.println("-'stop' pentru a inchide server-ul");

            BufferedReader command = new BufferedReader(new InputStreamReader(System.in));
            while(running){
                String inputCommand = command.readLine();
                if(inputCommand == null || inputCommand.equals("exit")){
                    output.println(inputCommand);
                    String raspuns = input.readLine();
                    System.out.println(raspuns);
                    running = false;
//                    System.out.println("[client] Client oprit!");
                }
                if(inputCommand.equals("stop")){
                output.println(inputCommand);
                String response = input.readLine();
                    System.out.println(response);
                    running = false;
                }
            }
            socket.close();
        }catch (Exception e){
            System.err.println("Eroare la conectarea la server:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GameClient gameClient = new GameClient("localhost", 4000);
        gameClient.startClient();
    }
}