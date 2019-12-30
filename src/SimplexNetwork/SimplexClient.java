package SimplexNetwork;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimplexClient {
	public static void main(String[] args) {
		System.out.println("Client received message.");
		
		try(
				Socket socket = new Socket("192.168.44.154", 34561);
				InputStream in = socket.getInputStream();
				
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				){
			String data = br.readLine();
			System.out.println("Data: "+data);
		}catch(Exception e) {
			System.out.println("Exception.");
		}
	}
}
