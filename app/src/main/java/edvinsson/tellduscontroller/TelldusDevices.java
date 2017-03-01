package edvinsson.tellduscontroller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class TelldusDevices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telldus_devices);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showMessage(view, "Laddar om listan på enheter");
                    loadDevices();
                }
            });
        }

        loadDevices();
    }

    private void showMessage(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void loadDevices() {
        org.apmem.tools.layouts.FlowLayout listView = (org.apmem.tools.layouts.FlowLayout) findViewById(R.id.listview);
        listView.removeAllViews();
        try {
            DevicesResponse devicesResponse = new GetDevicesAsyncTask().execute().get();
            if (listView != null) {
                final Device[] devices = devicesResponse.getDevice().toArray(new Device[devicesResponse.getDevice().size()]);
                for (final Device device : devices) {
                    LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.list_item, null);
                    TextView textView = (TextView) view.getChildAt(1);
                    textView.setText(device.getName());
                    ImageView imageView = (ImageView) view.getChildAt(0);
                    if (device.getState() == 2) {
                        imageView.setImageResource(android.R.drawable.ic_delete);
                    } else {
                        imageView.setImageResource(android.R.drawable.ic_dialog_info);
                    }
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toggle(v, device);
                        }
                    });
                    listView.addView(view);
                }
                listView.invalidate();
                listView.isDirty();
//                listView.setAdapter(new DeviceAdapter(this, devices));
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        toggle(view, devices[position]);
//                    }
//                });
            }
        } catch (ExecutionException | InterruptedException e) {
            showMessage(listView, "Hämtar enheter: Ett fel uppstod.");
            e.printStackTrace();
        }
    }

    private void toggle(View view, Device device) {
        try {
            new DeviceStatusTogglerAsyncTask().execute(device).get();
            loadDevices();
        } catch (InterruptedException | ExecutionException e) {
            showMessage(view, "Ändra status: Ett fel uppstod.");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_telldus_devices, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
