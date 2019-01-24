package com.melnichuk.rgr_ziks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.melnichuk.rgr_ziks.R;
import com.melnichuk.rgr_ziks.cryptography.Encryption;
import com.melnichuk.rgr_ziks.cryptography.MessageSignature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button decryptButton;
    private Button encryptButton;
    private TextView encryptedText;
    private EditText editFilename;
    private Button saveButton;
    private Button openButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        encryptButton = findViewById(R.id.encrypt_button);
        decryptButton = findViewById(R.id.decrypt_button);
        encryptedText = findViewById(R.id.encrypted_text);
        editFilename = findViewById(R.id.edit_filename);
        saveButton = findViewById(R.id.save_button);
        openButton = findViewById(R.id.open_button);

        MessageSignature messageSignature = null;
        try {
            messageSignature = new MessageSignature("DSA", 1024, "SHA1withDSA", null);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encryption encryption = new Encryption(editText.getText().toString());
                encryption.encryptText();
                encryptedText.setText(encryption.getOutputText());
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encryption encryption = new Encryption(editText.getText().toString());
                encryption.decryptText();
                encryptedText.setText(encryption.getOutputText());
        }
    });

        final MessageSignature finalMessageSignature = messageSignature;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveTextInFile(editFilename.getText().toString(), encryptedText.getText().toString() , finalMessageSignature);
            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenFile(editFilename.getText().toString());
            }
        });
    }

    public void onSaveTextInFile(String fileName, String text, MessageSignature messageSignature) {
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;

        try {
            fileOutputStream = openFileOutput(fileName + ".txt", MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            Toast.makeText(this, "Файл збережений", Toast.LENGTH_SHORT).show();

            fileInputStream = openFileInput(fileName + ".txt");
            messageSignature.signingMessage(fileInputStream, fileOutputStream);
        } catch (InvalidKeyException | SignatureException | NoSuchProviderException | NoSuchAlgorithmException | IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void onOpenFile(String fileName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(fileName + ".txt");
            String text = "";
            int i=-1;
            while((i=fileInputStream.read())!=-1){
                text += (char)i;
            }
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra("text", text);
            intent.putExtra("fileName", fileName + ".txt");
            startActivity(intent);
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
