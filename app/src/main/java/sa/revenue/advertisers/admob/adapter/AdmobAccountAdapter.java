package sa.revenue.advertisers.admob.adapter;

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
import sa.revenue.advertisers.admob.model.Account;

/**
 * Created by un on 1/3/2016.
 */
public class AdmobAccountAdapter extends ArrayAdapter<Account> {
    private LayoutInflater inflater;
    List<Account> accounts = null;


    public AdmobAccountAdapter(Context context, List<Account> accounts) {
        super(context, R.layout.admob_account_item, accounts);
        this.inflater = LayoutInflater.from(context);
        this.accounts = accounts;
    }

    public void swap(List<Account> newAccounts) {
        accounts.clear();
        accounts.addAll(newAccounts);
        notifyDataSetChanged();
    }

    @Override
    public Account getItem(int position) {
        return accounts.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.admob_account_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        Account account = accounts.get(position);
        holder.textView_admobName.setText(account.getName());
        holder.textView_admobId.setText(account.getId());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.textView_admobName)
        TextView textView_admobName;

        @Bind(R.id.textView_admobId)
        TextView textView_admobId;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}