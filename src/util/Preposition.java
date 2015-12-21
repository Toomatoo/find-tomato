package util;

import java.util.ArrayList;

/**
 * Created by sliu on 16/4/15.
 */
public class Preposition {
    //
    public static ArrayList<String> above = new ArrayList<String>();
    public static ArrayList<String> below = new ArrayList<String>();

    public static ArrayList<String> beside = new ArrayList<String>();

    public static ArrayList<String> infront = new ArrayList<String>();
    public static ArrayList<String> behind = new ArrayList<String>();

    public int direction(String prep) {
        if(above.contains(prep))
            return 1;
        if(below.contains(prep))
            return -1;

        if(infront.contains(prep))
            return 2;
        if(behind.contains(prep))
            return -2;

        if(beside.contains(prep))
            return 3;
        return 0;
    }

    public Preposition() {
        // above
        above.add("on");
        above.add("above");
        above.add("upon");
        above.add("on_top_of");

        // below
        below.add("below");
        below.add("beneath");
        below.add("under");
        below.add("underneath");

        // beside
        beside.add("beside");
        beside.add("opposite");
        beside.add("aside");
        beside.add("near");
        beside.add("next");
        beside.add("near_to");
        beside.add("next_to");
        beside.add("across_from");
        beside.add("along_with");
        beside.add("alongside_of");
        beside.add("close_to");
        beside.add("together_with");
        beside.add("surrounds");
        beside.add("around");

        // infront
        infront.add("infront");
        infront.add("in_front_of");
        infront.add("before");
        infront.add("ahead_of");

        // behind
        behind.add("behind");
        behind.add("after");

    }

    public int reverse(int direction) {
        if(Math.abs(direction) < 3)
            return -direction;
        else if(direction == 3)
            return 3;
        else
            return 0;
    }
}
