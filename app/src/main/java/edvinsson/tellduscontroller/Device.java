package edvinsson.tellduscontroller;

public class Device {
    private String name;
    private int state;
    private int id;

    public Device(String name, int state, int id) {
        this.name = name;
        this.state = state;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getState() {
        return state;
    }

    public int getId() {
        return id;
    }
}
