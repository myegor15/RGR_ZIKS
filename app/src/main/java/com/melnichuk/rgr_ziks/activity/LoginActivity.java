package com.melnichuk.rgr_ziks.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.melnichuk.rgr_ziks.R;
import com.melnichuk.rgr_ziks.UserInformation;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEdit;
    private EditText passwordEdit;

    private byte counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEdit = findViewById(R.id.login_edit);
        passwordEdit = findViewById(R.id.password_edit);
    }

    public void onLogin(View view) {
        boolean goNextActivity = false;
        for (int i = 0; i < 3; i++) {
            if (loginEdit.getText().toString().hashCode() == UserInformation.USERS[i][0].hashCode() &&
                    passwordEdit.getText().toString().hashCode() == UserInformation.USERS[i][1].hashCode()) {
                goNextActivity = true;
            }
        }
        if (goNextActivity) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            counter++;
            if (counter == 3) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Помилка вводу")
                        .setMessage("Ви тричі ввели неправильний логін або пароль! Додаток буде закрито!")
                        .setCancelable(false) //включает или отключает стандартную кнопку "назад"
                        .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .create()
                        .show();
            }
        }
    }
}
