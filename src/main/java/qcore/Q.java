package qcore;

import CommonBeans.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Q {
    private static double epsilon = 0.3;
    private static Q q = null;

    //y coordination,  value of each action
    private static HashMap<Integer, HashMap<Action, Double> > qTable = new HashMap<Integer, HashMap<Action, Double> >();

    private static HashMap<Integer, Integer> reward = new HashMap<Integer, Integer>();

    static {
        reward.put(0,100);
        for(int i = 1; i <= 100; i++){
            reward.put(i, -i*10);
        }
    }


    private Q(){
        HashMap<Action, Double> map = new HashMap<Action, Double>();
        map.put(Action.DOWN, 0.0);
        map.put(Action.UP, 0.0);
        map.put(Action.KEEP, 0.0);
        qTable.put(300, map);
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

    public Action getAction(int y){
        if( qTable.containsKey(y) ){
            return getMaxAction(qTable.get(y));
        }else {
            HashMap<Action, Double> map = new HashMap<Action, Double>();
            map.put(Action.DOWN, 0.0);
            map.put(Action.UP, 0.0);
            map.put(Action.KEEP, 0.0);
            qTable.put(y, map);
            return getRandomAction(qTable.get(y));
        }
    }

    private Action getMaxAction(HashMap<Action, Double> map){
        Action action = null;
        int score = -1;
        for(Map.Entry entry : map.entrySet()){
            Action tmp = (Action) entry.getKey();
            Integer tmpScore = (Integer) entry.getValue();
            if( tmpScore > score ){
                score = tmpScore;
                action = tmp;
            }
        }
        return action;
    }

    private Action getRandomAction(HashMap<Action, Double> map){
        Random random = new Random();
        int n = random.nextInt(3);
        return Action.getActionByIndex(n);
    }

}
