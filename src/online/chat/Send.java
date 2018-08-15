package online.chat;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {
	private DataOutputStream dos;
	private String msg;
	private boolean isRunning=true;
	public Send() {
		super();
	}

	public Send(Socket socket)  {
		super();
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String console=br.readLine();
			if(null!=console&&!"".equals(console)) {
				msg=console;
			}
			this.dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			isRunning=false;
			CloseUtils.closeAll(dos);
		}
		
	}

	@Override
	public void run() {
		while(isRunning) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
				CloseUtils.closeAll(dos);
			}
	}
	}
	
}

