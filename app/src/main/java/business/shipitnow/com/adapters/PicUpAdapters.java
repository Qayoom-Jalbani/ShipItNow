package business.shipitnow.com.adapters;


import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import business.shipitnow.com.R;
import business.shipitnow.com.coman.MyLib;
import business.shipitnow.com.module.AddPakage;


public class PicUpAdapters extends RecyclerView.Adapter<PicUpAdapters.ViewHolder> {

    public List<AddPakage> list = new ArrayList<>();
    private Context context;

    public static PicUpAdapters Adapter;

    public PicUpAdapters(Context context) {
        this.context = context;
    }
    public static PicUpAdapters getInstance(Context context){
        if (Adapter == null){
            Adapter = new PicUpAdapters(context);
        }
        return Adapter;
    }

    public void setList(List<AddPakage> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PicUpAdapters.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pickup_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Name.setText(list.get(position).getPackageName());
        holder.Description.setText(list.get(position).getDescription());
        holder.From.setText(list.get(position).getPickUpLocation());
        holder.To.setText(list.get(position).getDropOffLocation());

        String Date = list.get(position).getPickUpDate();
        Date = Date.substring(0,10);

        Date = MyLib.convertDate(Date,"yyyy-MM-dd");
        String pDate ="Drop-off Date : <b>"+Date+"</b>";
        holder.picDate.setText(Html.fromHtml(pDate));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout Prent, DetailView;
        ImageView Detail;
        TextView getPackage;

        TextView Name,picDate,Description,From,To;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.name);
            picDate = itemView.findViewById(R.id.date);
            Description = itemView.findViewById(R.id.pocket_dec);
            From = itemView.findViewById(R.id.from);
            To = itemView.findViewById(R.id.to);

            Prent = itemView.findViewById(R.id.prentview);
            DetailView = itemView.findViewById(R.id.detailview);

            getPackage = itemView.findViewById(R.id.pick_up_package);
            getPackage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("item", list.get(getAdapterPosition()));

                    Navigation.findNavController(v).navigate(R.id.pick_to_tracking, bundle);
                }
            });
            Detail = itemView.findViewById(R.id.detail);
            Detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (DetailView.getVisibility() == View.VISIBLE) {
                        // TransitionManager.beginDelayedTransition(Prent, new AutoTransition());
                        DetailView.setVisibility(View.GONE);
                        Detail.animate().rotation(0f).setDuration(500).start();
                        itemView.setBackgroundResource(R.drawable.ic_item_shape);

                    } else {
                        // TransitionManager.beginDelayedTransition(Prent, new AutoTransition());
                        DetailView.setVisibility(View.VISIBLE);
                        DetailView.setAlpha(1f);
                        //Detail.setAnimation(getAnim(1));
                        Detail.animate().rotation(180f).setDuration(500).start();
                        itemView.setBackgroundResource(R.drawable.ic_item_expend_shape);
                    }
                    notifyItemChanged(getAdapterPosition(), new Object());
                }
            });
        }
    }

}
