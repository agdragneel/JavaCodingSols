import java.util.*;
import java.net.*;
import java.io.*;

public class SocketClient4 {
    private static int count(String n) {
        int count = 0;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '1') {
                count++;
            }
        }
        return count;

    }

    public static String parity(String n1) {
        int c = count(n1);
        if (c % 2 == 0) {
            return n1 + "0";
        } else
            return n1 + "1";
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket c = new Socket("localhost", 18034);
        System.out.println("Client is connected to the server");
        while(true){
        BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
        DataOutputStream out = new DataOutputStream(c.getOutputStream());
        System.out.println("Enter the binary no");
        String b = sc.nextLine();
        String o = parity(b);
        out.writeBytes(o + '\n');
        String a = in.readLine();
        System.out.println(a);
        }
       // c.close();

    }

}