package cl.deliciasurbanas.www.admindeliciasurbanas.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.deliciasurbanas.www.admindeliciasurbanas.Network.CustomRequest;
import cl.deliciasurbanas.www.admindeliciasurbanas.Network.MySingleton;
import cl.deliciasurbanas.www.admindeliciasurbanas.R;
import cl.deliciasurbanas.www.admindeliciasurbanas.Models.Sandwich;
import cl.deliciasurbanas.www.admindeliciasurbanas.Adapters.SandwichAdapter;

public class Paso1InicioVentaActivity extends AppCompatActivity {

    private RecyclerView recyclerSandwiches;
    private RecyclerView.Adapter adapterSandwiches;
    private RecyclerView.LayoutManager managerSandwiches;
    private List<Sandwich> listaSandwich;
    private SparseBooleanArray listaCheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paso1_inicio_venta);

        //ACTIONBAR
        Toolbar actionBar = (Toolbar)findViewById(R.id.action_bar_inicio_paso1);
        //setSupportActionBar(actionBar);


        recyclerSandwiches = (RecyclerView)findViewById(R.id.recycler_p1_inicio_venta);
        recyclerSandwiches.setHasFixedSize(true);

        cargarSandwiches();

    }

    private void cargarSandwiches(){
        listaSandwich = new ArrayList<Sandwich>();
        SharedPreferences sandwichPf = getSharedPreferences("cantidad_sandwich", MODE_PRIVATE);
        int cantidadSandwich = sandwichPf.getInt("cantidad", 0);
        for(int i = 0; i < cantidadSandwich; i++){
            String strpf = "sandwich"+String.valueOf(i);
            SharedPreferences sandwichPrefs = getSharedPreferences(strpf, MODE_PRIVATE);

            String idSandwich = sandwichPrefs.getString("id_sandwich", "0");
            String nombreSandwich = sandwichPrefs.getString("nombre_sandwich", "nombre por defecto");
            String descripcionSandwich = sandwichPrefs.getString("descripcion_sandwich", "descripcion por defecto");
            String urlImgSandwich = sandwichPrefs.getString("url_img_sandwich", "url por defecto");
            String urlMinSandwich =  sandwichPrefs.getString("url_min_sandwich", "url min por defecto");
            String tipoSandwich =  sandwichPrefs.getString("tipo_sandwich", "tipo por defecto");
            String kcalSandwich = sandwichPrefs.getString("kcal_sandwich", "kcal por defecto");
            String precioSandwich = sandwichPrefs.getString("precio_sandwich", "0000");
            listaSandwich.add(new Sandwich(idSandwich, nombreSandwich, descripcionSandwich, urlImgSandwich,
                    urlMinSandwich, tipoSandwich, kcalSandwich, precioSandwich));
        }

        managerSandwiches = new LinearLayoutManager(Paso1InicioVentaActivity.this);
        recyclerSandwiches.setLayoutManager(managerSandwiches);
        adapterSandwiches = new SandwichAdapter(listaSandwich);
        recyclerSandwiches.setAdapter(adapterSandwiches);

        SandwichAdapter a = new SandwichAdapter(listaSandwich);
        listaCheckbox = a.getListaEstadoArray();

    }

    private void cargarSandwichPreferences(){
        String url = "https://deliciasurbanas.cl/sandwich/leer_sandwiches_api";

        CustomRequest request = new CustomRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray respuesta = response.getJSONArray("sandwiches");
                    listaSandwich = new ArrayList<Sandwich>();

                    Log.e("string de la respuesta", respuesta.toString());

                    for(int i = 0; i < respuesta.length(); i++){


                        JSONObject sandwich = respuesta.getJSONObject(i);

                        String idSandwich = sandwich.getString("id_sandwich");
                        String nombreSandwich = sandwich.getString("nombre_sandwich");
                        String descripcionSandwich = sandwich.getString("descripcion_sandwich");
                        String urlImgSandwich = sandwich.getString("url_img_sandwich");
                        String urlMinSandwich =  sandwich.getString("url_min_sandwich");
                        String tipoSandwich =  sandwich.getString("tipo_sandwich");
                        String kcalSandwich = sandwich.getString("kcal_sandwich");
                        String precioSandwich = sandwich.getString("precio_sandwich");


                        listaSandwich.add(new Sandwich(idSandwich,nombreSandwich,descripcionSandwich,
                                urlImgSandwich,urlMinSandwich,tipoSandwich,kcalSandwich,precioSandwich));
                    }
                }catch(JSONException error){
                    error.printStackTrace();
                }

                managerSandwiches = new LinearLayoutManager(Paso1InicioVentaActivity.this);
                recyclerSandwiches.setLayoutManager(managerSandwiches);
                adapterSandwiches = new SandwichAdapter(listaSandwich);
                recyclerSandwiches.setAdapter(adapterSandwiches);

                SandwichAdapter a = new SandwichAdapter(listaSandwich);
                listaCheckbox = a.getListaEstadoArray();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);
    }
}
