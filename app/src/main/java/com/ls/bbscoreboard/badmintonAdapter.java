package com.ls.bbscoreboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ls.bbscoreboard.CourseBadminton;

import java.util.ArrayList;

public class badmintonAdapter extends RecyclerView.Adapter<badmintonAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<CourseBadminton> CourseBadmintonArrayList;
    private Context context;

    // constructor
    public badmintonAdapter(ArrayList<CourseBadminton> CourseBadmintonArrayList, Context context) {
        this.CourseBadmintonArrayList = CourseBadmintonArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.badminton_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        CourseBadminton modal = CourseBadmintonArrayList.get(position);
        holder.teamATVResult.setText(modal.getTeamAResult());                      //teamA
        holder.teamBTVResult.setText(modal.getTeamBResult());                    //teamB
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return CourseBadmintonArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView teamATVResult, teamBTVResult;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            teamATVResult = itemView.findViewById(R.id.idTVTeamAResult);
            teamBTVResult = itemView.findViewById(R.id.idTVTeamBResult);
        }
    }
}
