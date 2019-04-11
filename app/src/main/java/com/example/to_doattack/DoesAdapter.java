package com.example.to_doattack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DoesAdapter extends RecyclerView.Adapter<DoesAdapter.MyViewHolder> {

    Context context;
    ArrayList<Mydoes> myDoes;



    public  DoesAdapter(Context c , ArrayList<Mydoes> p){
        context = c;
        myDoes = p;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_does,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolder.titledoes.setText(myDoes.get(i).getTitledoes());
        myViewHolder.descdoes.setText(myDoes.get(i).getDescdoes());
        myViewHolder.datedoes.setText(myDoes.get(i).getDatedoes());


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("To-do list").child(getItemId(i)+"");
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete To-do");
                builder.setMessage("Confirm deleting To-do?");
                builder.setIcon(R.color.colorPrimary);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(i);
                        Toast.makeText(context , "Item deleted" , Toast.LENGTH_SHORT).show();;
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context , "Item not deleted" , Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                builder.show();
              // reference.removeValue();

            }
        });


        final String getTitleDoes = myDoes.get(i).getTitledoes();
        final String getDateDoes = myDoes.get(i).getDatedoes();
        final String getDescDoes = myDoes.get(i).getDescdoes();





    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titledoes , datedoes , descdoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titledoes = itemView.findViewById(R.id.titledoes);
            descdoes = itemView.findViewById(R.id.descdoes);
            datedoes = itemView.findViewById(R.id.datedoes);


        }
    }

    private void deleteItem(int position) {
        myDoes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, myDoes.size());

    }






}
