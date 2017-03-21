package com.example.justinlam.coputingcup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class activityAdapter extends RecyclerView.Adapter<activityAdapter.ViewHolder> {

    static List<databaseModelList> dblist;
    Context context;

    activityAdapter(Context context, List<databaseModelList> dblist){
        this.dblist = new ArrayList<databaseModelList>();
        this.context = context;
        this.dblist = dblist;
    }
    @Override
    public activityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(activityAdapter.ViewHolder holder, int position){
        holder.textviewactivitynumber.setText(dblist.get(position).getActivityNumber().toString());
        holder.textviewpriority.setText(dblist.get(position).getPriority().toString());
    }
    @Override
    public int getItemCount(){
        return dblist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textviewactivitynumber, textviewpriority;
        public ViewHolder(View itemLayoutView){
            super(itemLayoutView);
            textviewactivitynumber = (TextView) itemLayoutView.findViewById(R.id.XMLactivitynumberid);
            textviewpriority = (TextView) itemLayoutView.findViewById(R.id.XMLpriorityid);
        }
    }

}
