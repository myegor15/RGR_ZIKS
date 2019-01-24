package com.melnichuk.rgr_ziks.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.melnichuk.rgr_ziks.R;


public class EditActivity extends AppCompatActivity {

    private EditText editFileText;
    private Button resaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editFileText = findViewById(R.id.edit_file_text);
        resaveButton = findViewById(R.id.resave_button);

        final String text = getIntent().getStringExtra("text");
        final String fileName = getIntent().getStringExtra("fileName");

        editFileText.setText(text);

        resaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickResave(text, fileName);
            }
        });
    }

    public void onClickResave(final String text, final String fileName) {
        final Intent intent = new Intent(this, MainActivity.class);
        if(!editFileText.getText().toString().equals(text)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Помилка збереження")
                    .setMessage("В зашифроване повідомлення внесені зміни! Повернутися до головного меню?")
                    .setCancelable(false) //включает или отключает стандартную кнопку "назад"
                    .setPositiveButton("Так", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(intent);
                        }
                    })
                    .create()
                    .show();
        } else {
            startActivity(intent);
        }
    }
}
