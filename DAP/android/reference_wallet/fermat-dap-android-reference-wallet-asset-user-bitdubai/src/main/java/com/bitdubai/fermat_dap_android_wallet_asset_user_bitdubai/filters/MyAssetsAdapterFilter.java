package com.bitdubai.fermat_dap_android_wallet_asset_user_bitdubai.filters;

import android.widget.Filter;

import com.bitdubai.fermat_dap_android_wallet_asset_user_bitdubai.adapters.MyAssetsAdapter;
import com.bitdubai.fermat_dap_android_wallet_asset_user_bitdubai.models.DigitalAsset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank Contreras (contrerasfrank@gmail.com) on 2/4/16.
 */
public class MyAssetsAdapterFilter extends Filter {

    private List<DigitalAsset> data;
    private MyAssetsAdapter adapter;

    public MyAssetsAdapterFilter(List<DigitalAsset> data, MyAssetsAdapter adapter) {
        this.data = data;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        String filterString = constraint.toString().toLowerCase();

        FilterResults results = new FilterResults();

        final List<DigitalAsset> list = data;

        int count = list.size();
        final ArrayList<DigitalAsset> nlist = new ArrayList<>(count);

        String filterableString;
        DigitalAsset digitalAsset;

        for (int i = 0; i < count; i++) {
            digitalAsset = list.get(i);
            filterableString = digitalAsset.getName();
            if (filterableString.toLowerCase().contains(filterString)) {
                nlist.add(list.get(i));
            }
        }

        results.values = nlist;
        results.count = nlist.size();

        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.changeDataSet((List<DigitalAsset>) filterResults.values);
    }
}
