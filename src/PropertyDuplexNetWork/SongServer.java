package PropertyDuplexNetWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;


public class SongServer {
	public static void main(String[] args) {
		try(
				ServerSocket ss = new ServerSocket(12345);
				){
			ThreadPoolExecutor threadPool = ThreadPool.createThreadPool();
			
			SongServer server = new SongServer();
			
			server.handleRequest(threadPool, ss);
		}catch(Exception e) {
			System.out.println("Error!");
		}
	}

	private void handleRequest(ThreadPoolExecutor pool, ServerSocket ss) throws IOException {
		while(true) {
			Socket socket = ss.accept();
			pool.submit(new Runnable() {
				@Override
				public void run() {
					try(
							InputStreamReader in = new InputStreamReader(socket.getInputStream());
							BufferedReader br = new BufferedReader(in);
							
							OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
							BufferedWriter bw = new BufferedWriter(out);
							){
						String requestMsg = br.readLine();
						
						System.out.println(requestMsg);
						
						System.out.println(requestMsg.equals("abc"));
						
						String lyrics = SongCache.getSong(requestMsg);
						System.out.println(lyrics);
						
						if(lyrics == null || lyrics.length() == 0) {
							lyrics = "No such a song...";
						}
						System.out.println(lyrics);
						bw.write(lyrics);
						bw.newLine();
						bw.flush();
						
					}catch(Exception e) {
						System.out.println("Exception!");
					}
				}
			});
		}
		
	}
}
