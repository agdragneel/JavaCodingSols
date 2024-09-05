import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GroupChatClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server...");

            Thread readerThread = new Thread(new MessageReader(socket));
            readerThread.start();

            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            String message;
            while (true) {
                message = scanner.nextLine();
                writer.writeBytes(message + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class MessageReader implements Runnable {
        private Socket socket;

        public MessageReader(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
