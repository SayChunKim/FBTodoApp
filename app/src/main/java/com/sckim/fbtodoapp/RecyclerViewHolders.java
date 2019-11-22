package com.sckim.fbtodoapp;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by SC Kim on 10/19/2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder{
    private static final String TAG = RecyclerViewHolders.class.getSimpleName();
    public ImageView deleteIcon;
    public TextView categoryTitle;
    public List<Task> taskObject;

    public RecyclerViewHolders(final View itemView, final List<Task> taskObject) {
        super(itemView);
        this.taskObject = taskObject;
        categoryTitle = itemView.findViewById(R.id.task_title);
        deleteIcon = itemView.findViewById(R.id.task_delete);
        deleteIcon.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View v) {
            //What happens when X is clicked.
            Toast.makeText(v.getContext(), "Delete icon is triggered",Toast.LENGTH_LONG).show();
            String taskTitle = taskObject.get(getAdapterPosition()).getTask();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            final Query applesQuery = ref.orderByChild("task").equalTo(taskTitle);
            applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()){
                        appleSnapshot.getRef().removeValue();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCanceled", databaseError.toException());
                }
            });


                  }
              }

            );
    }
}
