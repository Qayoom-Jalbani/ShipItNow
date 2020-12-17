package business.shipitnow.com.ui.courier.tab.api_services.pickup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import business.shipitnow.com.R;
import business.shipitnow.com.adapters.PicUpAdapters;
import business.shipitnow.com.coman.Loading;
import business.shipitnow.com.module.AddPakage;

public class tab_picup_schedule extends Fragment {

    private RecyclerView recyclerView;
    private PicUpAdapters Adapter;
    private ViewModels viewModels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frament_tab_pickup, container, false);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Pick-Up Packages");
        viewModels = new ViewModelProvider(this).get(ViewModels.class);


        viewModels.getLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Loading.ShowLoading(requireActivity());
                } else {
                    Loading.CloseLoading();
                }
            }
        });
        viewModels.getPackage().observe(getViewLifecycleOwner(), new Observer<List<AddPakage>>() {
            @Override
            public void onChanged(List<AddPakage> addPakages) {
                if (addPakages.size() > 0) {
                    List<AddPakage> list = new ArrayList<>();
                    for (AddPakage item : addPakages) {
                        if (item.getInProcess().equals("true") && item.getIsDelivered().equals("false")) {
                            list.add(item);
                        }
                    }
                    Adapter.setList(list);
                }
            }
        });

        recyclerView = root.findViewById(R.id.schedule_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        Adapter = PicUpAdapters.getInstance(requireContext());
        recyclerView.setAdapter(Adapter);

        if (Adapter.list.size() <= 0)
            viewModels.getMyPackage();

        return root;
    }
}
