package PropertyDuplexNetWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class SongClient {
	public static void main(String[] args) {
		
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(100);
		
		ses.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try(
						Socket socket  = new Socket("localhost", 12345);
						
						InputStreamReader in = new InputStreamReader(socket.getInputStream());
						BufferedReader bufRead = new BufferedReader(in);
						
						OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
						BufferedWriter bufWriter = new BufferedWriter(out);
						
						//Scanner input = new Scanner(System.in);
						){
					//System.out.println("Input song Name");
					//String songName = input.next();
					//System.out.println(songName);
					
					bufWriter.write("abc");
					bufWriter.newLine();
					bufWriter.flush();
					
					String songWord = bufRead.readLine();
					System.out.println(songWord);
				}catch(Exception e) {e.printStackTrace();}
			}
		}, 10, 10000, TimeUnit.MILLISECONDS);
	}
}
