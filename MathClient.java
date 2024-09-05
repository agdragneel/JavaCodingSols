import java.io.*;
import java.net.*;
import java.util.*;
public class MathClient {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        Socket connectionSocket=new Socket("localhost",17124);
        BufferedReader inFromServer=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToServer=new DataOutputStream(connectionSocket.getOutputStream());
        while(true)
        {
            System.out.println("Message from Server:"+inFromServer.readLine());
            String ch=String.valueOf(sc.nextInt());
            outToServer.writeBytes(ch+"\n");
            System.out.println("Message from Server:"+inFromServer.readLine());
            String op1=sc.next();
            String op2=sc.next();
            outToServer.writeBytes((op1)+"\n");
            outToServer.writeBytes((op2)+"\n");
            System.out.println("Message from Server:"+inFromServer.readLine());
            System.out.println("Operation Completed Successfully.");
        }
        // connectionSocket.close();
    }
}
