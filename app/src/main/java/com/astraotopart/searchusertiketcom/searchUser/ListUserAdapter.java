package com.astraotopart.searchusertiketcom.searchUser;


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

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.OnlineHolder> {

    List<UserResponse> userItemList;

    Context mContext;
    AdapterView.OnItemClickListener listener;
    private ListUserAdapter.OnItemClicked onClick;
    private AdapterOnItemClickListener mListener;
    Context context;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public ListUserAdapter(Context context, List<UserResponse> dataItem, AdapterOnItemClickListener listener) {
        this.mContext = context;
        userItemList = dataItem;
        this.mListener = listener;
    }

    @Override
    public ListUserAdapter.OnlineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        return new ListUserAdapter.OnlineHolder(itemView, mListener);
    }


    @Override
    public void onBindViewHolder(final ListUserAdapter.OnlineHolder holder, @SuppressLint("RecyclerView") final int position) {

        final UserResponse item = userItemList.get(position);

        Picasso.get().load(item.getAvatarUrl())
                .into(holder.ivProfile);

        holder.tvUname.setText(String.valueOf(item.getLogin()));
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

    public List<UserResponse> getItems() {
        return userItemList;
    }

    public UserResponse getItem(int position) {
        return userItemList.get(position);
    }


}
