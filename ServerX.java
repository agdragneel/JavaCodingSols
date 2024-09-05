

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerX {
    public static void main(String[] args) throws IOException {
        
    
        ServerSocket skt=new ServerSocket(13000);
        Socket cskt=skt.accept();
        BufferedReader inFromClient=new BufferedReader(new InputStreamReader(cskt.getInputStream()));
        int n=Integer.parseInt(inFromClient.readLine());
        DataOutputStream outToClient=new DataOutputStream(cskt.getOutputStream());
        int i=1;
        while(n!=0)
        {
            String fn=inFromClient.readLine();
            if(Integer.parseInt(fn)!=i)
            {
                System.out.println("Incorrect frame recvd");
                outToClient.writeBytes("Resending correct frame"+"\n");
            }
            else{


                String msg=inFromClient.readLine();
                outToClient.writeBytes("ok"+"\n");
               
            if(msg!=null)
            {
                
                System.out.println("frame "+i+" received string "+msg);
                


            } 
            n--;
            i++;
        }


        }
    }
}