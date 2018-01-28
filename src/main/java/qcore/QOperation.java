package qcore;

import CommonBeans.Action;

public class QOperation {

    private static Q q = Q.getInstance();

    public static Action makeDecision(int dis){
        return q.getAction(dis);
    }

    public static void updateQTable(int dis, int newDis, Action action){
        q.updateAction(dis, newDis, action);
//        q.printQData();
    }

}
