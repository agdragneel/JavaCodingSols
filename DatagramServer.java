
import java.net.*;
import java.io.*;
public class DatagramServer {
    public static void main(String[] args)throws IOException {
        DatagramSocket dSocket=new DatagramSocket(50002);
        byte buffer[]=new byte[2048];
        DatagramPacket rPacket=new DatagramPacket(buffer, buffer.length);
        DatagramPacket sPacket;

        dSocket.receive(rPacket);
        System.out.println("Message received from client:"+new String(rPacket.getData()).trim());
        InetAddress add=rPacket.getAddress();
        int port=rPacket.getPort();

        String msg="Successfully receieved data.";
        byte[] data=msg.getBytes();
        sPacket=new DatagramPacket(data,data.length,add,port);
        dSocket.send(sPacket);
        dSocket.close();
    }
}
