package com.example.dailyplan2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DailyPlan> mPlanList = new ArrayList<>();
    private CustomAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private int count = 0; //그냥 일단 실험용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리사이클러뷰
        mRecyclerView = (RecyclerView) findViewById(R.id.PlanList);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomAdapter(mPlanList);
        mRecyclerView.setAdapter(mAdapter);

        //액티비티 메인
        TextView date = (TextView)findViewById(R.id.date); //캘린더 누른 날짜 받아오기 구현

        ImageView add_btn = (ImageView)findViewById(R.id.btn_add);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                DailyPlan plan = new DailyPlan("plan"+count, false, Integer.toString(count), Integer.toString(count*100000));

                //mPlanList.add(0,plan); //처음에 삽입
                mPlanList.add(plan); //뒤에 삽입
                mAdapter.notifyDataSetChanged(); //업데이트
            }
        });

    }
}