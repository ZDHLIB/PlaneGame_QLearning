package CommonBeans;

import jbotsim.Link;
import jbotsim.Message;
import jbotsim.Node;
import qcore.QOperation;

import java.awt.*;
import java.util.List;

public class Bird extends Node {

    private static Integer xx;
    private static Integer yy;

    @Override
    public void onStart() {
        this.setIcon("/imgs/plane.png");
        this.setSize(40);
        this.setCommunicationRange(100);
    }

    @Override
    public void onClock() {
        // code to be executed by this node in each round
//        move();
//        wrapLocation();

        int newY = (int) this.getY();
        int dis = getShortestDistance(this.getOutNeighbors());
        Action action = QOperation.makeDecision(dis);
        switch (action){
            case UP:
                newY += 5;
                this.setLocation(this.getX(), newY);
                break;
            case KEEP:
                break;
            case DOWN:
                newY -= 5;
                this.setLocation(this.getX(), newY);
        }
        wrapLocation();
        int newDis = getShortestDistance(this.getOutNeighbors());
        QOperation.updateQTable(dis, newDis, action);
    }

    @Override
    public void onMessage(Message message) {
        // processing of a received message
    }

    @Override
    public void onSelection() {
        // what to do when this node is selected by the user
    }

    private Integer getShortestDistance(List<Node> nodes){
        int dis = 999;
        for(Node node : nodes){
            if( node.getX() > this.getX() ) {
                dis = (int) Math.min( dis, this.distance(node) );
            }
        }
        return dis;
    }
}
