package model;

import java.lang.reflect.Type;
import java.util.ArrayList;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.*;

public class QueryParser {
	public ArrayList<TypedDependency> tdl;
	public QueryParser() {
		
	}
	
	public ArrayList<String> RelationPair(String query) {
		ArrayList<String> relationPair = new ArrayList<String>();
		/*
		String[] arg = {"-outputFormat", "typedDependencies"};
		LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
        lp.setOptionFlags(arg);
		Tree tree = lp.parse(query);
		System.out.println(tree);*/
		
		LexicalizedParser lp = LexicalizedParser.loadModel(
				"edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz",
				"-maxLength", "80", "-retainTmpSubcategories");
		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		Tree parse = lp.parse(query);
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
		tdl = (ArrayList<TypedDependency>) gs.typedDependenciesCCprocessed(false);
		
		for(int i=0; i<tdl.size(); i++) {
			TypedDependency dependency = tdl.get(i);
			GrammaticalRelation reln = dependency.reln();
			TreeGraphNode gov = dependency.gov();
			TreeGraphNode dep = dependency.dep();

			CoreLabel cl = gov.label();
//String str = cl.getString(edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation.class);

			//if(reln.toString().split("_")[0].equals("prep"))
				relationPair.add(dep.toString() + "+" + reln.toString() + "+" + gov.toString().split("-")[1]);
			//else
			//	relationPair.add(dep.toString() + "+prep_" + reln.toString() + "+" + gov.toString().split("-")[1]);
			// TODO: something bug here - If no prep appears.
		}

		return relationPair;
	}
	
	public static void main(String []args) {
		QueryParser qp = new QueryParser();
		qp.RelationPair("Man on dog");
	}
}
