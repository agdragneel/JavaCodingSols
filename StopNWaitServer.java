import java.io.*;
import java.net.*;

public class StopNWaitServer {
    public static void main(String[] args) throws IOException {
        ServerSocket skt = new ServerSocket(50123);
        Socket clientSocket = skt.accept();
        System.out.println("Connection established.");
        DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        int noOfFrames = Integer.parseInt(inFromClient.readLine());
        String receivedFrames[] = new String[noOfFrames];
        for (int i = 0; i < noOfFrames;i++) {
            receivedFrames[i]=inFromClient.readLine();
            System.out.println("Frame "+i+"with content "+receivedFrames[i]+" was received.");
            outToClient.writeBytes("ACKNOWLEDGED"+"\n");
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
