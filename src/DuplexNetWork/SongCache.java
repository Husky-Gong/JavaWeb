package DuplexNetWork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Map;

public class SongCache {
	
	private static Map<String, String> songTable = new Hashtable<>();
	
	static {
		File file = new File("songs");
		
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if(pathname.getName().endsWith("DS_Store")) return false;
				else return true;
			}
		};
		
		try {
				if(!file.exists()) file.mkdir();
				
				File fullSongFiles[] = file.listFiles(filter);
				for(File songFile: fullSongFiles) {
					String songName = songFile.getName();
					String lyrics = getLyrics(songFile);
					songTable.put(songName, lyrics);}}
		catch(Exception e) {
			System.out.println("Error!");
		}
	}
	
	SongCache(){}
	
	public static String getSong(String songName) {
		return songTable.get(songName);
	}
	
	private static String getLyrics(File songFile) {
		System.out.println("Start searching songs....");
		StringBuilder sb = new StringBuilder(20);
		String lyrics;
		
		try(
				FileReader fr = new FileReader(songFile);
				BufferedReader bufReader = new BufferedReader(fr);
				){
			while((lyrics = bufReader.readLine()) != null) {
				sb.append(lyrics);
			}
		}catch(Exception e) {
			System.out.println("Error!");
		}
		return sb.toString();
	}
}
