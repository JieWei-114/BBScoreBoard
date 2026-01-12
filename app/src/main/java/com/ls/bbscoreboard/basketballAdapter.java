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

public class basketballAdapter extends RecyclerView.Adapter<basketballAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<CourseBasketball> CourseBasketballArrayList;
    private Context context;

    // constructor
    public basketballAdapter(ArrayList<CourseBasketball> CourseBasketballArrayList, Context context) {
        this.CourseBasketballArrayList = CourseBasketballArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basketball_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        CourseBasketball modal = CourseBasketballArrayList.get(position);
        holder.teamATVResult.setText(modal.getTeamAResult());                      //teamA
        holder.teamBTVResult.setText(modal.getTeamBResult());                    //teamB
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return CourseBasketballArrayList.size();
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
