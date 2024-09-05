import java.util.*;
import java.io.*;
import java.net.*;

public class ConcurrentClient {
    public static void main(String[] args) throws IOException {
        
        int serverPort=17541;
        Socket skt=new Socket("localhost",serverPort);

        BufferedReader inFromServer=new BufferedReader(new InputStreamReader((skt.getInputStream())));
        String message;
        while((message=inFromServer.readLine())!=null)
        {
            System.out.println("Message from server:"+message);
        }
        skt.close();
    }
}
