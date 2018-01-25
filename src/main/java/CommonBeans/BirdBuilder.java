package CommonBeans;

import jbotsim.Node;

public class BirdBuilder {
    private Bird bird;

    public BirdBuilder(Class<? extends Node> var1){
        try {
            bird = (Bird) var1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public BirdBuilder buildId(Integer id){
        bird.setID(id);
        return this;
    }

    public Bird build(){
        return bird;
    }
}





