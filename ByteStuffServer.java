import java.net.*;
import java.util.*;

import java.io.*;

public class ByteStuffServer {

    static String unstuffBytes(String data, char flag,char esc) {
            String newData="";
            data=data.substring(1,data.length()-1);
            for(int i=0;i<data.length();i++)
            {
                if(data.charAt(i)=='E' && data.charAt(i+1)=='E')
                {
                    newData+='E';
                    i++;
                }
                else if(data.charAt(i)=='E' && data.charAt(i+1)=='F')
                {
                    newData+='F';
                    i++;
                }
                else
                {
                    newData+=data.charAt(i);
                }
            }
            
            return newData;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket skt = new ServerSocket(12345);
        Scanner sc = new Scanner(System.in);
        Socket clientSocket = skt.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        char flag = 'F';
        char esc='E';
        String stuffedData = inFromClient.readLine();
        System.out.println("Stuffed data received:" + stuffedData);
        String unStuffedData = unstuffBytes(stuffedData, flag,esc);
        System.out.println("Unstuffed Data:"+unStuffedData);

    }
}
