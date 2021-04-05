package com.example.dailyplan2;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<DailyPlan> dailyPlans;

    public CustomAdapter(ArrayList<DailyPlan> dailyPlans) { //constructor
        this.dailyPlans = dailyPlans;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{ //item들 불러오기
        TextView plan;
        TextView order;
        TextView income;

        CheckBox cb;
        ImageView remove_btn;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.plan = (TextView) itemView.findViewById(R.id.plan_it);
            this.order = (TextView) itemView.findViewById(R.id.order_it);
            this.income = (TextView) itemView.findViewById(R.id.income_it);

            this.cb = (CheckBox) itemView.findViewById(R.id.cb_it);
            this.remove_btn = (ImageView) itemView.findViewById(R.id.remove_btn_it);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //뷰홀더 객체 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {//데이터를 뷰홀더에 바인딩
        final DailyPlan dailyPlan = dailyPlans.get(position); //final로 선언해야 값이 바뀌지 않음

        holder.plan.setText(dailyPlan.getPlan());
        holder.order.setText(dailyPlan.getOrder());
        holder.income.setText(dailyPlan.getIncome());

        holder.remove_btn.setTag(position); // 몇번짼지 태그 달기
        holder.remove_btn.setOnClickListener(new View.OnClickListener() { // 리무브 버튼 누르면 그 아이템 삭제시키기
            @Override
            public void onClick(View v) {
                int btnPosition = (int) v.getTag();
                dailyPlans.remove(btnPosition); // 그 아이템 삭제
                notifyDataSetChanged(); // 업데이트
            }
        });

        //먼저 체크박스 리스너 초기화
        holder.cb.setOnCheckedChangeListener(null);
        //getter로 체크 상태를 가져오고 setter로 이 값을 아이템 안의 체크박스에 set
        holder.cb.setChecked(dailyPlan.getSelected());
        //체크 상태를 알기 위해 리스너 부착
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //여기 dailyPlan이 final 키워드를 붙인 모델 클래스의 객체와 동일
                dailyPlan.setSelected(isChecked);
            }
        });

        //체크박스 체크면 수입 표시 아니면 수입이 안 보이게
     /*   holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.cb.isChecked()){
                    holder.income.setVisibility(View.VISIBLE);
                }else{
                    holder.income.setVisibility(View.INVISIBLE);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {//전체 아이템 갯수 리턴
        return dailyPlans.size();
    }

    @Override
    public long getItemId(int position) {
        //return super.getItemId(position);
        return position;
    }
}
