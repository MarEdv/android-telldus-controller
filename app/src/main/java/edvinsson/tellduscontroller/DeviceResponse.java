package edvinsson.tellduscontroller;

public class DeviceResponse {
    private String status;

    public DeviceResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
