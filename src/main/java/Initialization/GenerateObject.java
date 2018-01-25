package Initialization;

import CommonBeans.Bird;
import CommonBeans.BirdBuilder;
import CommonBeans.Stone;
import CommonBeans.StoneBuilder;
import jbotsim.Topology;

import java.util.Random;

public class GenerateObject {

    public static void generateOneStone(Topology tp,Integer id, Integer width, Integer height){
        StoneBuilder stoneBuilder = new StoneBuilder(Stone.class);
        Random random = new Random();

        int size = random.nextInt(15) + 5;
        double direction = random.nextInt(60) / 100.0 + 0.7;
        int x = width;
        int y = random.nextInt(height);

        stoneBuilder.buildId(id);
        stoneBuilder.buildSize(size);
        stoneBuilder.buildDirection(direction);
        tp.addNode(x, y, stoneBuilder.build());
    }

    public static Bird generateOneBird(Topology tp,Integer id, Integer width, Integer height) {
        BirdBuilder birdBuilder = new BirdBuilder(Bird.class);
        double x = width * 0.2;
        double y = height / 2.0;
        birdBuilder.buildId(id);
        System.out.print(x + " " + y);
        Bird bird = birdBuilder.build();
        tp.addNode(x, y, bird);
        return bird;
    }
}
