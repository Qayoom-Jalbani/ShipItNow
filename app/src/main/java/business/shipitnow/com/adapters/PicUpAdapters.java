package business.shipitnow.com.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import business.shipitnow.com.R;
import business.shipitnow.com.module.Pocket;

public class PicUpAdapters extends RecyclerView.Adapter<PicUpAdapters.ViewHolder> {

    private List<Pocket> list = new ArrayList<>();
    private Context context;

    public PicUpAdapters(Context context) {
        this.context = context;
    }

    public void setList(List<Pocket> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PicUpAdapters.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pickup_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout Prent, DetailView;
        ImageView Detail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Detail = itemView.findViewById(R.id.detail);
            Prent = itemView.findViewById(R.id.prentview);
            DetailView = itemView.findViewById(R.id.detailview);

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
