package com.example.a16254868.usuarioasteroide;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import models.Viagem;
import utils.HttpConnection;

import static utils.ConverterDinheiroKt.converterDinheiro;
import static utils.IPServidorKt.ipServidorComPorta;
import static utils.IPServidorKt.ipServidorSemPorta;

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list_view = (ListView) findViewById(R.id.list_view);

        List<Viagem> lstViagem = new ArrayList<>();

        adapter = new ViagemAdapter(this, lstViagem);

        list_view.setAdapter(adapter);

        new AsyncTask<Void, Void, Void>(){

            ArrayList<Viagem> lstViagem = new ArrayList<Viagem>();

            @Override
            protected Void doInBackground(Void... voids) {

                String retornoJson = HttpConnection.get(ipServidorComPorta()+"/api/v1/sugestoes");

                Log.d("TAG", retornoJson);

                try {
                    JSONArray jsonArray = new JSONArray(retornoJson);

                    for(int i =0; i < jsonArray.length(); i++){

                        JSONObject item = jsonArray.getJSONObject(i);

                        Viagem c = new  Viagem(
                                item.getInt("id"),
                                item.getString("preco"),
                                item.getString("origem"),
                                item.getString("destino"),
                                item.getString("hora_saida"),
                                item.getString("hora_chegada"),
                                item.getString("data_saida").substring(0, 10),
                                item.getString("data_chegada").substring(0, 10),
                                item.getString("km"),
                                item.getString("endereco_saida"),
                                item.getString("endereco_chegada"),
                                item.getString("img"),
                                item.getString("classe"),
                                item.getString("poltronas"));

                        lstViagem.add(c);
                    }

                    Log.d("TAG", lstViagem.size()+"");
                }catch (Exception ex){
                    Log.e("Erro: ", ex.getMessage());
                }

                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.addAll(lstViagem);
            }
        }.execute();


        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Viagem item = adapter.getItem(i);

                Intent intent = new Intent(getApplicationContext(), DetalhesViagemActivity.class);

                intent.putExtra("id", item.getId()+"");

                startActivity(intent);

            }
        });
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

            startActivity(new Intent(getApplicationContext(), MainActivity.class));

            finish();

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
            Intent intent = new Intent(getApplicationContext(), PerfilUsuarioActivity.class);

            startActivity(intent);

        } else if (id == R.id.nav_compraPassagem) {
            startActivity(new Intent(getApplicationContext(), CompraPassagem.class));
        } else if (id == R.id.nav_notificacao) {

        } else if (id == R.id.nav_acompanhamento) {

        } else if (id == R.id.nav_noticias) {
            startActivity(new Intent(getApplicationContext(), NoticiasUsuarioActivity.class));
        } else if (id == R.id.nav_mapa) {

        } else if (id == R.id.nav_historico) {

        } else if (id == R.id.nav_contato) {
            startActivity(new Intent(getApplicationContext(), FaleConoscoActivity.class));
        } else if (id == R.id.nav_qrcode) {
            startActivity(new Intent(getApplicationContext(), QRCodeUsuarioActivity.class));
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
            TextView txtValor = v.findViewById(R.id.txtValorViagem);

            ImageView imagemViagem = v.findViewById(R.id.imagemViagem);

            Picasso.with(getContext()).load(ipServidorSemPorta()+"/inf4m/asteroide/"+item.getImagem()).into(imagemViagem);

            Log.d("IP:",ipServidorSemPorta()+"/inf4m/asteroide/"+item.getImagem());

            tltViagem.setText(item.getOrigem() + " x " + item.getDestino());

            txtValor.setText("Apartir de "+ converterDinheiro(item.getValor()));

            return v;
        }
    }


}
