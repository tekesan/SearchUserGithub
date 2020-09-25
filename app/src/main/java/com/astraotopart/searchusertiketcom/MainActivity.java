package com.astraotopart.searchusertiketcom;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.astraotopart.searchusertiketcom.adapter.AdapterOnItemClickListener;
import com.astraotopart.searchusertiketcom.commons.MyApplication;
import com.astraotopart.searchusertiketcom.databinding.ActivityMainBinding;
import com.astraotopart.searchusertiketcom.searchByName.ByUserResponse;
import com.astraotopart.searchusertiketcom.searchByName.ByUserViewModel;
import com.astraotopart.searchusertiketcom.searchByName.ItemsItem;
import com.astraotopart.searchusertiketcom.searchByName.ListByUserAdapter;
import com.astraotopart.searchusertiketcom.searchUser.ListUserAdapter;
import com.astraotopart.searchusertiketcom.searchUser.UserResponse;
import com.astraotopart.searchusertiketcom.searchUser.UserViewModel;


import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private boolean loadingStat = true;
    private int previousTotal = 0;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    ActivityMainBinding binding;
    ProgressDialog loading;
    ListUserAdapter listUserAdapter;
    ListByUserAdapter listByUserAdapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        
        layoutManager = new LinearLayoutManager(this);
        binding.rvSearch.setLayoutManager(layoutManager);

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etSearch.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please input at least 1 character.", Toast.LENGTH_SHORT).show();
                } else {
                    loadUserByUname(binding.etSearch.getText().toString());
                }
            }
        });

        loadUser();
    }

    private void loadUser() {
        if (MyApplication.getInstance().isNetworkAvailable()) {
            loading = ProgressDialog.show(MainActivity.this, null, "Harap Tunggu...", true, false);
            UserViewModel userViewModel = new UserViewModel();
            //Log.d("logggDataHp", "onClick: hp " + inputregister.getText().toString());

            userViewModel.reqAllUsers().observe(MainActivity.this
                    , new Observer<List<UserResponse>>() {
                        @Override
                        public void onChanged(List<UserResponse> responseBody) {
                            if (responseBody != null && !responseBody.equals("")) {

                                loading.dismiss();

                                listUserAdapter = new ListUserAdapter(MainActivity.this, responseBody, genProductAdapterListener());
                                binding.rvSearch.setAdapter(listUserAdapter);
                                listUserAdapter.notifyDataSetChanged();

                                binding.rvSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                        visibleItemCount = binding.rvSearch.getChildCount();
                                        totalItemCount = layoutManager.getItemCount();
                                        firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                                        if (loadingStat) {
                                            if (totalItemCount > previousTotal) {
                                                loadingStat = false;
                                                previousTotal = totalItemCount;
                                            }
                                        }
                                        if (!loadingStat && (totalItemCount - visibleItemCount)
                                                <= (firstVisibleItem + visibleThreshold)) {

                                            loadUser();

                                            loadingStat = true;
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(MainActivity.this, "Error API.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        } else {
            loading.dismiss();
            Toast.makeText(MainActivity.this, "No Network Available", Toast.LENGTH_LONG).show();
        }
    }

    private void loadUserByUname(final String uname) {
        if (MyApplication.getInstance().isNetworkAvailable()) {
            loading = ProgressDialog.show(MainActivity.this, null, "Harap Tunggu...", true, false);
            ByUserViewModel byUserViewModel = new ByUserViewModel();

            byUserViewModel.reqByUser(uname).observe(MainActivity.this
                    , new Observer<ByUserResponse>() {
                        @Override
                        public void onChanged(ByUserResponse responseBody) {
                            if (responseBody != null && !responseBody.equals("")) {

                                loading.dismiss();

                                if (responseBody.getTotalCount() > 0) {
                                    List<ItemsItem> itemsItemList = responseBody.getItems();

                                    listByUserAdapter = new ListByUserAdapter(MainActivity.this, itemsItemList, genProductAdapterListener());
                                    binding.rvSearch.setAdapter(listByUserAdapter);
                                    listByUserAdapter.notifyDataSetChanged();

                                    binding.rvSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                        @Override
                                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                            visibleItemCount = binding.rvSearch.getChildCount();
                                            totalItemCount = layoutManager.getItemCount();
                                            firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                                            if (loadingStat) {
                                                if (totalItemCount > previousTotal) {
                                                    loadingStat = false;
                                                    previousTotal = totalItemCount;
                                                }
                                            }
                                            if (!loadingStat && (totalItemCount - visibleItemCount)
                                                    <= (firstVisibleItem + visibleThreshold)) {

                                                loadUserByUname(uname);

                                                loadingStat = true;
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(MainActivity.this, "Username not found!", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                loading.dismiss();

                                Toast.makeText(MainActivity.this, "Error API.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        } else {
            loading.dismiss();
            Toast.makeText(MainActivity.this, "No Network Available", Toast.LENGTH_LONG).show();
        }
    }

    private AdapterOnItemClickListener genProductAdapterListener() {
        return new AdapterOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        };
    }

}
