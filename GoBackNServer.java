import java.io.*;
import java.net.*;

public class GoBackNServer {
    public static void main(String[] args) throws IOException {
        ServerSocket skt = new ServerSocket(50123);
        Socket clientSocket = skt.accept();
        System.out.println("Connection established.");
        DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        int noOfFrames = Integer.parseInt(inFromClient.readLine());
        String receivedFrames[] = new String[noOfFrames];
        for (int i = 0; i < noOfFrames;) {
            int index = Integer.parseInt(inFromClient.readLine());
            String data = inFromClient.readLine();
            if (index == i) {
                outToClient.writeBytes("Acknowledged"+"\n");
                System.out.println("Frame "+i+" received.");
                receivedFrames[i] = data;
                i++;
            } else {
                outToClient.writeBytes("Incorrect Frame Received." + "\n");
                System.out.println("Incorrect Frame Number");
            }
        }
        System.out.println("All frames received. Succesfully transmitted. Frames are:");
        for (int i = 0; i < noOfFrames; i++) {
            System.out.print("[" + receivedFrames[i] + "] ");
        }
        System.out.println();
        clientSocket.close();
        skt.close();
    }
}
