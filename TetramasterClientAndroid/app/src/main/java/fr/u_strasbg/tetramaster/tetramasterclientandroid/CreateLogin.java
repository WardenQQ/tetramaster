package fr.u_strasbg.tetramaster.tetramasterclientandroid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateLogin extends AppCompatActivity {
    Button btn_cancel, btn_confirm;
    EditText confirmText, password, pseudo;
    ImageView imagePass, imageConfirm, pseudoImage;
    boolean okPass;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_createlogin);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        pseudo = (EditText) findViewById(R.id.pseudoText);
        password = (EditText) findViewById(R.id.passText);
        confirmText = (EditText) findViewById(R.id.passConfirmText);
        imagePass = (ImageView) findViewById(R.id.passImage);
        imageConfirm = (ImageView) findViewById(R.id.passConfirmImage);
        pseudoImage = (ImageView) findViewById(R.id.pseudoImage);
        final Drawable validated = getResources().getDrawable( R.drawable.validated , getTheme());
        final Drawable refused = getResources().getDrawable( R.drawable.refused , getTheme());
        btn_confirm.setEnabled(false);
        pseudoImage.setImageDrawable(refused);
        imagePass.setImageDrawable(refused);
        imageConfirm.setImageDrawable(refused);
        okPass = false;

        btn_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        pseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(pseudo.getText().length()<6)
                {
                    btn_confirm.setEnabled(false);
                    pseudoImage.setImageDrawable(refused);
                }
                else
                {
                    pseudoImage.setImageDrawable(validated);
                }

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(password.getText().length()<6)
                {
                    btn_confirm.setEnabled(false);
                    imagePass.setImageDrawable(refused);
                    okPass = false;
                }
                else
                {
                    imagePass.setImageDrawable(validated);
                    okPass = true;
                }

            }
        });

        confirmText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (confirmText.getText().toString().compareTo(password.getText().toString()) == 0 && okPass) {
                    btn_confirm.setEnabled(true);
                    imageConfirm.setImageDrawable(validated);
                } else {
                    btn_confirm.setEnabled(false);
                    imageConfirm.setImageDrawable(refused);
                }
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //insert into User values(null,pseudo.getText(),password.getText()
                Toast.makeText(getApplicationContext(), "Votre compte a ete cree", Toast.LENGTH_SHORT).show();
                //retour a la page d'accueil
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
