package PropertyDuplexNetWork;

import java.io.InputStream;
import java.util.Properties;


public class SongCache {
	private static Properties props = new Properties();
	
	static {
		try(
				
				InputStream in = Thread.currentThread()
									.getContextClassLoader()
									.getResourceAsStream("Song.properties");
				){
			System.out.println("Test...");
			if(in == null) throw new RuntimeException("Error!!");
			props.load(in);
		}catch(Exception e) {
			System.out.println("no such file...");
		}
	}
	
	public static String getSong(String key) {
		return props.getProperty("abc");
	}
}
