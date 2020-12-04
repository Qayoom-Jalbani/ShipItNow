package business.shipitnow.com.ui.print;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import business.shipitnow.com.R;

public class PrintFragment extends Fragment {

    private Button Upload,Upload1;
    private ConstraintLayout Uploadview,FileInfoView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_print_on_demad, container, false);
        Upload = root.findViewById(R.id.file_upload);
        Upload1 = root.findViewById(R.id.file_upload1);

        Uploadview = root.findViewById(R.id.upload);
        FileInfoView = root.findViewById(R.id.fileInfo);

        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uploadview.setVisibility(View.GONE);
                FileInfoView.setVisibility(View.VISIBLE);
            }
        });

        Upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uploadview.setVisibility(View.VISIBLE);
                FileInfoView.setVisibility(View.GONE);
            }
        });

        return root;
    }
}