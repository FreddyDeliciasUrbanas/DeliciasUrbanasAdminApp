package cl.deliciasurbanas.www.admindeliciasurbanas.Activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.FirebaseApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.deliciasurbanas.www.admindeliciasurbanas.Models.Mensaje;
import cl.deliciasurbanas.www.admindeliciasurbanas.Adapters.MensajeAdapter;
import cl.deliciasurbanas.www.admindeliciasurbanas.Network.MySingleton;
import cl.deliciasurbanas.www.admindeliciasurbanas.Network.NetworkLoaderPreferences;
import cl.deliciasurbanas.www.admindeliciasurbanas.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerMensajes;
    private RecyclerView.Adapter adapterMensajes;
    private RecyclerView.LayoutManager layoutManagerMensajes;
    private List<Mensaje> listaMensajes;
    private ProgressBar progressMensajes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]

        //ASOCIAR EL PROGRESSBAR
        progressMensajes = (ProgressBar)findViewById(R.id.progress_mensajes_mainactivity);

        //CONFIGURAR EL RECYCLERVIEW
        recyclerMensajes = (RecyclerView)findViewById(R.id.recycler_view_mensajes);
        recyclerMensajes.setHasFixedSize(true);
        NetworkLoaderPreferences loader = new NetworkLoaderPreferences(MainActivity.this, progressMensajes);
        loader.cargarMensajes();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                leerMensajes();
            }
        },2000);


    }

    //Hacer peticion de todos los
    private void leerMensajes(){
        listaMensajes = new ArrayList<Mensaje>();
        SharedPreferences prefs = getSharedPreferences("cantidad_mensajes", MODE_PRIVATE);
        int cantidadMensajes = prefs.getInt("cantidad", 0);

        Log.e("cantidad de mensajes", String.valueOf(cantidadMensajes));
        for(int i = 0; i < cantidadMensajes; i++){
            String strpf = "mensaje"+String.valueOf(cantidadMensajes - i);
            SharedPreferences mensajePref = getSharedPreferences(strpf, MODE_PRIVATE);
            int idMensajeActual = mensajePref.getInt("id_mensaje", 0);
            String nombreMensajeActual = mensajePref.getString("nombre_mensaje", "nombre por defecto");
            String emailMensajeActual = mensajePref.getString("email_mensaje", "email por defecto");
            String telefonoMensajeActual = mensajePref.getString("telefono_mensaje", "telefono por defecto");
            String direccionMensajeActual = mensajePref.getString("direccion_mensaje", "direccion por defecto");
            String textoMensajeActual = mensajePref.getString("texto_mensaje", "texto por defecto");
            listaMensajes.add(new Mensaje(idMensajeActual, nombreMensajeActual, emailMensajeActual,
                    telefonoMensajeActual, direccionMensajeActual, textoMensajeActual));
        }
        layoutManagerMensajes = new LinearLayoutManager(MainActivity.this);
        recyclerMensajes.setLayoutManager(layoutManagerMensajes);
        adapterMensajes = new MensajeAdapter(listaMensajes);
        recyclerMensajes.setAdapter(adapterMensajes);
    }



    //Hacer peticion solo 1 mensaje con el id

}