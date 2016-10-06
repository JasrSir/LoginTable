package com.jasrsir.logintable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jasrsir.logintable.controllers.ILoginMvc;
import com.jasrsir.logintable.controllers.LoginTable_Controller;

public class MainActivityLogin extends AppCompatActivity {

    //Variables de clase de instancia
    private ILoginMvc loginMvc;
    private EditText medtUser;
    private EditText medtPassword;
    private Button mbtnOk;
    private Button mbtnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        //Inicialization
        loginMvc = new LoginTable_Controller();
        medtUser = (EditText) findViewById(R.id.edUser);
        medtPassword = (EditText) findViewById(R.id.edtPass);
        mbtnOk = (Button) findViewById(R.id.btnOk);

        mbtnOk.setOnClickListener(new View.OnClickListener() {
            //Controlamos las reglas de negocio... 8 caract min, 1 mayus, 1 minus, 1 digit
            @Override
            public void onClick(View v) {
                String user = medtUser.getText().toString();
                String pass = medtPassword.getText().toString();
                //TODO TextUtils.loquequiera para evitar el nullPointerException
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    //TODO getResources().getString(R.string.data_empty) esto se usa para los recursos que añadimos
                    Toast.makeText(MainActivityLogin.this, getResources().getString(R.string.data_empty), Toast.LENGTH_LONG).show();
                } else {
                    int result = loginMvc.validateCredentials(user,pass);
                    //TODO hacemos un switch para el resultado
                    switch (result) {
                        case LoginTable_Controller.PASSWORD_DIGIT:
                            Toast.makeText(MainActivityLogin.this,
                                    getResources().getString(R.string.password_digit), Toast.LENGTH_LONG).show();
                            break;
                        case LoginTable_Controller.PASSWORD_CASE:
                            Toast.makeText(MainActivityLogin.this,
                                    getResources().getString(R.string.password_case), Toast.LENGTH_LONG).show();
                            break;
                        case LoginTable_Controller.PASSWORD_LENGTH:
                            Toast.makeText(MainActivityLogin.this,
                                    getResources().getString(R.string.password_length), Toast.LENGTH_LONG).show();
                            break;
                        case LoginTable_Controller.OK:
                            //Launch next Activity
                            break;
                    }
                }
            }
        });

        mbtnCancel = (Button) findViewById(R.id.btnCancel);
        mbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });
    }

    private void resetValues() {
        medtPassword.setText("");
        medtUser.setText("");
    }
}
