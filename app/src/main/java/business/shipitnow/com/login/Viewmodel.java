package business.shipitnow.com.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class Viewmodel extends ViewModel {

    private LiveData<String> LoginState;
    private LiveData<Boolean> Loading;
    private LiveData<String> Error;
    private Repository repository;

    public Viewmodel() {

        repository = new Repository();

        LoginState = repository.getLoginState();
        Loading = repository.getLoading();
        Error = repository.getError();
    }

    public LiveData<String> getLoginState() {
        return LoginState;
    }

    public LiveData<Boolean> getLoading() {
        return Loading;
    }

    public LiveData<String> getError() {
        return Error;
    }

    public void getLogin(String user, String pass) {
        repository.getUserLogin(user, pass);
    }
}
