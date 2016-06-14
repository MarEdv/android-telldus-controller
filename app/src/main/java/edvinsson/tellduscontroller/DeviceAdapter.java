package edvinsson.tellduscontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DeviceAdapter extends ArrayAdapter<Device> {
    private final Device[] values;
    private final Context context;

    public DeviceAdapter(Context context, Device[] objects) {
        super(context, -1, objects);
        values = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.list_item_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_item_icon);
        textView.setText(values[position].getName());
        if (values[position].getState() == 2) {
            imageView.setImageResource(android.R.drawable.ic_delete);
        } else {
            imageView.setImageResource(android.R.drawable.ic_dialog_info);
        }

        return rowView;
    }
}
