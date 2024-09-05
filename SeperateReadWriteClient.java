
import java.io.*;
import java.net.*;

public class SeperateReadWriteClient {
    public static void main(String[] args) throws IOException {
        Socket skt=new Socket("localhost",19000);
        BufferedReader inFromServer=new BufferedReader(new InputStreamReader(skt.getInputStream()));
        DataOutputStream outFromServer=new DataOutputStream(skt.getOutputStream());
        outFromServer.writeBytes("Connection is established with server."+"\n");
        System.out.println("Message received from Server:"+inFromServer.readLine());
        skt.close();
    }
}
