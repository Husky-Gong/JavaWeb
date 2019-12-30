package DuplexNetWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * 1. send a request
 * 		set streams
 * 		keep sending messages to server
 * 			set a count clock to send message
 * 2. receive message from the server
 */
public class songClient {
	public static void main(String[] args) {
		System.out.println("Start searching songs......");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String songName = input.next();

		ScheduledExecutorService ses = Executors.newScheduledThreadPool(100);

		ses.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try (Socket socket = new Socket("localhost", 12345);

						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
					bw.write(songName);
					bw.newLine();
					bw.flush();

					String lyrics = br.readLine();
					System.out.println(lyrics);
				} catch (Exception e) {
					System.out.println("Exception!!");
				}
			}
		}, 10, 10, TimeUnit.MILLISECONDS);
	}
}
