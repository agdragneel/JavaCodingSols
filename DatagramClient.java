import java.io.*;
import java.net.*;
public class DatagramClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket dSocket=new DatagramSocket();
        InetAddress add=InetAddress.getByName("localhost");
        byte[] buffer=new byte[2048];
        DatagramPacket rPacket=new DatagramPacket(buffer,buffer.length);
        DatagramPacket sPacket;

        String msg="Sent a packet to the server.";
        byte[] data=msg.getBytes();
        sPacket=new DatagramPacket(data,data.length,add,50002);
        dSocket.send(sPacket);

        dSocket.receive(rPacket);
        System.out.println("Message received from Server:"+new String(rPacket.getData()).trim());
        dSocket.close();
    }
}
