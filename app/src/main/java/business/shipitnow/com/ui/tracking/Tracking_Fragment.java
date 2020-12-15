package business.shipitnow.com.ui.tracking;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import business.shipitnow.com.R;


public class Tracking_Fragment extends Fragment {

    WebView simpleWebView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tracking, container, false);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Track Your Shipment");


        simpleWebView = (WebView) root.findViewById(R.id.web);
        simpleWebView.setWebViewClient(new MyWebViewClient());
        String url = "https://www.track-package.net/trk?key=cp8gzpftysjwz6p660gt9crtn1zx3qg3&url=www.shipitnowindy.com&carrier=auto&tn=&custom_activity=&view=&external=0&forceCarrier=1&seckey=2631325e-17e3-481b-988b-0b312ffcb1ff&puri=https%3A%2F%2Fwww.shipitnowindy.com%2FTracking#https%3A%2F%2Fwww.shipitnowindy.com%2FTracking";
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url);

        return root;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
           super.onPageFinished(view, url);
        }
    }


}