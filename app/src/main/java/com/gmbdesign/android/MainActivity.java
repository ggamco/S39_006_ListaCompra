package com.gmbdesign.android;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    //Recursos utilizados para la aplicación

    String[] arrayProductos =
            {
                    "Platanos","Tomates",
                    "Leche entera","Almendras crudas",
                    "Repollo","Acelgas",
                    "Filetes de lomo","Ensalada",
                    "Doradas","Bacalao",
                    "Pimientos verdes","Cebollas",
                    "Cerveza","Vino Blanco",
                    "Mango","Calabacin",
                    "Filetes de pollo","Jamon cocido",
                    "Papel Higienico","Champú",
            };

    boolean[] arrayEstado = new boolean[arrayProductos.length];



    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        //Paso previo: rellenamos el arrayEstado con false
        //Arrays.fill(arrayEstado, false);

        //Modificación del paso previo: preguntamos si en el bundle existe ya una version
        //guardada del arrayEstados
        if(bundle != null){
            arrayEstado = (boolean[]) bundle.get("estados");
        } else {
            Arrays.fill(arrayEstado, false);
        }

        //cargamos la vista que será llenada de objetos
        ListView lv = (ListView) findViewById(R.id.listaProductos);

        //creamos el adaptador y le pasamos los recursos
        ProductoAdapter adaptador = new ProductoAdapter(this, arrayProductos, arrayEstado);

        //seteamos el adaptador a la vista
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView switcher = (TextView) view.findViewById(R.id.switchSel);

                if(arrayEstado[position]) {
                    //el producto ya estaba selecionado
                    arrayEstado[position] = false;
                    switcher.setText("No Seleccionado");
                    switcher.setTextColor(Color.DKGRAY);
                } else {
                    //el producto no estaba seleccionado
                    arrayEstado[position] = true;
                    switcher.setText("Seleccionado");
                    switcher.setTextColor(Color.BLUE);
                }
            }
        });

    }

    //para mantener la informacion del arrayEstados cada vez que se recrea la actividad
    //sobreescribimos el metodo para almacenar en el Bundle.
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);

        //cargamos el array en el bundle
        bundle.putBooleanArray("estados", arrayEstado);
    }
}
