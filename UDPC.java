import java.io.*;
import java.util.*;
import java.net.*;
public class UDPC {
    public static void main(String[] args)throws IOException {
        DatagramSocket dSocket=new DatagramSocket();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string");
        String data=sc.next();
        byte[] b=data.getBytes();
        DatagramPacket packet=new DatagramPacket(b,b.length,InetAddress.getByName("localhost"),15001);
        dSocket.send(packet);
        byte[] buffer=new byte[2048];
        DatagramPacket packet1=new DatagramPacket(buffer,buffer.length);
        dSocket.receive(packet1);
        byte[] b1=packet1.getData();
        String msg=new String(b1);
        System.out.println(msg);
    }
}
