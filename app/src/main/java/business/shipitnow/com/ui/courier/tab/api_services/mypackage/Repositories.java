package business.shipitnow.com.ui.courier.tab.api_services.mypackage;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import business.shipitnow.com.APIService;
import business.shipitnow.com.module.AddPakage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories {

    public static Repositories repository;

    private MutableLiveData<Boolean> Loading = new MutableLiveData<>();
    private MutableLiveData<List<AddPakage>> List = new MutableLiveData<>();


    public MutableLiveData<Boolean> getLoading() {
        return Loading;
    }

    public MutableLiveData<List<AddPakage>> getMassage() {
        return List;
    }

    public void AddPackage() {
        Loading.setValue(true);
        Call<List<AddPakage>> GetPackage = APIService.getApiService().GetPackage();
        GetPackage.enqueue(new Callback<java.util.List<AddPakage>>() {
            @Override
            public void onResponse(Call<java.util.List<AddPakage>> call, Response<java.util.List<AddPakage>> response) {
                List.setValue(response.body());
                Loading.setValue(false);
                Log.e("QWR",response.body().toString());
            }

            @Override
            public void onFailure(Call<java.util.List<AddPakage>> call, Throwable t) {
                Loading.setValue(false);
            }
        });

    }
}
