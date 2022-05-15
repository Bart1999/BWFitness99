package com.example.bwfitness.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.example.bwfitness.PTBookingActivity;
import com.example.bwfitness.Payment_Activity;
import com.example.bwfitness.R;

import com.example.bwfitness.RecyclerViewInterface;
import com.example.bwfitness.model.PersonalTrainer;

public class PersonalTrainerAdapter extends RecyclerView.Adapter<PersonalTrainerAdapter.PersonalTrainerViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<PersonalTrainer> personalTrainerList;


    public PersonalTrainerAdapter(Context context,List<PersonalTrainer> personalTrainerList,
                                  RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.personalTrainerList=personalTrainerList;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public PersonalTrainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.personal_trainer_items,parent,false);
        return new PersonalTrainerViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalTrainerViewHolder holder, int position) {

        holder.ptImage.setImageResource(personalTrainerList.get(position).getImageUrl());
        holder.price.setText(personalTrainerList.get(position).getPrice());
        holder.name.setText(personalTrainerList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(context, PTBookingActivity.class);
                context.startActivity(m);
            }
        });

    }

    @Override
    public int getItemCount() {
        return personalTrainerList.size();
    }

    public static final class PersonalTrainerViewHolder extends RecyclerView.ViewHolder{


        ImageView ptImage;
        TextView price,name;

        public PersonalTrainerViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            ptImage=itemView.findViewById(R.id.pt_image);
            price=itemView.findViewById(R.id.pt_price);
            name=itemView.findViewById(R.id.pt_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos !=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
