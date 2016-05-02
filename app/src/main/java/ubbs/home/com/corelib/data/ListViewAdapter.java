package ubbs.home.com.corelib.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ubbs.home.com.corelib.sample.R;


/**
 * Created by udyatbhanu-mac on 6/27/15.
 */
public class ListViewAdapter extends ArrayAdapter<ListItem> {

    public ListViewAdapter(Context context, List<ListItem> items) {
        super(context, R.layout.listview_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.description = (TextView) convertView.findViewById(R.id.tvTitle);
            convertView.setTag(viewHolder);
        }else{
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListItem item = getItem(position);
        viewHolder.description.setText(item.description);
        return convertView;
    }

    private static class ViewHolder {

        TextView description;
    }
}
