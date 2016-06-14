package edvinsson.tellduscontroller;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;

class GetDevicesAsyncTask extends AsyncTask<Void, Void, DevicesResponse> {

    @Override
    protected DevicesResponse doInBackground(Void... params) {
        TelldusService telldusService = RetrofitSupport.getTelldusService();
        Call<DevicesResponse> listCall = telldusService.listDevices();

        try {
            return listCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
