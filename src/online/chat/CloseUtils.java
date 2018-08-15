package online.chat;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtils {
	public static void closeAll(Closeable... io) {
		for (Closeable closeable : io) {
			if(closeable!=null) {
				try {
					closeable.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
