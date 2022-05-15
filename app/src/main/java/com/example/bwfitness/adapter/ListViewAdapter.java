package com.example.bwfitness.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bwfitness.BenchProgressActivity;
import com.example.bwfitness.BookingActivity;
import com.example.bwfitness.CalorieActivity;
import com.example.bwfitness.DeadliftProgressActivity;
import com.example.bwfitness.GymProgressActivity;
import com.example.bwfitness.GymSlotActivity;
import com.example.bwfitness.HomePageActivity;
import com.example.bwfitness.MembershipActivity;
import com.example.bwfitness.OverallActivity;
import com.example.bwfitness.OverallNutritionActivity;
import com.example.bwfitness.PTActivity;
import com.example.bwfitness.Payment_Activity;
import com.example.bwfitness.R;
import com.example.bwfitness.SettingsActivity;
import com.example.bwfitness.SquatProgressActivity;
import com.example.bwfitness.TrackingActivity;
import com.example.bwfitness.WeightProgressActivity;
import com.example.bwfitness.WorkoutActivity;
import com.example.bwfitness.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<Model> models_list;
    ArrayList<Model> featuresList;


    public ListViewAdapter(Context context, List<Model> models_list){
        this.mContext = context;
        this.models_list = models_list;
        inflater = LayoutInflater.from(mContext);
        this.featuresList = new ArrayList<Model>();
        this.featuresList.addAll(models_list);
    }

    public class ViewHolder{
        TextView TitleText, DescText;
        ImageView ImageText;
    }
    @Override
    public int getCount() {
        return models_list.size();
    }

    @Override
    public Object getItem(int i) {
        return models_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row,null);

            holder.TitleText = view.findViewById(R.id.mainTitle);
            holder.DescText = view.findViewById(R.id.mainDesc);
            holder.ImageText = view.findViewById(R.id.mainIcon);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        holder.TitleText.setText(models_list.get(position).getTitle());
        holder.DescText.setText(models_list.get(position).getDesc());
        holder.ImageText.setImageResource(models_list.get(position).getImage());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(models_list.get(position).getTitle().equals("Overall Booking")){
                    Intent intent = new Intent(mContext, BookingActivity.class);
                    mContext.startActivity(intent);
                } else if
                (models_list.get(position).getTitle().equals("PT Booking")) {
                    Intent intent = new Intent(mContext, PTActivity.class);
                    mContext.startActivity(intent);
                } else if
                    (models_list.get(position).getTitle().equals("Home Page")){
                        Intent intent = new Intent(mContext, HomePageActivity.class);
                        mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Gym Membership Booking")){
                    Intent intent = new Intent(mContext, MembershipActivity.class);
                    mContext.startActivity(intent);
                }
                else if
                (models_list.get(position).getTitle().equals("Gym Slot Booking")){
                    Intent intent = new Intent(mContext, GymSlotActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Overall Tracking")){
                    Intent intent = new Intent(mContext, TrackingActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Calorie Tracking")){
                    Intent intent = new Intent(mContext, CalorieActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Workout Tracking")){
                    Intent intent = new Intent(mContext, WorkoutActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Payment")){
                    Intent intent = new Intent(mContext, Payment_Activity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Settings")){
                    Intent intent = new Intent(mContext, SettingsActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Progress")){
                    Intent intent = new Intent(mContext, GymProgressActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Workout Advice")){
                    Intent intent = new Intent(mContext, OverallActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Nutrition Advice")){
                    Intent intent = new Intent(mContext, OverallNutritionActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Bench Press Progress")){
                    Intent intent = new Intent(mContext, BenchProgressActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Deadlift Progress")){
                    Intent intent = new Intent(mContext, DeadliftProgressActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Squat Progress")){
                    Intent intent = new Intent(mContext, SquatProgressActivity.class);
                    mContext.startActivity(intent);
                }else if
                (models_list.get(position).getTitle().equals("Body Weight Progress")) {
                    Intent intent = new Intent(mContext, WeightProgressActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });

        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        models_list.clear();
        if (charText.length()==0){
            models_list.addAll(featuresList);
        }
        else{
            for(Model model : featuresList){
                if (model.getTitle().toLowerCase(Locale.getDefault())
                    .contains(charText)){
                    models_list.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
