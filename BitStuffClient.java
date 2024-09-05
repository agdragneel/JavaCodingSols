import java.net.*;
import java.util.*;
import java.io.*;

public class BitStuffClient {

    static String stuffBits(String data, String flag) {
        String newData = "";
        int oneCount = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '1') {
                oneCount++;

            } else {
                oneCount = 0;
            }
            newData += data.charAt(i);
            if (oneCount == 5) {
                newData += '0';
                oneCount = 0;
            }
        }
        newData = flag + newData + flag;
        return newData;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket clientSocket = new Socket("localhost", 12345);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String flag = "01111110";
        System.out.println("Enter data as stream of bits(1s or 0s)");
        String data = sc.next();
        // Bit Stuffing
        data = stuffBits(data, flag);
        outToServer.writeBytes(data + "\n");
        System.out.println("Stuffed Data:" + data);
        clientSocket.close();
    }
}
