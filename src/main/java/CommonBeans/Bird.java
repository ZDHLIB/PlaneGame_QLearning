package CommonBeans;

import jbotsim.Message;
import jbotsim.Node;

import java.awt.*;

public class Bird extends Node {

    private static Integer xx;
    private static Integer yy;

    @Override
    public void onStart() {
        this.setSize(20);
        this.setColor(new Color(177, 10, 13));
        this.setCommunicationRange(50);
    }

    @Override
    public void onClock() {
        // code to be executed by this node in each round
//        move();
//        wrapLocation();
    }

    @Override
    public void onMessage(Message message) {
        // processing of a received message
    }

    @Override
    public void onSelection() {
        // what to do when this node is selected by the user
    }

    public static Integer getXx() {
        return xx;
    }

    public static void setXx(Integer xx) {
        Bird.xx = xx;
    }

    public static Integer getYy() {
        return yy;
    }

    public static void setYy(Integer yy) {
        Bird.yy = yy;
    }
}
