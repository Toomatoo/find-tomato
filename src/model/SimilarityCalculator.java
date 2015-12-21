package model;

import edu.stanford.nlp.trees.TypedDependency;
import util.*;

import java.io.IOException;
import java.util.*;
public class SimilarityCalculator {
	QueryParser queryParser = new QueryParser();
	ImageParser imageParser;
	DescriptionParser descriptionParser = new DescriptionParser();

	Preposition preposition = new Preposition();

	final static int scoreForGov = 2;
	final static int scoreForDep = 1;
	final static int scoreForReln = 2;

	FileManager fileManager = new FileManager("/Users/sliu/Documents/JavaWorkspace/Servlet JSPs/Toomatoo/WebContent/content/data");
	
	public SimilarityCalculator() throws IOException {
		imageParser = new ImageParser();
	}
	
	public ArrayList<String> calculate(String query) {
		ArrayList<String> pictureList = method2(query);



		return pictureList;
	}

	public ArrayList<String> method1(String query) {
		ArrayList<String> pictureList = new ArrayList<String>();

		HashMap<String, Integer> scoresForImages = new HashMap<String, Integer>();

		ArrayList<String> queryRelationPair = queryParser.RelationPair(query);
		ArrayList<String> imageRelationPair = imageParser.RelationPair();
		ArrayList<String> descRelationPair = descriptionParser.RelationPair();

		for(int q=0; q<queryRelationPair.size(); q++) {
			String[] tmp = queryRelationPair.get(q).split("\\+");

			String queryGov = tmp[0].split("-")[0];
			String queryDep = tmp[1].split("-")[0];
			String queryReln = tmp[2].split("_")[1];

			// Add scores from images
			for(int i=0; i<imageRelationPair.size(); i++) {
				tmp = imageRelationPair.get(i).split("\\+");

				String imageGov = tmp[0];
				String imageDep = tmp[1];
				String imageReln = tmp[2];
				String img = tmp[3];

				int score = 0;
				if(!imageGov.equals("ROOT") && !imageDep.equals("ROOT")
					&& imageGov.equals(queryGov) && imageDep.equals(queryDep))
					score += scoreForGov + scoreForDep;
				if(imageReln.equals(queryReln))
					score *= scoreForReln;

				if(scoresForImages.containsKey(img))
					scoresForImages.put(img, scoresForImages.get(img) + score);
				else
					scoresForImages.put(img, score);

			}

			// Add scores from description
			for(int d=0; d<descRelationPair.size(); d++) {
				tmp = descRelationPair.get(d).split("\\+");

				String descGov = tmp[0].split("-")[0];
				String descDep = tmp[1].split("-")[0];
				String descReln = "";
				if(tmp[2].split("_").length > 1)
					descReln = tmp[2].split("_")[1];
				String img = tmp[3];

				int score = 0;
				if(!descGov.equals("ROOT") && !descDep.equals("ROOT")
					&&descGov.equals(queryGov) && descDep.equals(queryDep))
					score += scoreForGov + scoreForDep;
				if(descReln.equals(queryReln))
					score *= scoreForReln;

				if(scoresForImages.containsKey(img))
					scoresForImages.put(img, scoresForImages.get(img) + score);
				else
					scoresForImages.put(img, score);
			}
		}

		List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(scoresForImages.entrySet());

		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
				return (o2.getValue().compareTo(o1.getValue()));
			}
		});

		for(int i=0; i<9; i++) {
			if(infoIds.get(i).getValue() != 0)
				pictureList.add(infoIds.get(i).getKey() + ".jpg");
		}
		return pictureList;
	}

	public ArrayList<String> method2(String query) {
		ArrayList<String> pictureList = new ArrayList<String>();

		HashMap<String, Integer> scoresForImages = new HashMap<String, Integer>();

		ArrayList<String> queryRelationPair = queryParser.RelationPair(query);
		ArrayList<String> imageRelationPair = imageParser.RelationPair();
		ArrayList<String> descRelationPair = descriptionParser.RelationPair();

		// Builder of the parsing tree for query
		DepTreeBuilder queryTree = new DepTreeBuilder(queryRelationPair);

		// Builder of the parsing tree for image
		DepTreeBuilder imageTree = null;

		// Builder of the parsing tree for description
		DepTreeBuilder descTree = null;

		for(int i=0; i<queryRelationPair.size(); i++) {
			String []splits = queryRelationPair.get(i).split("\\+");
			TypedDependency typedDependency = queryParser.tdl.get(i);

			/* find valid pairs */
			// prep pair
			if(!splits[1].split("_")[0].equals("prep"))
				continue;
			// noun pair
			if(typedDependency.gov().label().getString(
					edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation.class).toCharArray()[0] != 'N')
				continue;

			String dep = splits[0].split("-")[0];
			String gov = queryTree.nodes.get(queryTree.find(Integer.valueOf(splits[2]))).word;
			String reln = splits[1].substring(splits[1].indexOf("_")+1);

			// Build every image parsing tree, and score them.
			ArrayList<ArrayList<String>> splittedImageRelationPair = splitRelationPair(imageRelationPair);
			for(int j=0; j<splittedImageRelationPair.size(); j++) {
				imageTree = new DepTreeBuilder(splittedImageRelationPair.get(j));
				ArrayList<ArrayList<Integer>> paths = imageTree.spanArea(dep, gov);

				int score = 0;
				// find a span in the image tree about these two objects.
				if(paths != null) {
					score += scoreForDep + scoreForGov;

					int directionOfQuery = preposition.direction(reln);
					ArrayList<Integer> directionsOfImageOnPath = imageDirection(paths, imageTree);

					if(directionsOfImageOnPath.contains(directionOfQuery)) {
						score *= scoreForReln;
					}
				}


				String img = splittedImageRelationPair.get(j).get(0).split("\\+")[3].split("-")[0];
				if(scoresForImages.containsKey(img))
					scoresForImages.put(img, scoresForImages.get(img) + score);
				else
					scoresForImages.put(img, score);
			}



			// Build every description parsing tree (MAYBE more than one tree in a description), and score them.
			/*
			ArrayList<ArrayList<String>> splittedDescRelationPair = splitRelationPair(descRelationPair);
			for(int j=0; j<splittedDescRelationPair.size(); j++) {
				//TODO: MAYBE more than one tree in a description
			}*/

		}

		List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(scoresForImages.entrySet());

		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
				return (o2.getValue().compareTo(o1.getValue()));
			}
		});

		for(int i=0; i<infoIds.size(); i++) {
			if(infoIds.get(i).getValue() != 0)
				pictureList.add(infoIds.get(i).getKey() + ".jpg");
		}

		return pictureList;
	}

	private ArrayList<ArrayList<String>> splitRelationPair(ArrayList<String> relationPair) {
		ArrayList<ArrayList<String>> splittedRelationPair = new ArrayList<ArrayList<String>>();

		ArrayList<String> newPairList = new ArrayList<String>();
		String beforeImageName = relationPair.get(0).split("\\+")[3];
		for(int i=0; i<relationPair.size(); i++) {
			String []splits = relationPair.get(i).split("\\+");
			if(splits[3].equals(beforeImageName))
				newPairList.add(relationPair.get(i));
			else {
				beforeImageName = splits[3];
				splittedRelationPair.add(newPairList);
				newPairList = new ArrayList<String>();
				newPairList.add(relationPair.get(i));
			}
		}
		splittedRelationPair.add(newPairList);

		return splittedRelationPair;
	}

	private ArrayList<Integer> imageDirection(ArrayList<ArrayList<Integer>> paths, DepTreeBuilder imageTree) {
		int direction;
		ArrayList<Integer> directionsOnPath = new ArrayList<Integer>();

		// the path from gov to ROOT
		for(int i=0; i<paths.get(0).size()-1; i++) {
			String reln = imageTree.nodes.get(paths.get(0).get(i)).reln;
			int dirc = preposition.direction(reln);

			if(!directionsOnPath.contains(dirc))
				directionsOnPath.add(dirc);
		}

		//the path from ROOT to dep
		ArrayList<Integer> tmpDirectionsOnPath = new ArrayList<Integer>();
		for(int i=0; i<paths.get(1).size()-1; i++) {
			String reln = imageTree.nodes.get(paths.get(1).get(i)).reln;
			int dirc = preposition.direction(reln);

			if(!tmpDirectionsOnPath.contains(dirc))
				tmpDirectionsOnPath.add(dirc);
		}

		//merge the two paths
		for(int i=tmpDirectionsOnPath.size()-1; i>=0; i--) {
			if(!directionsOnPath.contains(tmpDirectionsOnPath.get(i)))
				directionsOnPath.add(tmpDirectionsOnPath.get(i));
		}

		return directionsOnPath;
	}
}
