import java.io.*;
import java.net.*;
import java.util.*;

public class BSC {
    static String stuffbits(String data,String flag)
{
    int count=0;
    String newdata="";
    for(int i=0;i<data.length();i++)
    {
        if(data.charAt(i)=='1')
        {
            count++;
        }
        else
        {
            count=0;  
        }
        newdata+=data.charAt(i);
        if(count==5)
        {
            newdata+='0';
            count=0;
        }
        
    }
    newdata=flag+newdata+flag;
    return newdata;
}
    public static void main(String args[])throws IOException{
        Socket connectionSocket=new Socket("localhost",15001);
        BufferedReader inFromServer=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToServer=new DataOutputStream(connectionSocket.getOutputStream());
        Scanner sc=new Scanner(System.in);
        String flag="01111110";
        System.out.println("Enter the string");
        String data=sc.next();
        String data1=stuffbits(data,flag);
        outToServer.writeBytes(data1+'\n');
        System.out.println(data1);
    }
}
