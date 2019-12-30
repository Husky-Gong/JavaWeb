package HalpDuplexNetWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HalfDuplexNetServer {
	public static void main(String[] args) {
		System.out.println("Half-duplex net server:::");
		
		try(
				ServerSocket serverSocket = new ServerSocket(12346);
				Socket socket = serverSocket.accept();
				
				OutputStream out = socket.getOutputStream();
				InputStream in = socket.getInputStream();
				
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				){
			String clientData = br.readLine();
			
			LocalDateTime ldt = LocalDateTime.now().withNano(0);
			DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
			String currentTime = ldt.format(dtm);
			
			if(clientData.equals("getDateTime")) {
				bw.write(currentTime);
			}
			else {
				bw.write("Invalid Input.");
			}
			bw.newLine();
			bw.flush();
			
		}catch(Exception e) {
			System.out.println("Exception.");
		}
	}
}
