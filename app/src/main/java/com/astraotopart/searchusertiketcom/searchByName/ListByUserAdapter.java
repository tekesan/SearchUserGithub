package com.astraotopart.searchusertiketcom.searchByName;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.astraotopart.searchusertiketcom.R;
import com.astraotopart.searchusertiketcom.adapter.AdapterOnItemClickListener;
import com.astraotopart.searchusertiketcom.apiHelper.BaseApiService;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ListByUserAdapter extends RecyclerView.Adapter<ListByUserAdapter.OnlineHolder> {

    List<ItemsItem> userItemList;

    Context mContext;
    AdapterView.OnItemClickListener listener;
    private ListByUserAdapter.OnItemClicked onClick;
    private AdapterOnItemClickListener mListener;
    Context context;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public ListByUserAdapter(Context context, List<ItemsItem> dataItem, AdapterOnItemClickListener listener) {
        this.mContext = context;
        userItemList = dataItem;
        this.mListener = listener;
    }

    @Override
    public ListByUserAdapter.OnlineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        return new ListByUserAdapter.OnlineHolder(itemView, mListener);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ListByUserAdapter.OnlineHolder holder, @SuppressLint("RecyclerView") final int position) {

        final ItemsItem item = userItemList.get(position);

        Picasso.get().load(item.getAvatarUrl())
                .into(holder.ivProfile);

        holder.tvUname.setText(item.getLogin());
    }

    @Override
    public int getItemCount() {

        return userItemList == null ? 0 : userItemList.size();
    }

    public class OnlineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView ivProfile;
        TextView tvUname;

        private OnlineHolder(View itemView, AdapterOnItemClickListener listener) {

            super(itemView);
            mListener = listener;
            context = itemView.getContext();

            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvUname = itemView.findViewById(R.id.tvUname);
        }

        @Override
        public void onClick(View itemView) {
            mListener.onClick(itemView, getAdapterPosition());
        }
    }

    public List<ItemsItem> getItems() {
        return userItemList;
    }

    public ItemsItem getItem(int position) {
        return userItemList.get(position);
    }


}
