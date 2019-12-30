package DuplexNetWork;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	private static ThreadPoolExecutor pool= null;
	
	public static ThreadPoolExecutor createThreadPool() {
		if(pool == null) {
			pool = new ThreadPoolExecutor(100, 100,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		}
		return pool;
	}
}
