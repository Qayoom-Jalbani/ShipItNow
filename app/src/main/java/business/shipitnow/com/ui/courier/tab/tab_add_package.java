package business.shipitnow.com.ui.courier.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.libraries.places.api.Places;

import business.shipitnow.com.R;

public class tab_add_package extends Fragment {

    private TextView GetLoacation;
    private TextView PickLocation;
    private TextView DropLocation;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tab_add, container, false);

        GetLoacation = root.findViewById(R.id.mapAddress);
        PickLocation = root.findViewById(R.id.pickupLocation);
        DropLocation = root.findViewById(R.id.dropLocation);


        GetLoacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Places.isInitialized()) {
                    String key = requireContext().getResources().getString(R.string.google_maps_key);
                    Places.initialize(requireContext().getApplicationContext(), key);
                }


            }
        });

        return root;
    }
}
