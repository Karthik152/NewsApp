package com.example.newspaper.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newspaper.MainActivity;
import com.example.newspaper.MainActivity2;
import com.example.newspaper.Model.NewsModel;
import com.example.newspaper.R;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder>{

private ArrayList<NewsModel> data;
private Context context;
private OnItemClicked onClick;


public interface OnItemClicked {
    void onItemClick(int itemId, int position, View view);
}
    public NewsListAdapter(ArrayList<NewsModel> data) {
        this.data=data;
        this.context=context;
    }



    @NonNull
    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NewsModel curent= data.get(position);
        holder.descr.setText(curent.getDescriptionn());


        holder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(v.getId(),position,holder.itemView);

//                Intent intent=new Intent(context, MainActivity2.class);
//                intent.putExtra("id",data.get(position).getId());
//                Toast.makeText(context, "iddd"+data.get(position).getId(), Toast.LENGTH_SHORT).show();
//                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView descr;
    private ImageView imgview;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        descr=itemView.findViewById(R.id.desc);
        imgview=itemView.findViewById(R.id.itemimg);
    }
}
    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }

}