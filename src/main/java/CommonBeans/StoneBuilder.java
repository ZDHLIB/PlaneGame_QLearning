package CommonBeans;

import jbotsim.Node;

public class StoneBuilder {
    private Stone stone;

    public StoneBuilder(Class<? extends Node> var1){
        try {
            stone = (Stone) var1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public StoneBuilder buildId(Integer id){
        stone.setID(id);
        return this;
    }

    public StoneBuilder buildSize(Integer size){
        stone.setSize(size);
        return this;
    }

    public StoneBuilder buildDirection(Double var){
        stone.setDirection(var * Math.PI);
        System.out.println(var);
        return this;
    }

    public Stone build(){
        return stone;
    }
}





