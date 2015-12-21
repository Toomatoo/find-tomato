package util;

import java.util.ArrayList;

/**
 * Created by sliu on 12/4/15.
 */
public class DepTreeBuilder {
    ArrayList<String> relationPairs;
    public ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();

    public DepTreeBuilder(ArrayList<String> relationPairs) {
        this.relationPairs = relationPairs;

        nodes.add(new TreeNode("ROOT", 0));
        for(int i=0; i<relationPairs.size(); i++) {
            String items[] = relationPairs.get(i).split("\\+");

            nodes.add(new TreeNode(items[0].split("-")[0], Integer.valueOf(items[0].split("-")[1])));
        }

        for(int i=0; i<relationPairs.size(); i++) {
            String items[] = relationPairs.get(i).split("\\+");

            int aim = Integer.valueOf(items[2]);
            for(int j=0; j<nodes.size(); j++) {
                if(nodes.get(j).idx == aim) {
                    // TODO: test it again - index
                    nodes.get(j).sons.add(new SonPair(i+1, items[1]));
                    nodes.get(i+1).parent = j;
                    nodes.get(i+1).reln = items[1];
                }
            }
        }
    }

    public int find(int idx) {
        for(int j=0; j<nodes.size(); j++) {
            if(nodes.get(j).idx == idx) {
                return j;
            }
        }

        return -1;
    }

    // TODO: find a smallest span tree for objects
    public ArrayList<ArrayList<Integer>> spanArea(String gov, String dep) {
        TreeNode retNode = null;

        String []words = new String[2];

        int []indexes = new int[2];
        indexes[0] = -1;
        indexes[1] = -1;

        // Find the gov and dep words
        for(int i=0; i<nodes.size(); i++) {
            if(nodes.get(i).word.equals(gov)) {
                //TODO: should find all
                indexes[0] = i;
                break;
            }
        }
        for(int i=0; i<nodes.size(); i++) {
            if(nodes.get(i).word.equals(dep)) {
                //TODO: should find all
                indexes[1] = i;
                break;
            }
        }

        if(indexes[0] == -1 || indexes[1] == -1)
            return null;

        // Find the parent node
        ArrayList<Integer> parents0 = new ArrayList<Integer>();
        int pre = indexes[0];
        parents0.add(pre);
        while(nodes.get(pre).parent != -1) {
            parents0.add(nodes.get(pre).parent);
            pre = nodes.get(pre).parent;
        }

        ArrayList<Integer> parents1 = new ArrayList<Integer>();
        pre = indexes[1];
        parents1.add(pre);
        while(nodes.get(pre).parent != -1) {
            parents1.add(nodes.get(pre).parent);
            pre = nodes.get(pre).parent;
        }

        // Find the first co-parent
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();

        for(int i=0; i<parents0.size(); i++) {
            if(parents1.contains(parents0.get(i))) {
                retNode = nodes.get(parents0.get(i));

                paths.add(new ArrayList(parents0.subList(0, i+1)));
                paths.add(new ArrayList(parents1.subList(0, parents1.indexOf(parents0.get(i))+1)));

                break;
            }
        }


        return paths;
    }
}
