package CommonBeans;

public enum Action {

    UP("UP", 1),
    DOWN("DOWN", 2),
    KEEP("KEEP", 3);

    private String action;
    private int index;

    Action(String action, int i) {
        this.action = action;
        this.index = i;
    }

    public static Action getActionByIndex(Integer index){
        return Action.values()[index];
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
