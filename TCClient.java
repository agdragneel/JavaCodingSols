
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
class WriteHandler implements Runnable
{
    Socket cskt;
    public WriteHandler(Socket skt)
    {
        cskt=skt;
    }
    public void run()
    {
        try{
            while(true)
            {

            
        DataOutputStream outToClient=new DataOutputStream(cskt.getOutputStream());
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the text on client side");
        String msg=sc.nextLine();
        outToClient.writeBytes(msg+"\n");
        }
    }
    catch(Exception e)

    {
        System.out.println(e.getMessage());
    }
    }


}
class ReadHandler implements Runnable
{
    Socket cskt;

public ReadHandler(Socket skt)
    {
        cskt=skt;
    }
    public void run()
    {
        try{
            while(true)
            {

            
                BufferedReader inFromClient=new BufferedReader(new InputStreamReader(cskt.getInputStream()));
                String msg=inFromClient.readLine();
                if(msg==null)
                    continue;
                else{
                    System.out.println("from server:"+msg);
                }
        
        }
    }
    catch(Exception e)

    {
        System.out.println(e.getMessage());
    }
    }
}

public class TCClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket=new Socket("localhost",17541);
        WriteHandler wH=new WriteHandler(clientSocket);
        ReadHandler rH=new ReadHandler(clientSocket);
        Thread t1=new Thread(wH);
        Thread t2=new Thread(rH);
        
        t2.start();
        t1.start();
    }
}