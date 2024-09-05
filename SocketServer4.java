import java.util.*;
import java.net.*;
import java.io.*;

public class SocketServer4 {
    private static int count(String n) {
        int count = 0;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '1') {
                count++;
            }
        }
        return count;

    }

    private static boolean isBinary(String n)
    {
        int flag=0;
        for(int i=0;i<n.length();i++)
        {
            if(n.charAt(i)!='0' && n.charAt(i)!='1')
            {
                flag=1;
                break;
            }

            
        }
        return flag==0;
    }

    

    public static String RemParity(String n) {
        int c = count(n);
        if(isBinary(n)==false)
        {
           // System.err.println("Entered binary case");
            return "Error data receieved,please Re-Enter!";
        }
        else if (c % 2 == 0) {
            return n.substring(0, n.length() - 1);
        } else
            return "Error data received,please Re-Enter!";
    }

    

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(18034);
        System.out.println("Server is running");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Read the first binary number from the client
            while(true){
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
            String num1 = inFromClient.readLine();
            System.out.println("Received  binary number from client: " + num1);
            String dataWithoutParity = RemParity(num1);

            if (dataWithoutParity.endsWith("Error data received,please Re-Enter!"))
                continue;
            else {
                System.out.println("Received final result: " + dataWithoutParity);
                outToClient.writeBytes("successful received!" + '\n');
                
            }
        }
        }
    }

}