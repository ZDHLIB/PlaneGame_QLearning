import CommonBeans.Bird;
import CommonBeans.Stone;
import Initialization.GenerateObject;
import jbotsim.Topology;
import jbotsim.ui.JViewer;
import org.junit.Test;

public class test {
    @Test
    public void testHelloWorld() throws InterruptedException {
        new JViewer(new Topology());
        Thread.sleep(10000);
    }

    @Test
    public void testMovingTimingNode() throws InterruptedException {
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
        Thread.sleep(50000);
    }

}
