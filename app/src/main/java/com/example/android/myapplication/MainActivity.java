package com.example.android.myapplication;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.myapplication.Adapter.CustomAdapter;
import com.example.android.myapplication.Data.RetrofitPhotos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter mAdapter;
    private RecyclerView mRecyclerView;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progress_bar);

//        Progress bar should be visible when the app is loaded
        mProgressBar.setVisibility(View.VISIBLE);

        /*Create handle for the RetrofitInstance interface*/
        RetrofitPhotos.GetDataService service = InstantiateRetrofitInstance.getRetrofit().create(RetrofitPhotos.GetDataService.class);
        Call <List <RetrofitPhotos>> call = service.getAllPhotos();

        call.enqueue(new Callback <List <RetrofitPhotos>>() {
            @Override
            public void onResponse(Call <List <RetrofitPhotos>> call, Response <List <RetrofitPhotos>> response) {
                mProgressBar.setVisibility(View.GONE);
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call <List <RetrofitPhotos>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }

        /*Method to generate List of data using RecyclerView with custom adapter*/
        private void generateDataList (List < RetrofitPhotos > photoList) {
            mRecyclerView = findViewById(R.id.customRecyclerView);
            mAdapter = new CustomAdapter(this, photoList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
    }