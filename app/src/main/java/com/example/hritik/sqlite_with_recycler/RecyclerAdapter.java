package com.example.hritik.sqlite_with_recycler;


import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.logging.Handler;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context ctx;
    private List<BioData> bioDataList;
    private DataBaseHandler dataBaseHandler;
    private AlertDialog.Builder builder;

    public RecyclerAdapter(Context ctx, List<BioData> bioDataList) {
        this.ctx = ctx;
        this.bioDataList = bioDataList;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_biodata,parent,false);

        return new ViewHolder(view,ctx);
    }

    @Override
    public void onBindViewHolder( RecyclerAdapter.ViewHolder holder, int position) {
        BioData bioData=bioDataList.get(position);
        holder.bioId.setText(String.valueOf(bioData.getId()));
        holder.bioName.setText(bioData.getName());
        holder.bioReligion.setText(bioData.getReligion());


    }

    @Override
    public int getItemCount() {
        return bioDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView bioName,bioReligion,bioId;
        private Button bioEdit,bioDelete;

        public ViewHolder(View itemView,Context context) {
            super(itemView);
            ctx=context;
            itemView.setOnClickListener(this);
            bioName=(TextView)itemView.findViewById(R.id.nameId);
            bioReligion=(TextView)itemView.findViewById(R.id.religionId);
            bioId=(TextView)itemView.findViewById(R.id.id);
            bioEdit=(Button)itemView.findViewById(R.id.editbutton);
            bioDelete=(Button)itemView.findViewById(R.id.deletebutton);
            bioDelete.setOnClickListener(this);
            bioEdit.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ctx,"Tapped"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public void onClick(View v) {


                switch (v.getId())
                {
                    case R.id.deletebutton:{
                        dataBaseHandler=new DataBaseHandler(ctx);
                        BioData bioData=bioDataList.get(getAdapterPosition());

                        dataBaseHandler.delete(bioData.getId());
                        bioDataList.remove(getAdapterPosition()); // very important : Remove it from list object
                        notifyItemRemoved(getAdapterPosition());
                        Toast.makeText(ctx,"Deleted-"+getAdapterPosition(),Toast.LENGTH_SHORT).show();


                        break;
                        }


                    case R.id.editbutton:{

                                                    final BioData bioData=bioDataList.get(getAdapterPosition());
                                                    builder=new AlertDialog.Builder(ctx);
                                                    View view=LayoutInflater.from(ctx).inflate(R.layout.edit_popup,null);
                                                    builder.setCancelable(true);
                                                    final EditText editName=(EditText)view.findViewById(R.id.editText4);
                                                    EditText editId=(EditText)view.findViewById(R.id.editText3);
                                                    final EditText editReligion=(EditText)view.findViewById(R.id.editText5);
                                                    Button updateButton=(Button)view.findViewById(R.id.updateButton);
                                                    builder.setView(view);
                                                    final AlertDialog dialog=builder.create();
                                                    dialog.show();
                                                    updateButton.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            dataBaseHandler=new DataBaseHandler(ctx);

                                                            bioData.setName(editName.getText().toString());
                                                            bioData.setReligion(editReligion.getText().toString());


                                                            if(!editName.getText().toString().isEmpty()&& !editReligion.getText().toString().isEmpty())
                                                            {
                                                                dataBaseHandler.updateBioData(bioData,getAdapterPosition());
                                                                notifyItemChanged(getAdapterPosition(),bioData);
                                                            }
                                                            new android.os.Handler().postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    dialog.dismiss();
                                                                }
                                                            },500);

                                                        }

                                                    });

                        break;
                    }
                      //  Toast.makeText(ctx,"Edit-"+getAdapterPosition(),Toast.LENGTH_SHORT).show();


                }

        }
    }


}
