package sn.chaka.evaluateurapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

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

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        volleyOutput.setText("Les champs ne peuvent être vides");
    }
}
