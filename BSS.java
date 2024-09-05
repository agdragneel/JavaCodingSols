import java.io.*;
import java.net.*;

public class BSS {
    static String unstuffbits(String data)
{
    data=data.substring(8,data.length()-8);
    int count=0;
    String newdata="";
    for(int i=0;i<data.length();i++)
    {
        if(data.charAt(i)=='1')
        {
            count++;
        }
        newdata+=data.charAt(i);  
        if(count==5)
        {
            i++;
            count=0;
        }
    }
    return newdata;
}
    public static void main(String args[])throws IOException{
        ServerSocket skt=new ServerSocket(15001);
        Socket connectionSocket=skt.accept();
        BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());
        String data=inFromClient.readLine();
        String data1=unstuffbits(data);
        System.out.println(data1);
}
}
