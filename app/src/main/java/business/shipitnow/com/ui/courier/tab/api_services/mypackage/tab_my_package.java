package business.shipitnow.com.ui.courier.tab.api_services.mypackage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import business.shipitnow.com.R;
import business.shipitnow.com.adapters.MyPackageAdapters;
import business.shipitnow.com.coman.Loading;
import business.shipitnow.com.module.AddPakage;

public class tab_my_package extends Fragment {

    private RecyclerView recyclerView;
    private MyPackageAdapters Adapter;
    private ViewModels viewModels;

    private ConstraintLayout GetStart, MyPackage;
    private CheckBox All, InProgress, Delivered;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tab_mypackage, container, false);
        viewModels = new ViewModelProvider(this).get(ViewModels.class);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("My Packages");

        viewModels.getLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean && Adapter.list.size() <= 0) {
                    Loading.ShowLoading(requireActivity());
                } else {
                    Loading.CloseLoading();
                    Loading.CloseLoading();
                }
                Log.e("QWE","loading "+aBoolean);
            }
        });
        viewModels.getPackage().observe(getViewLifecycleOwner(), new Observer<List<AddPakage>>() {
            @Override
            public void onChanged(List<AddPakage> addPakages) {
                if (addPakages.size() > 0) {
                    Adapter.setList(addPakages);
                    GetStart.setVisibility(View.GONE);
                    MyPackage.setVisibility(View.VISIBLE);
                } else {
                    GetStart.setVisibility(View.VISIBLE);
                    MyPackage.setVisibility(View.GONE);
                }
            }
        });

        All = root.findViewById(R.id.all);
        InProgress = root.findViewById(R.id.inProcess);
        Delivered = root.findViewById(R.id.deliverd);
        All.setOnClickListener(CheckClick);
        InProgress.setOnClickListener(CheckClick);
        Delivered.setOnClickListener(CheckClick);
        ClickAble(All);
        ClickAble(InProgress);
        ClickAble(Delivered);


        GetStart = root.findViewById(R.id.start);
        MyPackage = root.findViewById(R.id.list);

        recyclerView = root.findViewById(R.id.myPackage);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        Adapter = MyPackageAdapters.getInstance(requireContext());
        recyclerView.setAdapter(Adapter);

        if (Adapter.list.size() <= 0)
            viewModels.getMyPackage();

        return root;
    }

    private void ClickAble(CheckBox box) {
        if (box.isChecked()) {
            box.setClickable(false);
            box.setFocusable(false);
        } else {
            box.setClickable(true);
            box.setFocusable(true);
        }
    }

    View.OnClickListener CheckClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = v.getTag().toString();
            if (tag.equals("all")) {
                InProgress.setChecked(false);
                Delivered.setChecked(false);
                Adapter.getFilter().filter("all");
            } else if (tag.equals("inProcess")) {
                All.setChecked(false);
                Delivered.setChecked(false);
                Adapter.getFilter().filter("inProcess");
            } else if (tag.equals("deliverd")) {
                All.setChecked(false);
                InProgress.setChecked(false);
                Adapter.getFilter().filter("deliverd");
            }
            ClickAble(All);
            ClickAble(InProgress);
            ClickAble(Delivered);
        }
    };
}
