import java.io.*;
import java.net.*;
public class BySS {
    static String unstuffbits(String data)
    {
        data=data.substring(1,data.length()-1);
        int count=0;
        String newdata="";
        for(int i=0;i<data.length()-1;i++)
        {
            if((data.charAt(i)=='E')&&(data.charAt(i+1)=='E'))
            {
                newdata+='E';
                i++;
            }
            else if((data.charAt(i)=='E')&&(data.charAt(i+1)=='F'))
            {
                newdata+='F';
                i++;
            }
            else
            {
            newdata+=data.charAt(i); 
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
    

