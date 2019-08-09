package sn.ept.evaluateurapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sn.ept.evaluateurapp.models.Memoire;
import sn.ept.evaluateurapp.models.User;
import sn.ept.evaluateurapp.pages.MemoirePage;
import sn.ept.evaluateurapp.webservices.ConnectionService;

public class MainActivity extends AppCompatActivity  implements Validator.ValidationListener{

    private SharedPreferences preferences;
    private SharedPreferences.Editor pEditor;

    @NotEmpty
    private EditText username;
    @NotEmpty
    private EditText password;

    private TextView volleyOutput;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        volleyOutput = findViewById(R.id.volleyOutput);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void connect(View v) {
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        final ConnectionService connection = ConnectionService.retrofit.create(ConnectionService.class);

        final Call<User> call = connection.connect(this.username.getText().toString(), this.password.getText().toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getCode().equals("404")) {
                    volleyOutput.setText("Nom d'utilisateur ou mot de passe incorrects");
                }

                else {
                    preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    pEditor = preferences.edit();
                    pEditor.putString("EVAL_ID", response.body().getCode());
                    pEditor.commit();

                    Intent i = new Intent(getApplicationContext(), MemoirePage.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                volleyOutput.setText("No no");
            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        volleyOutput.setText("Les champs ne peuvent être vides");
    }
}
