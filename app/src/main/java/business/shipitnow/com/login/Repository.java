package business.shipitnow.com.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import business.shipitnow.com.APIService;
import business.shipitnow.com.module.login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    public static Repository repository;
    public static Repository getRepository(){
        if (repository == null){
            repository = new Repository();
        }
        return repository;
    }

    private MutableLiveData<String> LoginState = new MutableLiveData<>();
    private MutableLiveData<Boolean> Loading = new MutableLiveData<>();
    private MutableLiveData<String> Error = new MutableLiveData<>();


    public LiveData<String> getLoginState() {
        return LoginState;
    }

    public LiveData<Boolean> getLoading() {
        return Loading;
    }

    public LiveData<String> getError() {
        return Error;
    }

    public void getUserLogin(String user, String pass) {
        Call<login> UserLogin = APIService.getApiService().getUserLogin(user, pass);
        UserLogin.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                LoginState.setValue("Success");
                Log.e("QWE", "Call : " + call.request().url().toString());
                Loading.setValue(false);
                Log.e("QWE","Response: "+response.body().toString());
            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {
                Log.e("QWE", "Prams " + call + " Error " + t.getMessage());
                Loading.setValue(false);
            }
        });

        Loading.setValue(true);
    }
}



