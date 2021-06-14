package com.example.sqlitedemo;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.model.Cong;

import java.util.List;

public class RevAdapter extends RecyclerView.Adapter<RevAdapter.CardView>{
    private Activity activity;
    private List<Cong> mList;

    public RevAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<Cong> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    public void addData(Cong c){
        this.mList.add(c);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = activity.getLayoutInflater().inflate(R.layout.item_card, parent,false);
        return new CardView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardView holder, int position) {
        Cong c = mList.get(position);
        holder.id.setText(Integer.toString(c.getId()));
        holder.name.setText(c.getName());
        holder.mark.setText(Double.toString(c.getMark()));
        if(c.isGender()){
            holder.img.setImageResource(R.drawable.ic_baseline_add_24);
        }
        else{
            holder.img.setImageResource(R.drawable.ic_sub);
        }
        System.out.println("revb"+c.isGender());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ActivitySelectedItem.class);
                intent.putExtra("item", c);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList!=null)return mList.size();
        return 0;
    }

    public class CardView extends RecyclerView.ViewHolder {
        TextView id, name,mark;
        ImageView img;
        public CardView(@NonNull View v) {
            super(v);
            id = v.findViewById(R.id.iID);
            name = v.findViewById(R.id.iName);
            mark = v.findViewById(R.id.iMark);
            img = v.findViewById(R.id.iImg);
        }
    }
}
