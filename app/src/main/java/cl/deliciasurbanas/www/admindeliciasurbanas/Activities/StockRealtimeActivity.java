package cl.deliciasurbanas.www.admindeliciasurbanas.Activities;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

import cl.deliciasurbanas.www.admindeliciasurbanas.Adapters.SandwichAdapter;
import cl.deliciasurbanas.www.admindeliciasurbanas.Adapters.SwitchStockAdapter;
import cl.deliciasurbanas.www.admindeliciasurbanas.Models.EstadoSandwich;
import cl.deliciasurbanas.www.admindeliciasurbanas.Models.Sandwich;
import cl.deliciasurbanas.www.admindeliciasurbanas.Network.MySingleton;
import cl.deliciasurbanas.www.admindeliciasurbanas.R;

public class StockRealtimeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerSandwiches;
    private RecyclerView.Adapter adapterSandwiches;
    private RecyclerView.LayoutManager managerSandwiches;
    private List<Sandwich> listaSandwiches;
    private List<EstadoSandwich> listaEstadoSandwich;
    private ProgressBar progressStock;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_realtime);

        recyclerSandwiches = (RecyclerView)findViewById(R.id.recycler_view_switches);
        recyclerSandwiches.setHasFixedSize(true);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container_stock_realtime);
        refreshLayout.setOnRefreshListener(this);

        progressStock = (ProgressBar)findViewById(R.id.progress_sandwich_stock_realtime);

        leerSandwiches();
    }

    private void leerSandwiches(){
        refreshLayout.setRefreshing(true);
        progressStock.setVisibility(View.VISIBLE);

        listaSandwiches = new ArrayList<>();
        SharedPreferences cantSandPrefs = getSharedPreferences("cantidad_sandwiches", MODE_PRIVATE);
        int cantidad = cantSandPrefs.getInt("cantidad", 0);
        for(int i = 0; i < cantidad; i++){
            String strpf = "sandwich"+String.valueOf(i + 1);
            SharedPreferences prefs = getSharedPreferences(strpf, MODE_PRIVATE);

            String id = prefs.getString("id_sandwich", "0");
            String nombre = prefs.getString("nombre_sandwich", "nombre por defecto");
            String descripcion = prefs.getString("descripcion_sandwich", "descripcion por defecto");
            String urlImg = prefs.getString("url_img_sandwich", "url por defecto");
            String urlMin =  prefs.getString("url_min_sandwich", "url min por defecto");
            String tipo =  prefs.getString("tipo_sandwich", "tipo por defecto");
            String kcal = prefs.getString("kcal_sandwich", "kcal por defecto");
            String precio = prefs.getString("precio_sandwich", "0000");

            leerEstadoSandwich(id);

           listaSandwiches.add(new Sandwich(id, nombre, descripcion, urlImg, urlMin, tipo, kcal, precio));
        }

        Handler handler = new Handler ();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                progressStock.setVisibility(View.GONE);
                managerSandwiches = new LinearLayoutManager(StockRealtimeActivity.this);
                recyclerSandwiches.setLayoutManager(managerSandwiches);
                adapterSandwiches = new SwitchStockAdapter(listaSandwiches);
                recyclerSandwiches.setAdapter(adapterSandwiches);

            }
        },3000);

        /*managerSandwiches = new LinearLayoutManager(StockRealtimeActivity.this);
        recyclerSandwiches.setLayoutManager(managerSandwiches);
        adapterSandwiches = new SwitchStockAdapter(listaSandwiches);
        recyclerSandwiches.setAdapter(adapterSandwiches);*/
    }

    private void leerEstadoSandwich(final String idSandwich){
        //Obtener los datos de internet
        String url = "https://deliciasurbanas.cl/sandwich/leer_estado_sandwich_api/"+idSandwich;
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String strPref = "estado_sandwich_"+idSandwich;
                SharedPreferences estadoSandwichPrefs = getSharedPreferences(strPref, MODE_PRIVATE);
                SharedPreferences.Editor estadoEditor = estadoSandwichPrefs.edit();
                estadoEditor.putString("estado", response);
                estadoEditor.apply();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(request);
        //pasar el estado a variables en shared preferences

        //leer el estado de las variables del shared preferences y mostrar el  estado de los switches en su adapter
    }

    @Override
    public void onRefresh() {
        leerSandwiches();
    }
}
