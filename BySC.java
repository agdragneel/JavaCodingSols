import java.io.*;
import java.net.*;
import java.util.*;
public class BySC {
    static String stuffbytes(String data)
{
    int count=0;
    String newdata="";
    for(int i=0;i<data.length();i++)
    {
        if(data.charAt(i)=='E'||data.charAt(i)=='F')
        {
            newdata+='E';
        }
        newdata+=data.charAt(i);
        
    }
    newdata='F'+newdata+'F';
    return newdata;
}
    public static void main(String args[])throws IOException{
        Socket connectionSocket=new Socket("localhost",15001);
        BufferedReader inFromServer=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToServer=new DataOutputStream(connectionSocket.getOutputStream());
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string");
        String data=sc.next();
        String data1=stuffbytes(data);
        outToServer.writeBytes(data1+'\n');
        System.out.println(data1);
    }
}
