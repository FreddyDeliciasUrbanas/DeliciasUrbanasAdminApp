package cl.deliciasurbanas.www.admindeliciasurbanas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cl.deliciasurbanas.www.admindeliciasurbanas.R;

public class VentasActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        btn = (Button)findViewById(R.id.btn_ir_al_paso_1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VentasActivity.this, Paso1InicioVentaActivity.class);
                startActivity(i);
            }
        });
    }
}
