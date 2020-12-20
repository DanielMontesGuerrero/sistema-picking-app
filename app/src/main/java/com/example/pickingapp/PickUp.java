package com.example.pickingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class PickUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_pickup);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Deciding which fragment will show first
        Fragment firstFragment;
        Intent intent = getIntent();
        if (!intent.getStringExtra("firstFragment").equals("none")) {
            firstFragment = new PickUpFragment();
            bottomNavigationView.setSelectedItemId(R.id.nav_pickup);
            // TODO: Cambiar el titulo del actionbar (PickUp)
        }
        else {
            firstFragment = new AlmacenFragment();
            bottomNavigationView.setSelectedItemId(R.id.nav_almacen);
            // TODO: Cambiar el titulo del actionbar (Almacen)
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_menu_pickup, firstFragment).commit();
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_inicio:
                            Intent intent = new Intent(getBaseContext(), Menu.class);
                            startActivity(intent);
                            return false;
                        case R.id.nav_pickup:
                            selectedFragment = new PickUpFragment();
                            // TODO: Cambiar el titulo del actionbar (PickUp)
                            break;
                        case R.id.nav_cambio:
                            // TODO: Popup - Y cambiar a la modalidad surte almacen
                            Toast.makeText(getApplicationContext(), "Aca debe ir un popup explicando el surte almacen y cambiando a esa modalidad", Toast.LENGTH_SHORT).show();
                            return false;
                        case R.id.nav_almacen:
                            selectedFragment = new AlmacenFragment();
                            // TODO: Cambiar el titulo del actionbar (Almacen)
                            break;
                        case R.id.nav_config:
                            // TODO: Popup - Y cambiar a la modalidad surte almacen
                            Toast.makeText(getApplicationContext(), "Empezar config activity", Toast.LENGTH_SHORT).show();
                            return false;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_menu_pickup, selectedFragment).commit();

                    return true;
                }
            };
}