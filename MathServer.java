import java.io.*;
import java.net.*;

public class MathServer {
    public static void main(String[] args) throws Exception {
        ServerSocket skt = new ServerSocket(17124);
        while (true) {
            Socket connectionSocket = skt.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            while (true) {
                String menu = "Enter your choice: 1. Add, 2. Subtract,3. Multiply\n";
                outToClient.writeBytes(menu);
                int clientOption = Integer.parseInt(inFromClient.readLine());
                System.out.println("Option Received from Client:" + clientOption);
                outToClient.writeBytes("Enter operands." + '\n');
                int op1 = Integer.parseInt(inFromClient.readLine());
                int op2 = Integer.parseInt(inFromClient.readLine());
                System.out.println("Operands received from Client:"+op1+" "+op2);
                String output;
                switch (clientOption) {
                    case 1:
                        output = String.valueOf(op1 + op2);
                        break;
                    case 2:
                        output = String.valueOf(op1 - op2);
                        break;
                    case 3:
                        output = String.valueOf(op1 * op2);
                        break;
                    default:
                        output = "Invalid option chosen for operator.";

                }

                outToClient.writeBytes(output + '\n');
                System.out.println("Operation Completed Successfully.");
            }
        }
        // skt.close();
    }
}
