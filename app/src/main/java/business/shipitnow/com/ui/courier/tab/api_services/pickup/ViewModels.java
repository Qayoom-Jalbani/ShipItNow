package business.shipitnow.com.ui.courier.tab.api_services.pickup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import business.shipitnow.com.module.AddPakage;
import business.shipitnow.com.ui.courier.tab.api_services.mypackage.Repositories;

public class ViewModels extends ViewModel {

    private Repositories repositories;
    private LiveData<List<AddPakage>>Massage;
    private LiveData<Boolean>Loading;


    public ViewModels() {
        repositories = Repositories.getRepository();
        Massage = repositories.getMassage();
        Loading = repositories.getLoading();
    }

    public LiveData<List<AddPakage>> getPackage() {
        return Massage;
    }

    public LiveData<Boolean> getLoading() {
        return Loading;
    }

    public void getMyPackage(){
        repositories.AddPackage();
    }


}
