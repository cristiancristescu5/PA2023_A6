package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;
    private GameServer gameServer;
    private boolean running = true;

    public ClientThread(Socket clientSocket, GameServer server){
        this.clientSocket = clientSocket;
        this.gameServer = server;
        try{
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);
        }catch (Exception e){
            System.err.println("Eroare la crearea input/output streams pentru client.");
        }
    }

    @Override
    public void run() {
        try(
            BufferedReader inputSocket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outputSocket = new PrintWriter(clientSocket.getOutputStream(), true);
        ){
          String inputCommand;
          while((inputCommand = inputSocket.readLine())!=null && running){
              System.out.println("[server] Comanda primita de la client: " + inputCommand);
              if(inputCommand.equals("stop")){
                  gameServer.stopServer();
                  outputSocket.println("[client] Server oprit!");
                  break;
              }
              if(inputCommand.equals("exit")){
                  running = false;
                  outputSocket.println("[server] Client deconectat de la server!");
                  break;
              }
              else{
                  outputSocket.println("[server]Server-ul a primit cererea: " + inputCommand);
              }
          }
        } catch (Exception e){
            System.err.println("[server] Eroare la procesarea cererii clientului: " + e.getMessage());
        }
    }
}
