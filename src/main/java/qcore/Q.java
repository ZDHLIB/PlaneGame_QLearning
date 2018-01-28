package qcore;

import CommonBeans.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Q {
    private static double epsilon = 0.3;
    private static double alpha = 0.3;
    private static double gamma = 0.8;
    private static Q q = null;

    //Distance to neighbours,  value of each action
    private static HashMap<Integer, HashMap<Action, Double> > qTable = new HashMap<Integer, HashMap<Action, Double> >();

    private static HashMap<Integer, Integer> reward = new HashMap<Integer, Integer>();

    static {
        reward.put(0,50);
        for(int i = 1000; i > 0; i--){
            reward.put(i, -(10000/i));
        }
    }


    private Q(){
//        HashMap<Action, Double> map = new HashMap<Action, Double>();
//        map.put(Action.DOWN, 0.0);
//        map.put(Action.UP, 0.0);
//        map.put(Action.KEEP, 0.0);
//        qTable.put(300, map);
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

    public Action getAction(int dis){
        if( qTable.containsKey(dis) ){
            return getMaxAction(qTable.get(dis));
        }else {
            HashMap<Action, Double> map = new HashMap<Action, Double>();
            map.put(Action.DOWN, 0.0);
            map.put(Action.UP, 0.0);
            map.put(Action.KEEP, 0.0);
            qTable.put(dis, map);
            return Action.KEEP;
        }
    }

    public void updateAction(int dis, int newDis, Action action){
        System.out.println("dis:"+dis+" newDis:"+newDis);
        if( qTable.containsKey(newDis) ){
            double r = dis < 20 && dis > 0 ? reward.get(dis)*2 : reward.get(dis);
            double newValue = (1 - alpha) * qTable.get(dis).get(action) +
                              alpha * ( r + gamma * qTable.get(newDis).get(action) );
            HashMap<Action, Double> map = qTable.get(newDis);
            map.put(action, newValue);
            qTable.put(newDis, map);
        }else {
            HashMap<Action, Double> map = new HashMap<Action, Double>();
            map.put(Action.DOWN, 0.0);
            map.put(Action.UP, 0.0);
            map.put(Action.KEEP, 0.0);
            double r = dis < 20 && dis > 0 ? reward.get(dis)*2 : reward.get(dis);
            double newValue = (1 - alpha) * qTable.get(dis).get(action) +
                              alpha * ( r + gamma * 0 );
            map.put(action, newValue);
            qTable.put(newDis, map);
        }
        printQData();
    }

    private Action getMaxAction(HashMap<Action, Double> map){
        Action action = Action.KEEP;
        Double score = -1.0;
        for(Map.Entry entry : map.entrySet()){
            Action tmp = (Action) entry.getKey();
            Double tmpScore = (Double) entry.getValue();
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

    public void printQData(){
        for(Map.Entry entry : qTable.entrySet()){
            Integer dis = (Integer) entry.getKey();
            HashMap<Action, Double> map = (HashMap<Action, Double>) entry.getValue();
            System.out.print("Distance:" + dis + "---");
            for(Map.Entry entry2 : map.entrySet()){
                Action action = (Action) entry2.getKey();
                Double value = (Double) entry2.getValue();
                System.out.print(action.getAction()+">>"+value+", ");
            }
            System.out.println("\n");
        }
    }

}
