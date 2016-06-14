package edvinsson.tellduscontroller;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TelldusService {
    @GET("/telldus/devices.php")
    Call<DevicesResponse> listDevices();

    @GET("/telldus/turnOn.php")
    Call<DeviceResponse> turnOn(@Query("id") int id);

    @GET("/telldus/turnOff.php")
    Call<DeviceResponse> turnOff(@Query("id") int id);
}
