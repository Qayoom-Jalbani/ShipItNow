package business.shipitnow.com.ui.print;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import business.shipitnow.com.APIService;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


public class PrintRepository {

    public static PrintRepository repository;
    private MutableLiveData<Boolean> Loading = new MutableLiveData<>();
    private MutableLiveData<ResponseBody> Response = new MutableLiveData<>();

    public static PrintRepository getRepository() {
        if (repository == null) {
            repository = new PrintRepository();
        }
        return repository;
    }

    public MutableLiveData<Boolean> getLoading() {
        return Loading;
    }

    public MutableLiveData<ResponseBody> getResponse() {
        return Response;
    }


    public void UploadFiles(RequestBody  Name, RequestBody  Email, RequestBody  Company, RequestBody  Intro, MultipartBody.Part File1, MultipartBody.Part File2, MultipartBody.Part File3, MultipartBody.Part File4, MultipartBody.Part File5, RequestBody  n1, RequestBody  n2, RequestBody  n3, RequestBody  n4, RequestBody n5) {
        Loading.setValue(true);
        Call<ResponseBody> UploadFile = APIService.getApiService().UploadFiles(Name, Email, Company, Intro, File1, File2, File3, File4, File5,n1,n2,n3,n4,n5);
        UploadFile.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                /*Log.e("QWE","Url "+call.request().url());
                Log.e("QWE","Url "+call.request());
                Log.e("QWE","Url "+call.request().body().contentType());
                Log.e("QWE","Res "+response.message());*/
                Response.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("QWE","Url "+call.request().url());
                Log.e("QWE"," request throw Exception Error  "+t.getMessage());
            }
        });
    }
}
