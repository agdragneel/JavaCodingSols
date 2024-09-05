import java.net.*;
import java.util.*;

import java.io.*;

public class BitStuffServer {

    static String unstuffBits(String data, String flag) {
        String flagRemoved = data.substring(8);
        flagRemoved = flagRemoved.substring(0, flagRemoved.length() - 8);
        String newData = "";
        int oneCount = 0;
        for (int i = 0; i < flagRemoved.length(); i++) {
            if (flagRemoved.charAt(i) == '1') {
                oneCount++;
            } else {
                oneCount = 0;
            }
            newData = newData + flagRemoved.charAt(i);
            if (oneCount == 5) {
                i++;
                oneCount = 0;
            }
        }
        return newData;

    }

    public static void main(String[] args) throws IOException {
        ServerSocket skt = new ServerSocket(12345);
        Scanner sc = new Scanner(System.in);
        Socket clientSocket = skt.accept();
        DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String flag = "01111110";
        String stuffedData = inFromClient.readLine();
        System.out.println("Stuffed data received:" + stuffedData);
        String unStuffedData = unstuffBits(stuffedData, flag);
        System.out.println("Unstuffed Data:"+unStuffedData);

    }
}
