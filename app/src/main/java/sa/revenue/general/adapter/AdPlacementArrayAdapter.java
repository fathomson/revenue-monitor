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
import sa.revenue.general.db.AdPlacement;


public class AdPlacementArrayAdapter extends ArrayAdapter<AdPlacement> {
    private LayoutInflater inflater;
    List<AdPlacement> adPlacements = null;


    public AdPlacementArrayAdapter(Context context, List<AdPlacement> adPlacements) {
        super(context, R.layout.simple_list_item, adPlacements);
        this.inflater = LayoutInflater.from(context);
        this.adPlacements = adPlacements;
    }

    public void swap(List<AdPlacement> adPlacements) {
        this.adPlacements.clear();
        this.adPlacements.addAll(adPlacements);
        notifyDataSetChanged();
    }

    @Override
    public AdPlacement getItem(int position) {
        return adPlacements.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.simple_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        AdPlacement adPlacement = getItem(position);
        holder.text1.setText(adPlacement.getAdvertiserAdName());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.text1)
        TextView text1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



}