package qcore;

import CommonBeans.Action;

import java.util.ArrayList;
import java.util.HashMap;

public class Q {
    private static Q q = null;

    //number of neighbours,  value of each action
    private static HashMap<Integer, HashMap<Action, Double> > qTable = new HashMap<Integer, HashMap<Action, Double> >();

    private Q(){
        HashMap<Action, Double> map = new HashMap<Action, Double>();
        map.put(Action.DOWN, 0.0);
        map.put(Action.UP, 0.0);
        map.put(Action.KEEP, 0.0);
        qTable.put(0, map);
    }

    public static Q getInstance(){
        if(q == null){
            synchronized (Q.class){
                if(q == null){
                    q = new Q();
                    return q;
                }
            }
        }
        return q;
    }


}
