import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8088);
		Socket socket = serverSocket.accept();
		System.out.println("有客服端访问");
		while(true) {
			InputStream inputStream = socket.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			List<Byte> list = new ArrayList<Byte>();
			list.add(dataInputStream.readByte());
			byte[] b=new byte[list.size()];
			for(int i=0;i<list.size();i++) {
				b[i]=list.get(i);
			}
			System.out.println(new String(b));
			
		}
	}
}