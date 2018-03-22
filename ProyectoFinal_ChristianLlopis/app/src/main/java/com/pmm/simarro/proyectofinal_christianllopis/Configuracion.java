package com.pmm.simarro.proyectofinal_christianllopis;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class Configuracion extends AppCompatActivity {

    static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        prefs = getSharedPreferences("preferenciasmusicteca", Context.MODE_PRIVATE);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(android.R.id.content, new PreferenciasFragment());
        ft.commit();
    }

    public static class PreferenciasFragment extends PreferenceFragment {

        static ListPreference idioma;
        static ListPreference logo;

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.opciones);

            idioma = (ListPreference) findPreference("idioma");
            logo = (ListPreference) findPreference("logo");

            if(idioma.getValue() == null){
                idioma.setValueIndex(0);
            }
            if(logo.getValue() == null){
                logo.setValueIndex(0);
            }

        }

        @Override
        public void onPause() {
            super.onPause();
            EditTextPreference saludo = (EditTextPreference) findPreference("saludo");
            ListPreference idioma = (ListPreference) findPreference("idioma");
            ListPreference logo = (ListPreference) findPreference("logo");
            CheckBoxPreference valoresDefecto = (CheckBoxPreference) findPreference("valoresDefecto");

            SharedPreferences.Editor editor = prefs.edit();

            if (valoresDefecto.isChecked()) {
                editor.putString("saludo", "");
                editor.putString("idioma", "ES");
                editor.putString("logo", "spoty");

            } else {
                editor.putString("saludo", saludo.getText());
                editor.putString("idioma", idioma.getValue());
                editor.putString("logo", logo.getValue());
            }


            editor.commit();

            //Quan tire arrere, com les activitys ja estan creades no es veu que canvie el idioma, pero si tornes a crear una activity si que canvia
            String idi = prefs.getString("idioma", "");

            Locale localizacion = new Locale(idi.toLowerCase(), idi);

            Locale.setDefault(localizacion);
            Configuration config = new Configuration();
            config.locale = localizacion;
            getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());
        }

    }
}
