package HalpDuplexNetWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HalfDuplexNetClient {
	public static void main(String[] args) {
		System.out.println("Client::");
		try(
				Socket socket = new Socket("localhost",12346);
				
				InputStream in = socket.getInputStream();
				BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));
				
				OutputStream out = socket.getOutputStream();
				BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(out));
				){
			
			bufWriter.write("getDateTime");
			bufWriter.newLine();
			bufWriter.flush();
			
			String clientData = bufRead.readLine();
			System.out.println(clientData);
			
		}catch(Exception e) {
			System.out.println("Exception!");
		}
	}
}
