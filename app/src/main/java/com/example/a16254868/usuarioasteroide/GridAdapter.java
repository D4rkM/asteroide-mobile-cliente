/*package com.example.a16254868.usuarioasteroide;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 16254868 on 09/05/2018.
 */

/*public class GridAdapter extends BaseAdapter{

    private int icons[];

    private int numeros[];

    private Context context;

    private LayoutInflater inflater;

    public GridAdapter(Context context, int[] icons, int[] numeros) {
        this.icons = icons;
        this.numeros = numeros;
        this.context = context;
    }

    @Override
    public int getCount() {
        return numeros.length;
    }

    @Override
    public Object getItem(int i) {
        return numeros.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View gridView = view;


        if(view == null){

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = inflater.inflate(R.layout.grid_view_bus, null);

        }

        ImageView icon = (ImageView) gridView.findViewById(R.id.imgBancoOnibus);

        TextView numero = (TextView) gridView.findViewById(R.id.numeroPoltrona);


        icon.setImageResource(icons[i]);

        numero.setText(numeros[i]+"");

        return gridView;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}*/

package com.example.a16254868.usuarioasteroide;

        import android.content.Context;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import models.Poltrona;

/**
 * Created by 16254868 on 09/05/2018.
 */

public class GridAdapter extends BaseAdapter{

    private int numeros[];
    private int cor[];
    private Context context;
    private LayoutInflater inflater;

    public GridAdapter() {

    }

    public GridAdapter(Context context, Poltrona poltrona) {

        this.cor = poltrona.getCor();
        this.numeros = poltrona.getNumeroPoltrona();
        this.context = context;
    }

    @Override
    public int getCount() {
        return numeros.length;
    }

    @Override
    public Object getItem(int i) {
        return numeros.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View gridView = view;

        if(view == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = inflater.inflate(R.layout.grid_view_bus, null);
        }

        TextView numero = (TextView) gridView.findViewById(R.id.numeroPoltrona);


        if(cor[i] == 0) {
            numero.setBackgroundResource(R.drawable.square_green);
        }else if(cor[i] == 1){
            numero.setBackgroundResource(R.drawable.square_yellow);
        }else if(cor[i] == 3){
            numero.setBackgroundResource(R.drawable.square_red);
        }
        numero.setText(numeros[i]+"");

        return gridView;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
