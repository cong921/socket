package online.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
 
public class Client1 {
	public static void main(String[] args) throws IOException {
		//�ͻ��������뱾����20006�˿ڽ���TCP���� 
		Socket client = new Socket("localhost", 20006);
		client.setSoTimeout(10000);
		//��ȡ�������� 
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		//��ȡSocket��������������������ݵ������  
		PrintStream out = new PrintStream(client.getOutputStream());
		//��ȡSocket�����������������մӷ���˷��͹���������  
		BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream()));
		boolean flag = true;
		while(flag){
			System.out.print("������Ϣ��");
			String str = input.readLine();
			//�������ݵ������  
			out.println(str);
			if("bye".equals(str)){
				flag = false;
			}else{
				try{
					//�ӷ������˽��������и�ʱ�����ƣ�ϵͳ���裬Ҳ�����Լ����ã������������ʱ�䣬����׳����쳣
					String echo = buf.readLine();
					System.out.println(echo);
				}catch(SocketTimeoutException e){
					System.out.println("Time out, No response");
				}
			}
		}
		input.close();
		if(client != null){
	        //������캯�������������ӣ���ر��׽��֣����û�н��������ӣ���Ȼ���ùر�
			client.close();	//ֻ�ر�socket������������������Ҳ�ᱻ�ر�
		}
	}
}