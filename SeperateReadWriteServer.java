import java.util.*;
import java.io.*;
import java.net.*;

class Writer implements Runnable
{
    private Socket clientSocket;
    public Writer(Socket cskt)
    {
        clientSocket=cskt;
    }
    public void run()
    {
        try
        {
            DataOutputStream outToClient=new DataOutputStream(clientSocket.getOutputStream());
            Date now=new Date();
            outToClient.writeBytes(now.toString()+"\n");

        }
        catch(Exception e)
        {
            System.out.println("Exception caught:"+e.getMessage());
        }
    }
}

class Reader implements Runnable
{
    private Socket clientSocket;
    public Reader(Socket cskt)
    {
        clientSocket=cskt;
    }
    public void run()
    {
        try
        {
            BufferedReader inFromClient=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Client Sent:"+inFromClient.readLine());
        }
        catch(Exception e)
        {
            System.out.println("Exception caught:"+e.getMessage());
        }
    }
}


public class SeperateReadWriteServer {
    public static void main(String[] args) throws IOException {
        int portNumber=19000;
        ServerSocket skt=new ServerSocket(portNumber);
        while(true)
        {
            Socket cliSocket=skt.accept();
            Thread readerThread=new Thread(new Reader(cliSocket));
            Thread writerThread=new Thread(new Writer(cliSocket));
            readerThread.start();
            writerThread.start();

        }
    }
}
