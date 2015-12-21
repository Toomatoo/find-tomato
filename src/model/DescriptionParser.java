package model;

import java.io.*;
import java.util.ArrayList;

public class DescriptionParser {
	// pair+image
	public ArrayList<String> relationPair = new ArrayList<String>();

	public DescriptionParser() throws IOException {
		String rootDirectory = "/Users/sliu/Documents/JavaWorkspace/Toomatoo/web/content/data/desc-parsed/";
		String[] fileList = (new File(rootDirectory)).list();
		for(int i=0; i<fileList.length; i++) {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rootDirectory + fileList[i])));

			String str = "";
			while((str=br.readLine()) != null) {
				//if(str.equals("") || str.toCharArray()[0]=='.')
				//	continue;

				if(str.equals("")) {
					relationPair.add("" + "+" + (fileList[i].split("\\."))[0]);
					continue;
				}

				String[] splitted = str.split("[\\(||\\)]");
				//if(splitted[0].split("_")[0].equals("prep")) {
					str = splitted[1].split(", ")[1] +"+"+ splitted[0] + "+" + splitted[1].split(", ")[0].split("-")[1]
							+ "+" + (fileList[i].split("\\."))[0];
					relationPair.add(str);
				//}
			}
			br.close();
		}
	}

	public ArrayList<String> RelationPair() {
		return relationPair;
	}

	public static void main(String []args) throws IOException {
		ArrayList<String> preps = new ArrayList<String>();

		DescriptionParser dp = new DescriptionParser();
		for(int i=0; i<dp.RelationPair().size(); i++) {
			String prep = dp.relationPair.get(i).split("\\+")[1];
			String splits[] = prep.split("_");
			if(splits[0].equals("prep")) {
				if(splits.length > 1) {
						prep = prep.substring(prep.indexOf("_")+1);
					if (!preps.contains(prep))
						preps.add(prep);
				}
			}
		}

		return;
	}
}
