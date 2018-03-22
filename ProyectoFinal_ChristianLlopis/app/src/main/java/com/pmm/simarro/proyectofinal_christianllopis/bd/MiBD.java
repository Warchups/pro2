package com.pmm.simarro.proyectofinal_christianllopis.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pmm.simarro.proyectofinal_christianllopis.dao.CancionYoutubeDAO;
import com.pmm.simarro.proyectofinal_christianllopis.pojo.CancionYoutube;

public class MiBD extends SQLiteOpenHelper{

    private static SQLiteDatabase db;
    //nombre de la base de datos
    private static final String database = "MisCanciones";
    //versión de la base de datos
    private static final int version = 11;
    //Instrucción SQL para crear la tabla de CancionesYoutube
    private String sqlCreacionCY = "CREATE TABLE cancionesyoutube ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre STRING, artista STRING, " +
            "genero STRING, url STRING);";

    private static MiBD instance = null;

    private static CancionYoutubeDAO cancionYoutubeDAO;

    public CancionYoutubeDAO getCancionYoutubeDAO() {
        return cancionYoutubeDAO;
    }

    public static MiBD getInstance(Context context) {
        if(instance == null) {
            instance = new MiBD(context);
            db = instance.getWritableDatabase();
            cancionYoutubeDAO = new CancionYoutubeDAO();
        }
        return instance;
    }

    public static SQLiteDatabase getDB(){
        return db;
    }
    public static void closeDB(){db.close();};

    protected MiBD(Context context) {
        super( context, database, null, version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionCY);

        insercionDatos(db);
        Log.i("SQLite", "Se crea la base de datos " + database + " version " + version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i("SQLite", "Control de versiones: Old Version=" + oldVersion + " New Version= " + newVersion  );
        if ( newVersion > oldVersion )
        {
            //elimina tabla
            db.execSQL( "DROP TABLE IF EXISTS cancionesyoutube" );
            //y luego creamos la nueva tabla
            db.execSQL(sqlCreacionCY);

            insercionDatos(db);
            Log.i("SQLite", "Se actualiza versión de la base de datos, New version= " + newVersion  );
        }
    }

    public void insercionCancion(CancionYoutube c){
        db.execSQL("INSERT INTO cancionesyoutube (id, nombre, artista, genero, url) VALUES (null, '" +c.getNombre()+"', '"+ c.getArtista()+"', '"+ c.getGenero()+"', '"+ c.getUrl()+"');");
    }

    private void insercionDatos(SQLiteDatabase db){
        // Insertamos los clientes
        db.execSQL("INSERT INTO cancionesyoutube(id, nombre, artista, genero, url) VALUES (1, 'Cicatrices', 'Natos y Waor', 'Rap', 'https://www.youtube.com/watch?v=GL_BaF283TM');");
        db.execSQL("INSERT INTO cancionesyoutube(id, nombre, artista, genero, url) VALUES (2, 'Dorian Gray', 'Sharif', 'Rap', 'https://www.youtube.com/watch?v=O4GZGP11xa4');");
        db.execSQL("INSERT INTO cancionesyoutube(id, nombre, artista, genero, url) VALUES (3, 'La rueca', 'Marea', 'Rock', 'https://www.youtube.com/watch?v=HvjD7RxpxuU');");
        db.execSQL("INSERT INTO cancionesyoutube(id, nombre, artista, genero, url) VALUES (4, 'Malos pensamientos', 'La Fuga', 'Rock', 'https://www.youtube.com/watch?v=McSq7JlEIuc');");
        db.execSQL("INSERT INTO cancionesyoutube(id, nombre, artista, genero, url) VALUES (5, 'Mi cenicienta', 'Rulo y la Contrabanda', 'Rock', 'https://www.youtube.com/watch?v=g5pwP6EFcZ4');");
        db.execSQL("INSERT INTO cancionesyoutube(id, nombre, artista, genero, url) VALUES (6, 'Tu jardin con enanitos', 'Melendi', 'Pop', 'https://www.youtube.com/watch?v=v3-9eDFDAFw');");
        db.execSQL("INSERT INTO cancionesyoutube(id, nombre, artista, genero, url) VALUES (7, 'Tanto por ti', 'Swan Fyahbwoy', 'Reggae', 'https://www.youtube.com/watch?v=Q9Q1Zyeyy_Y');");


    }
}
