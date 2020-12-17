package business.shipitnow.com.adapters;


import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult;

import business.shipitnow.com.R;
import business.shipitnow.com.coman.MyLib;
import business.shipitnow.com.module.AddPakage;
import business.shipitnow.com.module.Pocket;

public class MyPackageAdapters extends RecyclerView.Adapter<MyPackageAdapters.ViewHolder> implements Filterable {

    public List<AddPakage> list = new ArrayList<>();
    private List<AddPakage> FullList = new ArrayList<>();
    private Context context;

    public MyPackageAdapters(Context context) {
        this.context = context;
    }
    public static MyPackageAdapters Adapter;
    public static MyPackageAdapters getInstance(Context context){
        if (Adapter ==null){
            Adapter = new MyPackageAdapters(context);
        }
        return Adapter;
    }


    public void setList(List<AddPakage> list) {
        this.list.clear();
        this.list.addAll(list);
        this.FullList.clear();
        this.FullList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyPackageAdapters.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_package_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.size() > 0) {

            AddPakage item = list.get(position);
            if (item.getInProcess().equals("false")) {
                holder.Status.setText("Package Order in a Queue to process");
                holder.Description.setText("Package Order In a Queue When Order Get into Processing you wil get Order Process notification");
                holder.DeliverDate.setVisibility(View.GONE);
            } else if (item.getInProcess().equals("true") && item.getIsDelivered().equals("false")) {
                holder.Status.setText("InProcess");
                holder.Description.setText("Package order in a processing. Rider name is johns his contact no is 3030587214 when Order Is Delivered you will get order Delivered Notification");
                holder.DeliverDate.setVisibility(View.VISIBLE);
            } else if (item.getIsDelivered().equals("true")) {
                holder.Status.setText("Delivered");
                holder.Description.setText("Package order is Delivered on your Desire Destination  ");
                holder.DeliverDate.setVisibility(View.VISIBLE);
            }
            holder.Name.setText(item.getPackageName());
            holder.OrderNo.setText("Order No " + item.getPackageID());
            if (item.getInProcess().equals("true") && item.getIsDelivered().equals("false")) {
                String Date = item.getPickUpDate();
                Date = Date.substring(0, 10);
                Date = MyLib.convertDate(Date, "yyyy-MM-dd");
                String pDate = "Drop-off Date : <b>" + Date + "</b>";
                holder.DeliverDate.setText(Html.fromHtml(pDate));
                holder.DeliverDate.setVisibility(View.VISIBLE);
            }else if (item.getInProcess().equals("true") && item.getIsDelivered().equals("true")) {
                String Date = item.getDropOffDate();
                Date = Date.substring(0, 10);
                Date = MyLib.convertDate(Date, "yyyy-MM-dd");
                String pDate = "Delivered Date : <b>" + Date + "</b>";
                holder.DeliverDate.setText(Html.fromHtml(pDate));
                holder.DeliverDate.setVisibility(View.VISIBLE);
            }else  {
                holder.DeliverDate.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout Prent, DetailView;
        private ImageView Detail;
        private TextView Name;
        private TextView DeliverDate;
        private TextView Description;
        private TextView Status;
        private TextView OrderNo;

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

            Name = itemView.findViewById(R.id.name);
            DeliverDate = itemView.findViewById(R.id.date);
            Description = itemView.findViewById(R.id.pocket_dec);
            Status = itemView.findViewById(R.id.statusvalue);
            OrderNo = itemView.findViewById(R.id.tracId);
        }
    }

    @Override
    public Filter getFilter() {
        return FilterList;
    }

    private final Filter FilterList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence type) {
            List<AddPakage> filteredList = new ArrayList<>();
            Log.e("QWE", "" + type);
            if (type.equals("all")) {
                filteredList.addAll(FullList);
            } else {
                for (AddPakage item : FullList) {
                    Log.e("ITM", "Item = " + item.toString());
                    if (type.equals("deliverd") && item.getIsDelivered().equals("true")) {
                        filteredList.add(item);
                    } else if (type.equals("inProcess") && item.getInProcess().equals("true") && item.getIsDelivered().equals("false")) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
