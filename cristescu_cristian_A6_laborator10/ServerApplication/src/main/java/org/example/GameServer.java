package org.example;

import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private Integer port;
    private ServerSocket serverSocket;
    public GameServer(Integer port){
        this.port = port;
    }
    public void startServer(){
        try{
            serverSocket = new ServerSocket(port);
            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("[server] Client conectat " + clientSocket.getInetAddress().getHostAddress());
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
            }
        }catch (Exception e){
        }
    }
    public void stopServer() {
        try{
            serverSocket.close();
            System.out.println("[server] Server oprit!");
        }catch (Exception e){
            System.err.println("[server] Eroarea la oprirea server-ului");
        }
    }

    public static void main(String[] args) {
        GameServer gameServer = new GameServer(4000);
        gameServer.startServer();
    }
}