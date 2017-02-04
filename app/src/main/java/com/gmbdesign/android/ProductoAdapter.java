package com.gmbdesign.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ggamboa on 4/2/17.
 */

public class ProductoAdapter extends BaseAdapter {

    //Variables locales
    Context contexto;
    String[] productos;
    boolean[] estados;

    //Constructor de la clase
    public ProductoAdapter(Context contexto, String[] productos, boolean[] estados){
        this.contexto = contexto;
        this.productos = productos;
        this.estados = estados;
    }

    //Implementamos los metodos de la interfaz que implementa la clase BaseAdapter

    @Override
    public int getCount() {
        //devuelve el número de elemento que tiene que pintar, pasamos el length del array
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //metodo que crea la nueva vista o la recicla si ya ha sido creada

        View vista = null;

        if(convertView == null) {
            //la vista no ha sido inflada
            Log.d(getClass().getCanonicalName(), "La vista se ha creado pos: " + position);

            Activity actividad = (Activity) contexto;

            //preparamos el LayoutInflater para inflar la vista
            LayoutInflater li = actividad.getLayoutInflater();
            vista = li.inflate(R.layout.fila, parent, false);

        } else {
            //la vista es reciclada
            Log.d(getClass().getCanonicalName(), "La vista se recicla pos: " + position);
            vista = convertView;

        }

        //Una vez que ya sabemos si la vista se crea o se recicla podemos setear los valores
        //dentro de cada una de las subvistas del layout

        //para este ejercicio tenemos 3 vistas disponibles, un ImageView y 2 TextView
        //1º creamos el Imageview y seteamos la imagen

        //2º creamos el primer TextView, nombre del producto
        TextView nombre = (TextView) vista.findViewById(R.id.productoLB);
        nombre.setText(productos[position]);

        //3º creamos el segundo TextView, para saber si esta selecionado o no
        TextView switcher = (TextView) vista.findViewById(R.id.switchSel);
        if(estados[position]){
            switcher.setText("Seleccionado");
            switcher.setTextColor(Color.BLUE);
        } else {
            switcher.setText("No Seleccionado");
            switcher.setTextColor(Color.DKGRAY);
        }


        return vista;
    }
}
