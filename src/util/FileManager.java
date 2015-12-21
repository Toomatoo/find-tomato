package util;

import java.io.File;
public class FileManager {
	String[] imageParsed;
	
	String[] descParsed;
	
	public FileManager(String rootDirectory) {
		readImageParsed(rootDirectory + "/conll");
		readDescParsed(rootDirectory + "/desc-parsed");
	}
	
	private void readImageParsed(String imageDirectory) {
		File file = new File(imageDirectory);
		imageParsed = file.list();
	}
	
	private void readDescParsed(String descDirectory) {
		File file = new File(descDirectory);
		descParsed = file.list();
	}
}
