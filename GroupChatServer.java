import java.io.*;
import java.net.*;
import java.util.HashMap;

public class GroupChatServer {
    private static final int PORT = 8080;
    private static HashMap<Integer, DataOutputStream> clientWriters = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getPort());

                DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());
                clientWriters.put(clientSocket.getPort(), writer);

                ClientHandler handler = new ClientHandler(clientSocket);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader reader;

        public ClientHandler(Socket clientSocket) {
            try {
                this.clientSocket = clientSocket;
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("Message from client " + clientSocket.getPort() + ": " + message);
                    broadcast(clientSocket.getPort(), message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void broadcast(int senderPort, String message) {
            for (int port : clientWriters.keySet()) {
                if (port != senderPort) {
                    DataOutputStream writer = clientWriters.get(port);
                    try {
                        writer.writeBytes("Client " + senderPort + ": " + message + "\n");
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
