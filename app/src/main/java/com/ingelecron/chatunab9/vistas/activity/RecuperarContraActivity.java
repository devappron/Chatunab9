package com.ingelecron.chatunab9.vistas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.ingelecron.chatunab9.R;
import com.ingelecron.chatunab9.controladores.RecuperarContraControlador;
import com.ingelecron.chatunab9.utiles.ValidarCorreo;

public class RecuperarContraActivity extends AppCompatActivity {

    private TextInputEditText tie_correo;
    private Button b_enviarcorreo;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra);

        view= findViewById(R.id.cl_recuperarcontra);

        tie_correo=findViewById(R.id.tie_correo);
        b_enviarcorreo=findViewById(R.id.b_enviarcorreo);

        tie_correo.addTextChangedListener(textWatcher);

        b_enviarcorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecuperarContraControlador.recuperar(RecuperarContraActivity.this, getCorreo());
            }
        });

    }

    private TextWatcher textWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            habilitar();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private boolean habilitar(){

        String correo=getCorreo().trim();

        if(ValidarCorreo.validar(correo)){
            b_enviarcorreo.setEnabled(true);
            return true;
        }else {
            b_enviarcorreo.setEnabled(false);
            return false;
        }
    }

    private String getCorreo() {
        return tie_correo.getText().toString();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ocultarTeclado(this, view);
        tie_correo.clearFocus();
        return true;
    }

    private void ocultarTeclado(Context contexto, View view) {
        InputMethodManager inputMethodManager= (InputMethodManager) contexto.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}