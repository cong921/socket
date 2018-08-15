package online.chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
//		InetAddress localHost = InetAddress.getLocalHost();
//		System.out.println(localHost.getHostAddress());
		Socket client = new Socket("localhost", 9999);
		System.out.println("请输入聊天信息:");
		
		//发送
		/*DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF(msg);
		dos.flush();*/
		new Thread(new Send(client)).start();
		//接收
		/*DataInputStream dis = new DataInputStream(client.getInputStream());
		String readUTF = dis.readUTF();
		System.out.println(readUTF);*/
		new Thread(new Receive(client)).start();
	}
}
