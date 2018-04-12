package com.example.a16254868.usuarioasteroide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeUsuarioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView list_view;

    ViagemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list_view = (ListView) findViewById(R.id.list_view);

        List<Viagem> lstViagem = new ArrayList<>();

        //dados fakes

        lstViagem.add(new Viagem("Rio de Janeiro"));
        lstViagem.add(new Viagem("São Paulo"));
        lstViagem.add(new Viagem("Gramados"));
        lstViagem.add(new Viagem("Jardim Japão"));


        adapter = new ViagemAdapter(this, lstViagem);

        list_view.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            // Handle the camera action
        } else if (id == R.id.nav_compraPassagem) {

        } else if (id == R.id.nav_notificacao) {

        } else if (id == R.id.nav_acompanhamento) {

        } else if (id == R.id.nav_noticias) {
            startActivity(new Intent(getApplicationContext(), NoticiasUsuarioActivity.class));
        } else if (id == R.id.nav_mapa) {

        } else if (id == R.id.nav_historico) {

        } else if (id == R.id.nav_contato) {

        } else if (id == R.id.nav_qrcode) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //classe do adapter
    private class ViagemAdapter extends ArrayAdapter<Viagem> {

        public ViagemAdapter(Context ctx, List<Viagem> items){
            super(ctx, 0, items);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View v = convertView;

            if(v == null){
                v = LayoutInflater.from(getContext()).inflate(R.layout.item_lista, null);
            }

            Viagem item = getItem(position);

            TextView tltViagem = v.findViewById(R.id.tltViagem);

            tltViagem.setText(item.getNome());

            return v;
        }
    }
}
