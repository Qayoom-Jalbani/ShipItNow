package business.shipitnow.com.ui.courier.tab.api_services.dropup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import business.shipitnow.com.R;
import business.shipitnow.com.adapters.PicUpAdapters;

public class tab_delivery_schedule extends Fragment {

    private RecyclerView recyclerView;
    private PicUpAdapters Adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frament_tab_pickup, container, false);

        recyclerView = root.findViewById(R.id.schedule_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        Adapter = new PicUpAdapters(requireContext());
        recyclerView.setAdapter(Adapter);


        return root;
    }
}
