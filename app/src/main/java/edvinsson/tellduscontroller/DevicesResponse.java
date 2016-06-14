package edvinsson.tellduscontroller;

import java.util.List;

public class DevicesResponse {
    private List<Device> device;

    public DevicesResponse(List<Device> device) {
        this.device = device;
    }

    public List<Device> getDevice() {
        return device;
    }
}
