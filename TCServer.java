
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class WriteHandler implements Runnable {
    private Socket clientSocket;

    public WriteHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter the text on server side:");
                String msg = scanner.nextLine();
                outToClient.writeBytes(msg + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error in WriteHandler: " + e.getMessage());
        }
    }
}

class ReadHandler implements Runnable {
    private Socket clientSocket;

    public ReadHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String msg;
            while ((msg = inFromClient.readLine()) != null) {
                System.out.println("From client: " + msg);
            }
        } catch (IOException e) {
            System.out.println("Error in ReadHandler: " + e.getMessage());
        }
    }
}

public class TCServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(17541);
             Socket clientSocket = serverSocket.accept()) {
            System.out.println("Client connected.");

            WriteHandler writeHandler = new WriteHandler(clientSocket);
            ReadHandler readHandler = new ReadHandler(clientSocket);

            Thread writeThread = new Thread(writeHandler);
            Thread readThread = new Thread(readHandler);

            writeThread.start();
            readThread.start();

            // Wait for both threads to finish
            writeThread.join();
            readThread.join();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error in ChatServer: " + e.getMessage());
        }
    }
}