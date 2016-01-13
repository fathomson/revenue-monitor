package sa.revenue.general.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sa.revenue.R;
import sa.revenue.general.db.App;


/**
 * Created by un on 1/3/2016.
 */
public class AppSpinnerAdapter extends ArrayAdapter<App> {
    private LayoutInflater inflater;
    List<App> apps = null;

    public AppSpinnerAdapter(Context context, List<App> apps) {
        super(context, R.layout.app_spinner_list_item, apps);
        this.inflater = LayoutInflater.from(context);
        this.apps = apps;
    }

    @Override
    public App getItem(int position) {
        return apps.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.app_spinner_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        App app = apps.get(position);
    //    holder.textView_appName.setText(app.getAdvertiserAdName());
     //   holder.textView_appAdId.setText(app.getAdvertiserAdId());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.textView_appName)
        TextView textView_appName;
        @Bind(R.id.textView_appAdId)
        TextView textView_appAdId;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}