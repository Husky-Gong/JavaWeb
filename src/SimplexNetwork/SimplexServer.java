package SimplexNetwork;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimplexServer {
	public static void main(String[] args) {
		System.out.println("Simplex network starts:::");
		try(
				ServerSocket serverSocket = new ServerSocket(12345);
				
				Socket socket = serverSocket.accept();
				
				// server will set an output stream to the client and send data to the client.
				OutputStream out = socket.getOutputStream();
				BufferedWriter bufWrite = new BufferedWriter(new OutputStreamWriter(out));
				){
		LocalDateTime ldt = LocalDateTime.now().withNano(0);
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(ldt);
		bufWrite.write(currentTime);
		bufWrite.newLine();
		bufWrite.flush();
		}catch(Exception e) {
			System.out.println("Exception.");
		}
	}
}
