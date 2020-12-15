package business.shipitnow.com.ui.print;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import business.shipitnow.com.login.Repository;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class PrintViewModel extends ViewModel {

    private PrintRepository repository;
    private LiveData<Boolean> Loading;
    private LiveData<ResponseBody> Response;

    public PrintViewModel() {
        repository = PrintRepository.getRepository();
        Loading = repository.getLoading();
        Response = repository.getResponse();
    }

    public LiveData<Boolean> getLoading() {
        return Loading;
    }

    public LiveData<ResponseBody> getResponse() {
        return Response;
    }

    public void UploadFiles(RequestBody  Name, RequestBody  Email, RequestBody  Company, RequestBody  Intro, MultipartBody.Part File1, MultipartBody.Part File2, MultipartBody.Part File3, MultipartBody.Part File4, MultipartBody.Part File5, RequestBody  n1, RequestBody  n2, RequestBody  n3, RequestBody  n4, RequestBody n5) {
        repository.UploadFiles(Name, Email, Company, Intro, File1, File2, File3, File4, File5,n1,n2,n3,n4,n5);
    }
}