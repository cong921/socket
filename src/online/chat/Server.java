package online.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(9999);
		while(true) {
			Socket socket = server.accept();
			MyChanle myChanle = new Server().new MyChanle(socket);
			new Thread(myChanle).start();
		}
		
		
	}
	private class MyChanle implements Runnable {
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning=true;
		
		public MyChanle(Socket server) {
			try {
				this.dis=new DataInputStream(server.getInputStream());
				this.dos=new DataOutputStream(server.getOutputStream());
			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
				isRunning=false;
				CloseUtils.closeAll(dis,dos);
			}
		}
		public String receive() {
			String msg="";
			try {
				msg=dis.readUTF();
				System.out.println("服务器收到:"+msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isRunning=false;
				CloseUtils.closeAll(dis,dos);
			}
			return msg;
		}
		public void send(String msg) {
			if (msg!=null&&!"".equals(msg)) {
				try {
					dos.writeUTF(msg);
					dos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					isRunning=false;
					CloseUtils.closeAll(dis,dos);
				}
			}
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning) {
				send(receive());
			}
		}
	}
}
