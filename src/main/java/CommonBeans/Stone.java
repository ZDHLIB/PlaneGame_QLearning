package CommonBeans;

import jbotsim.Message;
import jbotsim.Node;

import java.awt.*;

public class Stone extends Node {

    @Override
    public void onStart() {
        this.setIcon("/imgs/stone.png");
        this.setCommunicationRange(0);
    }

    @Override
    public void onClock() {
        // code to be executed by this node in each round
        move();
        wrapLocation();
    }

    @Override
    public void onMessage(Message message) {
        // processing of a received message
    }

    @Override
    public void onSelection() {
        // what to do when this node is selected by the user
    }
}
