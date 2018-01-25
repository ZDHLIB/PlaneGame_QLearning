import CommonBeans.Stone;
import Initialization.GenerateObject;
import jbotsim.Topology;
import jbotsim.ui.JViewer;

public class main {
    public static void main(String[] orgs) throws InterruptedException {
        Topology tp = new Topology(600, 600);
        tp.setDefaultNodeModel(Stone.class);
        for(int i = 0; i < 5; i++){
            GenerateObject.generateOneStone(tp, i+1, 600, 600);
            Thread.sleep(20);
        }
        GenerateObject.generateOneBird(tp, 0, 600, 600);

        new JViewer(tp);
//        for(int i = 0; i < 5; i++){
//            bird.setY(bird.getY() - 5);
//            Thread.sleep(500);
//        }
    }
}
