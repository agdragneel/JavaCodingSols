import java.net.*;
import java.util.*;
import java.io.*;

public class ByteStuffClient {

    static String stuffBytes(String data, char flag,char esc) {
       String newData="";
       for(int i=0;i<data.length();i++)
       {
        if(data.charAt(i)==flag || data.charAt(i)==esc)
        {
            newData+=esc;
        }
        newData+=data.charAt(i);
       }
       newData='F'+newData+'F';
       return newData;

    }



    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket clientSocket = new Socket("localhost", 12345);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        char flag = 'F';
        char esc='E';
        System.out.println("Enter data");
        String data = sc.next();
        // Bit Stuffing
        data = stuffBytes(data, flag,esc);
        outToServer.writeBytes(data + "\n");
        System.out.println("Stuffed Data:" + data);
        clientSocket.close();
    }
}
