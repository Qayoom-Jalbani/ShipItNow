package business.shipitnow.com.ui.courier.tab;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import java.util.Calendar;

import business.shipitnow.com.R;
import business.shipitnow.com.coman.Place_Picker;

public class tab_add_package extends Fragment {

    private TextView GetLoacation;
    private TextView PickLocation;
    private TextView DropLocation;
    private TextView Date;
    private Place_Picker place_picker;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tab_add, container, false);

        GetLoacation = root.findViewById(R.id.mapAddress);
        PickLocation = root.findViewById(R.id.pickupLocation);
        DropLocation = root.findViewById(R.id.dropLocation);
        Date = root.findViewById(R.id.pickDate);

        place_picker = new Place_Picker(requireActivity());


        GetLoacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place_picker.ShowLoading(PickLocation, "Pin To Pick-Up Location");
            }
        });

        PickLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        place_picker.ShowLoading(DropLocation, "Pin To Drop Location");
                    }
                }, 500);
                Log.e("QWE", "Show Dialog Again");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                DatePickerDialog datePicker = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String SelectedData = dayOfMonth+"/"+month+"/"+year;
                        Date.setText(SelectedData);

                    }
                },
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                //Create a cancel button and set the title of the dialog.
                datePicker.setCancelable(false);
                datePicker.setTitle("Select the date");
                datePicker.show();
            }
        });


        return root;
    }
}
