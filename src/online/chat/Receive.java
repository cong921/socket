package online.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
	private DataInputStream dis;
	private boolean isRunning=true;
	
	public Receive() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Receive( Socket client) {
		super();
		try {
			this.dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isRunning=false;
			CloseUtils.closeAll(dis);
			
		}
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning) {
			String msg = "";
			try {
				msg = dis.readUTF();
				System.out.println("Ω” ’µΩ:"+msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				isRunning=false;
				CloseUtils.closeAll(dis);
			}
			if(null!=msg&&!"".equals(msg)) {
				isRunning=false;
			}
		}
	}

}
