package view;

import java.io.IOException;
import java.util.*;
import model.SimilarityCalculator;
public class ResultViewer {
	SimilarityCalculator simCalculator;
	public ResultViewer() throws IOException{
		simCalculator = new SimilarityCalculator();
	}
	
	public ArrayList<String> getPictures(String query) {
		ArrayList<String> pictureList = new ArrayList<String>();
		
		pictureList = simCalculator.calculate(query);
		
		return pictureList;
	}
	
	public static void main(String []args) throws IOException {
		(new ResultViewer()).getPictures("Man and Dog");
	}
}
