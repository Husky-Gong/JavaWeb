package DuplexNetWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;




public class songServer {
	public static void main(String[] args) {
		try(
				ServerSocket ss = new ServerSocket(12345);
				
				){
			ThreadPoolExecutor pool = ThreadPool.createThreadPool();
			
			songServer server = new songServer();
			
			server.handlerRequest(pool, ss);
		}catch(Exception e) {
			System.out.println("Error!");
		}
	}
	
	private void handlerRequest(ThreadPoolExecutor pool, ServerSocket ss) throws IOException {
		// Listen to client
		while(true) {
			Socket socket =  ss.accept();
			pool.submit(new Runnable() {
				@Override
				public void run() {
					try(	BufferedReader br = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
							
							BufferedWriter bw = new BufferedWriter(
									new OutputStreamWriter(socket.getOutputStream()))
							){
						String songName = br.readLine();
						String lyrics = SongCache.getSong(songName);
						
						if(lyrics == null || lyrics.length() == 0) {
							System.out.print("Song not exists!");
						}
						else {
							bw.write(lyrics);
							bw.newLine();
							bw.flush();
						}
					}catch(Exception e) {
						System.out.println("Exception!");
					}
				}
				
			});
		}
	}
}
