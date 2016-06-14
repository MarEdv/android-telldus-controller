package edvinsson.tellduscontroller;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;

class DeviceStatusTogglerAsyncTask extends AsyncTask<Device, Void, DeviceResponse> {

    @Override
    protected DeviceResponse doInBackground(Device... devices) {
        if (devices != null) {
            TelldusService telldusService = RetrofitSupport.getTelldusService();
            try {
                for (Device device : devices) {
                    Call<DeviceResponse> call;
                    if (device.getState() == 2) {
                        call = telldusService.turnOn(device.getId());
                    } else {
                        call = telldusService.turnOff(device.getId());
                    }

                    call.execute();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
