
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
public class ClientX {
    public static void main(String[] args) throws IOException{

        Socket cskt=new Socket("localhost",13000);
        BufferedReader inFromServer=new BufferedReader(new InputStreamReader(cskt.getInputStream()));
    
        DataOutputStream outToServer=new DataOutputStream(cskt.getOutputStream());
        System.out.println("Enter no of frames");
        Scanner sc=new Scanner(System.in);
        String n=sc.nextLine();
        outToServer.writeBytes(n+"\n");
        int n1=Integer.parseInt(n);
        int i=0;
        while(n1!=0)
        {
            
            System.out.println("Frame no to be sent");
            String str=sc.nextLine();
            outToServer.writeBytes(str+"\n");
            System.out.println("content to be sent");
            String msg=sc.nextLine();
            outToServer.writeBytes(msg+"\n");
            String msg1=inFromServer.readLine();
            if(msg1.equals("ok"))
            {
                n1--;
                i++;
            }
            else
            {
                
                System.out.println(msg1);
            }
           

        }
    }
}