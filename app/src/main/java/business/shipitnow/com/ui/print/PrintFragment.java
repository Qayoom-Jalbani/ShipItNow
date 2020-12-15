package business.shipitnow.com.ui.print;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import business.shipitnow.com.FileUtil;
import business.shipitnow.com.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


import static android.app.Activity.RESULT_OK;

public class PrintFragment extends Fragment {

    private Button Upload, DeleteAll;
    private ConstraintLayout Uploadview;
    private LinearLayout Flie1, Flie2, Flie3, Flie4, Flie5;
    private TextView FileName1, FileName2, FileName3, FileName4, FileName5;
    private ImageView FileIcon1, FileIcon2, FileIcon3, FileIcon4, FileIcon5;
    private Button DeleteIcon1, DeleteIcon2, DeleteIcon3, DeleteIcon4, DeleteIcon5;
    private PrintViewModel printViewModel;

    private EditText FullName, Email, Phone, Company;
    private TextView Cost;

    private Button Print;
    List<Uri> Files = new ArrayList<>();
    private Uri uri;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        printViewModel = new ViewModelProvider(this).get(PrintViewModel.class);
        View root = inflater.inflate(R.layout.fragment_print_on_demad, container, false);
        printViewModel.getLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });

        printViewModel.getResponse().observe(getViewLifecycleOwner(), new Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody responseBody) {
                Toast.makeText(requireContext(), "File Uploaded Successfully", Toast.LENGTH_LONG).show();
            }
        });
        Print = root.findViewById(R.id.print);
        Print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File filePath = new File(Files.get(0).getPath());
                try {
                    File Fi1 = FileUtil.from(requireActivity(), Files.get(0));
                    RequestBody F1 = RequestBody.create(Fi1, MediaType.parse(requireContext().getContentResolver().getType(Files.get(0))));
                    MultipartBody.Part file = MultipartBody.Part.createFormData("File1", filePath.getName(), F1);
                    MultipartBody.Part file1 = MultipartBody.Part.createFormData("File2", filePath.getName(), F1);
                    MultipartBody.Part file2 = MultipartBody.Part.createFormData("File3", filePath.getName(), F1);
                    MultipartBody.Part file3 = MultipartBody.Part.createFormData("File4", filePath.getName(), F1);
                    MultipartBody.Part file4 = MultipartBody.Part.createFormData("File5", filePath.getName(), F1);

                   /* RequestBody requestFile = RequestBody.create(
                                    MediaType.parse(requireContext().getContentResolver().getType(uri)),
                                    file
                            );*/

                    String descriptionString = "hello, this is description speaking";
                    RequestBody Name =
                            RequestBody.create(
                                    descriptionString, okhttp3.MultipartBody.FORM);

                    printViewModel.UploadFiles(Name, Name, Name, Name, file, file1, file2, file3, file4, Name, Name, Name, Name, Name);
                } catch (Exception e) {
                    Log.e("QWE", "Exception " + e.getMessage());
                }
            }
        });

        Upload = root.findViewById(R.id.uploadfile);
        Uploadview = root.findViewById(R.id.upload);
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Flie5.getVisibility() == View.VISIBLE) {
                    Toast.makeText(requireContext(), "Five (5) Files Limit Only", Toast.LENGTH_SHORT).show();
                    return;
                }
                showFileChooser();
            }
        });

        DeleteAll = root.findViewById(R.id.deleteAll);
        DeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Files.clear();
                Flie1.setVisibility(View.GONE);
                Flie2.setVisibility(View.GONE);
                Flie3.setVisibility(View.GONE);
                Flie4.setVisibility(View.GONE);
                Flie5.setVisibility(View.GONE);
            }
        });

        FullName = root.findViewById(R.id.name);
        Email = root.findViewById(R.id.email);
        Company = root.findViewById(R.id.number);
        Phone = root.findViewById(R.id.company);
        Cost = root.findViewById(R.id.cost);

        Flie1 = root.findViewById(R.id.file1);
        Flie2 = root.findViewById(R.id.file2);
        Flie3 = root.findViewById(R.id.file3);
        Flie4 = root.findViewById(R.id.file4);
        Flie5 = root.findViewById(R.id.file5);

        FileName1 = root.findViewById(R.id.f_name1);
        FileName2 = root.findViewById(R.id.f_name2);
        FileName3 = root.findViewById(R.id.f_name3);
        FileName4 = root.findViewById(R.id.f_name4);
        FileName5 = root.findViewById(R.id.f_name5);

        FileIcon1 = root.findViewById(R.id.f_icon1);
        FileIcon2 = root.findViewById(R.id.f_icon2);
        FileIcon3 = root.findViewById(R.id.f_icon3);
        FileIcon4 = root.findViewById(R.id.f_icon4);
        FileIcon5 = root.findViewById(R.id.f_icon5);


        DeleteIcon1 = root.findViewById(R.id.delete1);
        DeleteIcon1.setOnClickListener(RemoveItem);
        DeleteIcon2 = root.findViewById(R.id.delete2);
        DeleteIcon2.setOnClickListener(RemoveItem);
        DeleteIcon3 = root.findViewById(R.id.delete3);
        DeleteIcon3.setOnClickListener(RemoveItem);
        DeleteIcon4 = root.findViewById(R.id.delete4);
        DeleteIcon4.setOnClickListener(RemoveItem);
        DeleteIcon5 = root.findViewById(R.id.delete5);
        DeleteIcon5.setOnClickListener(RemoveItem);

        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK & data != null) {
                Uploadview.setVisibility(View.GONE);
                Uri FileURI = data.getData();
                uri = FileURI;
                AddFile(FileURI);

                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void AddFile(Uri FileURI) {
        String path = FileURI.getPath();

        File filePath = new File(path);
        File file = new File(requireContext().getFilesDir().getAbsolutePath(), filePath.getName());
        String Name = file.getName();
        String fPath = filePath.getPath();
        String ext = fPath.substring(fPath.lastIndexOf(".") + 1); // Without dot jpg, png

        if (Flie1.getVisibility() == View.GONE) {
            FileName1.setText(Name);
            Flie1.setVisibility(View.VISIBLE);
            setIcon(FileIcon1, ext);
            Files.add(0, FileURI);
            return;
        }
        if (Flie2.getVisibility() == View.GONE) {
            FileName2.setText(Name);
            Flie2.setVisibility(View.VISIBLE);
            setIcon(FileIcon2, ext);
            Files.add(1, FileURI);
            return;
        }
        if (Flie3.getVisibility() == View.GONE) {
            FileName3.setText(Name);
            Flie3.setVisibility(View.VISIBLE);
            setIcon(FileIcon3, ext);
            Files.add(2, FileURI);
            return;
        }
        if (Flie4.getVisibility() == View.GONE) {
            FileName4.setText(Name);
            Flie4.setVisibility(View.VISIBLE);
            setIcon(FileIcon4, ext);
            Files.add(3, FileURI);
            return;
        }
        if (Flie5.getVisibility() == View.GONE) {
            FileName5.setText(Name);
            Flie5.setVisibility(View.VISIBLE);
            setIcon(FileIcon5, ext);
            Files.add(4, FileURI);
            return;
        }
    }

    private void setIcon(ImageView icon, String ext) {
        if (ext.equals("pdf")) {
            icon.setImageResource(R.drawable.pdf);
        } else if (ext.equals("docx") || ext.equals("ppt") || ext.equals("xls") || ext.equals("doc")) {
            icon.setImageResource(R.drawable.word);
        } else if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png")) {
            icon.setImageResource(R.drawable.ic_image);
        } else if (ext.equals("zip")) {
            icon.setImageResource(R.drawable.ic_zip);
        } else {
            icon.setImageResource(R.drawable.photoshop_icon);
        }
    }

    View.OnClickListener RemoveItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.delete1) {
                Flie1.setVisibility(View.GONE);
                Files.remove(0);
            } else if (v.getId() == R.id.delete2) {
                Flie2.setVisibility(View.GONE);
                Files.remove(1);
            } else if (v.getId() == R.id.delete3) {
                Flie3.setVisibility(View.GONE);
                Files.remove(2);
            } else if (v.getId() == R.id.delete4) {
                Flie4.setVisibility(View.GONE);
                Files.remove(3);
            } else if (v.getId() == R.id.delete5) {
                Flie5.setVisibility(View.GONE);
                Files.remove(4);
            }
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    1);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}