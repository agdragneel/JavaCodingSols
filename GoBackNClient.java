import java.io.*;
import java.net.*;
import java.util.*;

public class GoBackNClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 50123);

        System.out.println("Enter number of frames.");
        Scanner sc = new Scanner(System.in);
        int noOfFrames = sc.nextInt();
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(String.valueOf(noOfFrames) + "\n");

        for (int i = 0; i < noOfFrames;) {
            System.out.println("Frame number to be sent:");
            int index = sc.nextInt();
            System.out.println("Enter Content to be sent: " + index);
            String data = sc.next();
            outToServer.writeBytes(String.valueOf(index) + "\n");
            outToServer.writeBytes(data + "\n");
            String msg = inFromServer.readLine();

            if (msg.equals("Incorrect Frame Received.")) {
                System.out.println("Resending Correct Frame");
            } else {
                i++;
            }
        }
        System.out.println("All frames sent. Succesfully transmitted.");
        clientSocket.close();
    }
}
