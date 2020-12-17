package business.shipitnow.com.ui.courier.tab.api_services.addpackage;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import business.shipitnow.com.APIService;
import business.shipitnow.com.module.AddPakage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories {

    public static Repositories repository;

    private MutableLiveData<Boolean> Loading = new MutableLiveData<>();
    private MutableLiveData<String> Massage = new MutableLiveData<>();


    public MutableLiveData<Boolean> getLoading() {
        return Loading;
    }

    public MutableLiveData<String> getMassage() {
        return Massage;
    }

    public void AddPackage(String image, String BoxName, String BoxDescription, String PickLocation, String DropLocation, String Type, String weight, String QTY, String Date, String service, String pay, String plat, String plng, String dlat, String dlng) {
        Loading.setValue(true);
        Call<AddPakage> UserLogin = APIService.getApiService().AddPakge(image, BoxName, BoxDescription, PickLocation, DropLocation, Type, weight, QTY, Date, service, pay, plat, plng, dlat, dlng, "", "", Date, "");
        UserLogin.enqueue(new Callback<AddPakage>() {
            @Override
            public void onResponse(Call<AddPakage> call, Response<AddPakage> response) {
                Log.e("QWE", "Call : " + call.request().url().toString());
                Log.e("QWE", "Response: " + response.body().toString());
                if (response.body().getStatus().equals("200")) {
                    Massage.setValue("Package Order Add Success");
                } else {
                    Massage.setValue("Package Order Add Failed Please Try Again");
                }
                Loading.setValue(false);
            }

            @Override
            public void onFailure(Call<AddPakage> call, Throwable t) {
                Log.e("QWE", "Prams " + call + " Error " + t.getMessage());
                Loading.setValue(false);
                Massage.setValue("Package Order Add Failed\nPlease Check your Network Conectivity\nAnd Try Again");
            }
        });
    }
}
