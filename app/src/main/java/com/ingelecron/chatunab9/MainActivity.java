package com.ingelecron.chatunab9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.ingelecron.chatunab9.adaptadores.AdaptadorFragment;
import com.ingelecron.chatunab9.vistas.activity.LoginActivity;
import com.ingelecron.chatunab9.vistas.perfil.PerfilActivity;
import com.ingelecron.chatunab9.vistas.fragment.tab1.Tab1Fragment;
import com.ingelecron.chatunab9.vistas.fragment.Tab2Fragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);

        int[] tituloTab= new int[]{R.string.tab1, R.string.tab2};

        viewPager.setAdapter(new AdaptadorFragment(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                this, arrayFragment(), tituloTab));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_mas);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_persona);
    }

    private ArrayList<Fragment> arrayFragment() {

        ArrayList<Fragment> fragmentArrayList= new ArrayList<>();
        fragmentArrayList.add(new Tab1Fragment());
        fragmentArrayList.add(new Tab2Fragment());

        return fragmentArrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;

        switch (item.getItemId()){

            case R.id.perfil:
                startActivity(new Intent(MainActivity.this, PerfilActivity.class));
                return true;
            case R.id.saludo:
                Toast.makeText(this, "Saludo", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.notificacion:
                Toast.makeText(this, "Notificacion", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mapa:
                Toast.makeText(this, "mapa", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.archivo:
                Toast.makeText(this, "archivo", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.configuracion:
                Toast.makeText(this, "configuracion", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cerrar:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}