import java.io.*;
import java.net.*;
import java.util.*;

public class StopNWaitClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 50123);

        System.out.println("Enter number of frames.");
        Scanner sc = new Scanner(System.in);
        int noOfFrames = sc.nextInt();
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(String.valueOf(noOfFrames) + "\n");

        for (int i = 0; i < noOfFrames;i++) {
            System.out.println("Enter data of frame");
            String data = sc.next();
            outToServer.writeBytes(data+"\n");
            System.out.println("Frame "+i+" sent with content "+data);
            String rec=inFromServer.readLine();
            if(rec.equals("ACKNOWLEDGED"))
            {
                System.out.println("Acknowledgement received from receiver");
            }
        }
        System.out.println("All frames sent. Succesfully transmitted.");
        clientSocket.close();
    }
}
