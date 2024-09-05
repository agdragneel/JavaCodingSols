
import java.io.*;
import java.net.*;
public class UDPS
{
    public static void main(String[] args)throws IOException {
        DatagramSocket dSocket=new DatagramSocket(15001);
       
        byte[] buffer=new byte[2048];
        DatagramPacket packet =new DatagramPacket(buffer,buffer.length);
        dSocket.receive(packet);
        byte[] b1=packet.getData();
        String msg=new String(b1);
        String msg1="";
        for(int i=msg.length()-1;i>=0;i--)
        {
            msg1+=msg.charAt(i);
        }
        DatagramPacket packet1=new DatagramPacket(msg1.getBytes(),msg1.getBytes().length,packet.getAddress(),packet.getPort());
        dSocket.send(packet1);
    }
}
