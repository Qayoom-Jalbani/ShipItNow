package business.shipitnow.com.ui.courier.tab.api_services.addpackage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import business.shipitnow.com.ui.courier.tab.api_services.addpackage.Repositories;

public class ViewModels extends ViewModel {

    private Repositories repositories;
    private LiveData<String>Massage;
    private LiveData<Boolean>Loading;

    public ViewModels() {
        repositories = new Repositories();
        Massage = repositories.getMassage();
        Loading = repositories.getLoading();
    }

    public LiveData<String> getMassage() {
        return Massage;
    }

    public LiveData<Boolean> getLoading() {
        return Loading;
    }

    public void AddPackage(String image, String BoxName, String BoxDescription, String
            PickLocation, String DropLocation, String Type, String weight, String QTY,
                           String Date, String service, String pay, String plat, String plng, String dlat, String dlng) {
        repositories.AddPackage(image, BoxName, BoxDescription,
                PickLocation, DropLocation, Type, weight, QTY,
                Date, service, pay, plat, plng, dlat, dlng);
    }
}
