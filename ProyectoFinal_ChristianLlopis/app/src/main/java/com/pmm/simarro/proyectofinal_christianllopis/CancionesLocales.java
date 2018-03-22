package com.pmm.simarro.proyectofinal_christianllopis;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pmm.simarro.proyectofinal_christianllopis.adaptador.AdaptadorCanciones;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.Cancion;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class CancionesLocales extends AppCompatActivity {

    private ListView listaCanciones;
    private ArrayList<Cancion> canciones = new ArrayList<>();
    private MediaPlayer mp = null;
    private int anterior = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canciones_locales);

        listaCanciones = (ListView) findViewById(R.id.listaCanciones);

        Field[] fields = R.raw.class.getFields();

        for(int i=0; i < fields.length; i++){
            int id = getRaw(this, fields[i].getName());
            String nombre = formatearNombre(fields[i].getName());
            canciones.add(new Cancion(id, nombre));
        }

        listaCanciones.setAdapter(new AdaptadorCanciones(this, canciones));

        listaCanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                if (anterior != -1)
                    listaCanciones.getChildAt(anterior).setBackgroundColor(Color.green(R.color.verdeTransparente));

                //Compruebo si no es la misma que la que se ha pulsado anteriormente
                if (anterior != pos) {
                    //Si no es null, es decir, que se ha pulsado anteriormente, le hago un stop(),
                    //ya que si ha entrado a aqui es porque es otra cancion la que hemos pulsado
                    //y queremos detener la anterior.
                    if (mp != null)
                        mp.stop();

                    //Lo ponemos a null "for if the flies"
                    mp = null;
                    //Creamos el MediaPlayer cogiendo la id en la posicion del array canciones que hayamos pulsado
                    //el cual seria el id de R.raw.* equivalente
                    mp = MediaPlayer.create(getApplicationContext(), canciones.get(pos).getId());
                }

                //Compruebo si no se esta reproduciendo
                if (!mp.isPlaying()) {
                    //Si no se esta reproduciendo le hacemos start()
                    listaCanciones.getChildAt(pos).setBackgroundColor(Color.argb(208, 40,59,0));
                    mp.start();
                }else {
                    //Si ja se esta reproduciendo, lo pausamos
                    mp.pause();
                }

                //Nos guardamos la posicion que hemos pulsado, para despues hacer las comprobaciones
                anterior = pos;
            }
        });
    }

    private int getRaw(Context c, String name) {
        return c.getResources().getIdentifier(name, "raw", c.getPackageName());
    }

    private String formatearNombre(String n) {
        String [] palabras = n.split("_");
        String aux = "";

        for (int i = 0 ; i < palabras.length ; i++) {
            aux += palabras[i] + " ";
        }

        aux = aux.trim();

        n = Character.toUpperCase(aux.charAt(0)) + aux.substring(1, aux.length());

        return n;
    }
}
