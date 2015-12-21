package util;

import java.util.ArrayList;

/**
 * Created by sliu on 12/4/15.
 */
public class TreeNode {
    public String word = null;
    public int idx = -1;

    public ArrayList<SonPair> sons = null;
    public int parent = -1;
    public String reln = null;

    public TreeNode(String word, int idx) {
        this.word = word;
        this.idx = idx;
        sons = new ArrayList<SonPair>();
    }
}
