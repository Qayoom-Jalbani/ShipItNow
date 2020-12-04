package business.shipitnow.com.ui.ltl_freight;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LTL_FreightViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LTL_FreightViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}