package com.ingelecron.chatunab9.vistas.perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ingelecron.chatunab9.R;
import com.ingelecron.chatunab9.vistas.perfil.PerfilFragment;

public class PerfilActivity extends AppCompatActivity {

    private ImageButton iv_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        iv_perfil= findViewById(R.id.iv_perfil);
        iv_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_perfil, new PerfilFragment(), PerfilFragment.class.getSimpleName())
                .commit();
    }
}