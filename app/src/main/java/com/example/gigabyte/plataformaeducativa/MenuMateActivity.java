package com.example.gigabyte.plataformaeducativa;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class MenuMateActivity extends AppCompatActivity {

    RecyclerView menuMateRecycler;
    CaptionedImagesAdapter adapter;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mate);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        menuMateRecycler = findViewById(R.id.menu_mate_recycler);

        String[] mateActNames = new String[MenuMateDatos.datos.length];
        for (int i = 0; i < mateActNames.length; i++) {
            mateActNames[i] = MenuMateDatos.datos[i].getName();
        }

        int[] mateActImages = new int[MenuMateDatos.datos.length];
        for (int i = 0; i < mateActImages.length; i++) {
            mateActImages[i] = MenuMateDatos.datos[i].getImageResourceId();
        }

        adapter = new CaptionedImagesAdapter(mateActNames, mateActImages);
        menuMateRecycler.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        menuMateRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(MenuMateActivity.this, EspanolActivity.class);
                startActivity(intent);
            }
        });
    }
}
