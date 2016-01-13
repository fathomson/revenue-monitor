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
import sa.revenue.Utils;
import sa.revenue.general.db.AdPlacement;


public class AdPlacementDetailsArrayAdapter extends ArrayAdapter<AdPlacement> {
    private LayoutInflater inflater;
    List<AdPlacement> adPlacements = null;


    public AdPlacementDetailsArrayAdapter(Context context, List<AdPlacement> adPlacementSums) {
        super(context, R.layout.ad_placement_details_list_item, adPlacementSums);
        this.inflater = LayoutInflater.from(context);
        this.adPlacements = adPlacementSums;
    }

    public void swap(List<AdPlacement> adPlacementSums) {
        this.adPlacements.clear();
        this.adPlacements.addAll(adPlacementSums);
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
            convertView = inflater.inflate(R.layout.ad_placement_details_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        AdPlacement adPlacement = adPlacements.get(position);
        holder.textView_adPlacementName.setText(adPlacement.getAdvertiserAdName());
        holder.textView_adPlacementRevenue.setText(Utils.formatDoubleAsCurreny(adPlacement.getRevenue()));
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.textView_adPlacementName)
        TextView textView_adPlacementName;

        @Bind(R.id.textView_adPlacementRevenue)
        TextView textView_adPlacementRevenue;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}