package cl.deliciasurbanas.www.admindeliciasurbanas.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

import cl.deliciasurbanas.www.admindeliciasurbanas.Models.Sandwich;
import cl.deliciasurbanas.www.admindeliciasurbanas.Network.MySingleton;
import cl.deliciasurbanas.www.admindeliciasurbanas.R;

public class SwitchStockAdapter extends RecyclerView.Adapter<SwitchStockAdapter.SwitchStockViewHolder> {
    List<Sandwich> listaSandwich;

    public SwitchStockAdapter (List<Sandwich> listaSandwich){
        this.listaSandwich = listaSandwich;
    }

    @NonNull
    @Override
    public SwitchStockViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.switch_stock_item,viewGroup, false);

        return new SwitchStockViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SwitchStockViewHolder switchStockViewHolder, final int i) {
        final Sandwich sandwich = listaSandwich.get(i);
        switchStockViewHolder.nombreSandwich.setText(sandwich.getNombre());
        String strpf = "estado_sandwich_"+ sandwich.getId();
        SharedPreferences estadoPrefs = switchStockViewHolder.itemView.getContext().getSharedPreferences(strpf, Context.MODE_PRIVATE);
        String estado = estadoPrefs.getString("estado", "-1");
        if(estado.equals("1")){
            switchStockViewHolder.switchSandwich.setChecked(true);
        }else if(estado.equals("0")){
            switchStockViewHolder.switchSandwich.setChecked(false);
        }else {
            Toast.makeText(switchStockViewHolder.itemView.getContext(), "Error al leer preferencias ver LOG", Toast.LENGTH_LONG).show();
            Log.e("Error Preferences", "No esta leyendo las preferencias o no esta escribiendo al iniciar");
        }

        switchStockViewHolder.switchSandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarEstadoSandwich(String.valueOf(sandwich.getId()), switchStockViewHolder.itemView.getContext());
            }
        });


    }

    @Override
    public int getItemCount() {
        if(listaSandwich == null){
            return 0;
        }else{
            return listaSandwich.size();
        }
    }
    private void cambiarEstadoSandwich (final String idSandwich, final Context context){
        String url = "https://deliciasurbanas.cl/sandwich/cambiar_estado_sandwich_api/"+idSandwich;
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("respuesta del switch", response);
                Toast.makeText(context, "Estado cambiado sandwich id:"+ idSandwich, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }


    public class SwitchStockViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreSandwich;
        private Switch switchSandwich;

        public SwitchStockViewHolder(@NonNull final View itemView) {
            super(itemView);
            nombreSandwich = (TextView)itemView.findViewById(R.id.textview_nombre_sandwich_switch_stock);
            switchSandwich = (Switch)itemView.findViewById(R.id.switch_sanwich_switch_stock);

            //final Sandwich sandwich = listaSandwich.get();

            /*switchSandwich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    cambiarEstadoSandwich(String.valueOf(sandwich.getId()),itemView.getContext());
                }
            });*/


        }


    }




}


