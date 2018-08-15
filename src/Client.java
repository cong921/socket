import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception {
		Socket client = new Socket("192.168.1.10", 8088);
		OutputStream outputStream = client.getOutputStream();
		String str = new BufferedReader(new InputStreamReader(System.in)).readLine();		
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		System.out.println(str);
		dos.writeUTF(str);
		client.close();
	}
}
