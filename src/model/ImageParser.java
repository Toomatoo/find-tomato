package model;

import java.io.*;
import java.util.ArrayList;

public class ImageParser {
	// pair+image
	ArrayList<String> relationPair = new ArrayList<String>();

	public ImageParser() throws IOException {
		treePair();
	}

	/******************************************************************************************************************/
	public void simplePair() throws IOException {
		String rootDirectory = "/Users/sliu/Documents/JavaWorkspace/Toomatoo/web/content/data/conll-reln/";
		String[] fileList = (new File(rootDirectory)).list();
		for(int i=0; i<fileList.length; i++) {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rootDirectory + fileList[i])));

			String str = "";
			while((str=br.readLine()) != null) {
				if(str.equals(""))
					continue;

				str += "+" + (((fileList[i].split("\\."))[0]).split("-"))[0];
				relationPair.add(str);
			}
			br.close();
		}
	}
	/******************************************************************************************************************/
	public void treePair() throws IOException {
		//String rootDirectory = "/Users/sliu/Documents/JavaWorkspace/Toomatoo/web/content/data/conll-reln1/";
		String rootDirectory = "/Users/sliu/Documents/JavaWorkspace/Toomatoo/web/content/data/conll-reln2/";

		String[] fileList = (new File(rootDirectory)).list();
		for(int i=0; i<fileList.length; i++) {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rootDirectory + fileList[i])));

			String str = "";
			while((str=br.readLine()) != null) {
				if(str.equals(""))
					continue;

				str += "+" + (fileList[i].split("\\."))[0];
				relationPair.add(str);
			}
			br.close();
		}
	}
	/******************************************************************************************************************/

	public ArrayList<String> RelationPair()  {
		return relationPair;
	}
}
