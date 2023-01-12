package edu.upc.dsa.andoroid_dsa.activities;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.models.Gadget;
import edu.upc.dsa.andoroid_dsa.models.User;

public class RecyclerViewAdapterUsers extends RecyclerView.Adapter<RecyclerViewAdapterUsers.ViewHolder>{
    private static RecycleClickViewListener listener;
    public List<User> users;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name,surname,coins,experience;
        ImageView profilePicture;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.nameOfUser);
            surname=(TextView)itemView.findViewById(R.id.surnameOfUser);
            coins=(TextView)itemView.findViewById(R.id.coinsOfUser);
            experience=(TextView)itemView.findViewById(R.id.experienceOfUser);
            profilePicture=(ImageView) itemView.findViewById(R.id.profilePicture);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            listener.recyclerViewListClicked(this.getLayoutPosition());
        }
    }

    public RecyclerViewAdapterUsers(List<User> users, RecycleClickViewListener listener) {
        this.users = users;
        this.listener =listener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterUsers.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_user,parent,false);
        RecyclerViewAdapterUsers.ViewHolder viewHolder= new RecyclerViewAdapterUsers.ViewHolder(view);
        return viewHolder;
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterUsers.ViewHolder holder, int position) {
        holder.name.setText(users.get(position).getName());
        holder.surname.setText(users.get(position).getSurname());
        holder.coins.setText("Coins: "+Integer.toString(users.get(position).getCoins()));
        holder.experience.setText("Experience: "+Integer.toString(users.get(position).getExperience()));
        Picasso.get().load(users.get(position).getProfilePicture()).into(holder.profilePicture);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
