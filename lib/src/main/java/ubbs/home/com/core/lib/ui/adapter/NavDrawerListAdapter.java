package ubbs.home.com.core.lib.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ubbs.home.com.core.lib.R;
import ubbs.home.com.core.lib.ui.data.NavDrawerItem;

/**
 * Created by udyatbhanu-mac on 7/3/15.
 */
public class NavDrawerListAdapter extends ArrayAdapter<NavDrawerItem> {

    public NavDrawerListAdapter(Context context, List<NavDrawerItem> items) {
        super(context, R.layout.navdrawer_listview_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.navdrawer_listview_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.description = (TextView) convertView.findViewById(R.id.tvTitle);

            convertView.setTag(viewHolder);
        }else{
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NavDrawerItem item = getItem(position);
        viewHolder.description.setText(item.description);

        return convertView;
    }

    private static class ViewHolder {

        TextView description;
    }
}