import java.io.*;
import java.net.*;
import java.util.Date;


class ClientHandler implements Runnable
{
    ServerSocket serverSocket;
    public ClientHandler(ServerSocket cskt)
    {
        serverSocket=cskt;
    }
    public void run()
    {
        try{
        
        
        Socket clientSocket=serverSocket.accept();
        System.out.println("Accepted Request on Port:"+clientSocket.getPort());
        while(true)
        {
        DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
        Date now=new Date();
        String systemTime=now.toString();
        outToClient.writeBytes(systemTime+"\n");
        
        }
        }
        catch(Exception e)
        {
            System.out.println("Exception Caught:"+e.getMessage());
        }
    }
}

public class ConcurrentServer
{
    public static void main(String[] args) throws IOException {
        int portNumber=17541;
        ServerSocket serverSocket=new ServerSocket(portNumber);
        while(true)
        {
            
            ClientHandler clientHandler=new ClientHandler(serverSocket);
            Thread clientThread=new Thread(clientHandler);
            clientThread.start();
        }   
    }
}