package business.shipitnow.com.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import business.shipitnow.com.MainActivity;
import business.shipitnow.com.R;
import business.shipitnow.com.coman.Loading;

public class LoginActivity extends AppCompatActivity {


    Button Login;
    private Viewmodel viewModel;
    private EditText UserName,Pass;
    private ImageView Visibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(Viewmodel.class);

        viewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Loading.ShowLoading(LoginActivity.this);
                }else {
                    Loading.CloseLoading();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });
        viewModel.getLoginState().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e("QWE","Error "+s);
            }
        });

        UserName = findViewById(R.id.email) ;
        Pass = findViewById(R.id.password) ;
        Visibility = findViewById(R.id.visibility) ;
        Visibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String visible = v.getTag().toString();
                switch (visible){
                    case "true":
                        Pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        Visibility.setTag("false");
                        Visibility.setImageResource(R.drawable.ic_visibility);
                        break;
                    case "false":
                        Pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        Visibility.setImageResource(R.drawable.ic_visibility_off);
                        Visibility.setTag("true");
                        break;
                }
            }
        });


        Login = findViewById(R.id.login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = UserName.getText().toString();
                String pass = Pass.getText().toString();
                if (user.equals("")){
                    FocusError(UserName);
                    return;
                }
                if (pass.equals("")){
                    FocusError(Pass);
                    return;
                }
                viewModel.getLogin(user,pass);
            }
        });
    }


    private void FocusError(EditText editText){
        editText.requestFocus();
        editText.setHintTextColor(Color.parseColor("#E74C3C"));
        editText.setBackgroundResource(R.drawable.edittext_error_background);
    }
}