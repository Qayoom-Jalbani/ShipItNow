package business.shipitnow.com.ui.courier.tab.api_services.addpackage;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import business.shipitnow.com.R;
import business.shipitnow.com.coman.Loading;
import business.shipitnow.com.coman.Place_Picker;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class tab_add_package extends Fragment {

    private TextView PickLocation;
    private TextView DropLocation;
    private TextView Date;
    private Place_Picker place_picker;
    private CheckBox Courier, LTL, Self, CashOn;
    private EditText BoxName, BoxDescription, QTY, Weight, Dimesion;
    private ImageView image;

    private Button AddPackage;
    private ViewModels viewModels;

    private int pay = 0;
    private int service = 0;
    private Spinner type,Pkg_type_id;
    private String Type;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tab_add, container, false);
        viewModels = new ViewModelProvider(this).get(ViewModels.class);

        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Add Package");
        //viewModels.getLoading().removeObservers(getViewLifecycleOwner());
        viewModels.getLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    AddPackage.setEnabled(false);
                    Loading.ShowLoading(requireActivity());
                } else {
                    AddPackage.setEnabled(true);
                    Loading.CloseLoading();
                }
            }
        });
        viewModels.getMassage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                if (s.equals("Package Order Add Success")) {
                    ClearView();
                }
                Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        QTY = root.findViewById(R.id.packets_qty);
        Weight = root.findViewById(R.id.packets_weight);
        Dimesion = root.findViewById(R.id.packets_dem);
        type = root.findViewById(R.id.pkg_type);
        Pkg_type_id = root.findViewById(R.id.pkg_type_id);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              switch (position){
                  case 0:
                      QTY.setVisibility(View.VISIBLE);
                      Pkg_type_id.setVisibility(View.GONE);
                      break;
                  case 1:
                      QTY.setVisibility(View.VISIBLE);
                      Pkg_type_id.setVisibility(View.GONE);
                      break;
                  case 2:
                      QTY.setVisibility(View.INVISIBLE);
                      Pkg_type_id.setVisibility(View.VISIBLE);
                      break;
                  case 3:
                      QTY.setVisibility(View.INVISIBLE);
                      Pkg_type_id.setVisibility(View.VISIBLE);
                      break;

              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        BoxName = root.findViewById(R.id.pickName);
        BoxDescription = root.findViewById(R.id.pickDesc);
        Courier = root.findViewById(R.id.courier);
        LTL = root.findViewById(R.id.ltl);
        Self = root.findViewById(R.id.payself);
        CashOn = root.findViewById(R.id.cashondelivery);
        Courier.setOnClickListener(onClickListener);
        ClickAble(Courier);
        LTL.setOnClickListener(onClickListener);
        ClickAble(LTL);

        Self.setOnClickListener(onClickListener);
        ClickAble(Self);
        CashOn.setOnClickListener(onClickListener);
        ClickAble(CashOn);

        image = root.findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        PickLocation = root.findViewById(R.id.pickupLocation);
        DropLocation = root.findViewById(R.id.dropLocation);
        Date = root.findViewById(R.id.pickDate);

        place_picker = new Place_Picker(requireActivity());

        ImageView Pick = root.findViewById(R.id.pick);
        Pick.setOnClickListener(GetLocation);
        ImageView Drop = root.findViewById(R.id.drop);
        Drop.setOnClickListener(GetLocation);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                DatePickerDialog datePicker = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String SelectedData = year + "-" + month + "-" + dayOfMonth;
                        SelectedData = SelectedData.replaceAll(" ", "");
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

        AddPackage = root.findViewById(R.id.addPackage);
        AddPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {

                    String dLatLan = DropLocation.getTag().toString();
                    dLatLan = dLatLan.replaceAll("LatLng:", "");
                    dLatLan = dLatLan.replaceAll(" ", "");
                    Log.e("QWE", "LatLng : " + dLatLan);
                    String[] dlat = dLatLan.split("/");

                    String pLatLan = PickLocation.getTag().toString();
                    pLatLan = pLatLan.replaceAll("LatLng:", "");
                    pLatLan = pLatLan.replaceAll(" ", "");
                    String[] plat = pLatLan.split("/");

                    viewModels.AddPackage("nill", BoxName.getText().toString(), BoxDescription.getText().toString(),
                            PickLocation.getText().toString(), DropLocation.getText().toString(), Type,
                            Weight.getText().toString(),
                            QTY.getText().toString(), Date.getText().toString(), String.valueOf(service), String.valueOf(pay),
                            plat[0], plat[1], dlat[0], dlat[1]);
                } else {
                    Log.e("QWE", "Some Thing is wrong");
                }
            }
        });
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

    private View.OnClickListener GetLocation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = v.getTag().toString();
            if (tag.equals("Pin To Pick-Up Location"))
                place_picker.ShowLoading(PickLocation, tag);
            else
                place_picker.ShowLoading(DropLocation, tag);
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CheckBox box = (CheckBox) v;
            String TAG = box.getTag().toString();
            String Text = box.getText().toString();

            if (TAG.equals("serviceType")) {
                if (Text.equals("Throw Courier")) {
                    LTL.setChecked(false);
                    Courier.setChecked(true);
                    service = 0;
                } else if (Text.equals("Throw LtL")) {
                    Courier.setChecked(false);
                    LTL.setChecked(true);
                    service = 1;
                }
                ClickAble(LTL);
                ClickAble(Courier);
            } else {
                if (Text.equals("Self Payment")) {
                    CashOn.setChecked(false);
                    Self.setChecked(true);
                    pay = 0;
                } else if (Text.equals("Cash on Delivery")) {
                    Self.setChecked(false);
                    CashOn.setChecked(true);
                    pay = 1;
                }
                ClickAble(Self);
                ClickAble(CashOn);
            }


        }
    };

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.e("QWE", "Image Recevice");
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        image.setImageBitmap(selectedImage);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), selectedImage);
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                            Log.e("Activity", "Pick from Gallery::>>> ");
                            image.setImageBitmap(bitmap);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private boolean validation() {
        boolean valid = true;
        if (BoxName.getText().toString().equals("")) {
            BoxName.requestFocus();
            valid = false;
        }
        if (BoxDescription.getText().toString().equals("")) {
            BoxDescription.requestFocus();
            valid = false;
        }
        if (PickLocation.getText().toString().equals("")) {
            PickLocation.requestFocus();
            valid = false;
        }
        if (DropLocation.getText().toString().equals("")) {
            DropLocation.requestFocus();
            valid = false;
        }
        if (QTY.getText().toString().equals("")) {
            QTY.requestFocus();
            valid = false;
        }
        if (Weight.getText().toString().equals("")) {
            Weight.requestFocus();
            valid = false;
        }
        if (Dimesion.getText().toString().equals("")) {
            Dimesion.requestFocus();
            valid = false;
        }
        if (Date.getText().toString().equals("Pic Date")) {
            BoxName.requestFocus();
            Toast.makeText(requireContext(), "Please Select Date", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        Type = type.getSelectedItem().toString();
        if (Type.equals("Select Item Type")) {
            Toast.makeText(requireContext(), "Please Select Item Type", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    private void ClearView() {
        BoxName.setText("");
        BoxDescription.setText("");
        Weight.setText("");
        Dimesion.setText("");
        QTY.setText("");
        type.setSelection(0);
        PickLocation.setText("");
        PickLocation.setText("");
        Date.setTag("Pic Date");
        DropLocation.setText("");
        DropLocation.setTag("");
    }
}
