package cl.deliciasurbanas.www.admindeliciasurbanas.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import cl.deliciasurbanas.www.admindeliciasurbanas.R;
import cl.deliciasurbanas.www.admindeliciasurbanas.Models.Sandwich;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichViewHolder> {
    private List<Sandwich> listaSandwich;
    private SparseBooleanArray listaEstadoArray = new SparseBooleanArray();

    public SandwichAdapter(List<Sandwich> listaSandwich){
        this.listaSandwich = listaSandwich;
    }

    @NonNull
    @Override
    public SandwichViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sandwich_paso1_item,viewGroup,false);
        return new SandwichViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SandwichViewHolder sandwichViewHolder, int i) {
        Sandwich sandwich = listaSandwich.get(i);
        sandwichViewHolder.tvSandwichItemPaso1.setText(sandwich.getNombre());
        sandwichViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        if(listaSandwich == null){
            return 0;
        }else{
            return listaSandwich.size();
        }
    }

    public SparseBooleanArray getListaEstadoArray() {
        return listaEstadoArray;
    }

    public class SandwichViewHolder extends RecyclerView.ViewHolder{
        private TextView tvSandwichItemPaso1;
        private CheckBox chbSandwichItemPaso1;

        public SandwichViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSandwichItemPaso1 = (TextView)itemView.findViewById(R.id.tv_sandwich_item_paso1);
            chbSandwichItemPaso1 = (CheckBox)itemView.findViewById(R.id.chb_sandwich_item_paso1);
            chbSandwichItemPaso1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posicionAdapter = getAdapterPosition();
                    if(!listaEstadoArray.get(posicionAdapter, false)){
                        chbSandwichItemPaso1.setChecked(true);
                        listaEstadoArray.put(posicionAdapter, true);
                    }else{
                        chbSandwichItemPaso1.setChecked(false);
                        listaEstadoArray.put(posicionAdapter, false);
                    }
                }
            });
        }
         void bind (int posicion){
            if(!listaEstadoArray.get(posicion, false)){
                chbSandwichItemPaso1.setChecked(false);
            }else{
                chbSandwichItemPaso1.setChecked(true);
            }
         }

    }




}
