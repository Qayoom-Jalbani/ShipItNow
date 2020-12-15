package business.shipitnow.com.coman;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import business.shipitnow.com.R;

public class Loading {

    private static AlertDialog alertDialog;
    private static int Desh = -1;

    public static void ShowLoading(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(activity);
        View view2 = layoutInflaterAndroid.inflate(R.layout.loadin_view, null);
        builder.setView(view2);
        TextView Loading = view2.findViewById(R.id.desh);
        textanimte(Loading);

        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    public static void CloseLoading() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private static void textanimte(final TextView textView) {
        Desh++;
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Desh == 0) {
                    textView.setText("");
                } else if (Desh == 1) {
                    textView.setText(".");
                } else if (Desh == 2) {
                    textView.setText("..");
                } else if (Desh == 3) {
                    textView.setText("...");
                    Desh = -1;
                }
                textanimte(textView);
            }
        }, 800);
    }
}
