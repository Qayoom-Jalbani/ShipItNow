package business.shipitnow.com.ui.courier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import business.shipitnow.com.R;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class Courier_Fragment extends Fragment {

    private AppBarConfiguration mAppBarConfiguration;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_courier, container, false);

        BottomNavigationView navView = root.findViewById(R.id.bottom_navigation);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_mypackge, R.id.nav_add, R.id.nav_tracking,R.id.nav_pickup,R.id.nav_delivery)
                .build();
        View fragment = root.findViewById(R.id.host_fragment);

        NavController navController = Navigation.findNavController(fragment);

        NavigationUI.setupWithNavController(navView, navController);

        return root;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}