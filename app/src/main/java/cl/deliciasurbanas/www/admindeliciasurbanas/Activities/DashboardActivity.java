package cl.deliciasurbanas.www.admindeliciasurbanas.Activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;

import cl.deliciasurbanas.www.admindeliciasurbanas.Network.NetworkLoaderPreferences;
import cl.deliciasurbanas.www.admindeliciasurbanas.R;

public class DashboardActivity extends AppCompatActivity {

    private Button btnVentas;
    private Button btnCompras;
    private Button btnMensajes;
    private Button btnStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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
                Log.d("DashboardActivity", "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]

        btnVentas = (Button)findViewById(R.id.btn_ventas);
        btnCompras = (Button)findViewById(R.id.btn_compras);
        btnMensajes = (Button)findViewById(R.id.btn_mensajes);
        btnStock = (Button)findViewById(R.id.btn_stock_realtime);

        btnVentas.setOnClickListener(ventasListener);
        btnCompras.setOnClickListener(comprasListener);
        btnMensajes.setOnClickListener(mensajesListener);
        btnStock.setOnClickListener(stockRealtime);
        NetworkLoaderPreferences loader = new NetworkLoaderPreferences(DashboardActivity.this);
        loader.cargarSandwiches();
    }

    private View.OnClickListener ventasListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(DashboardActivity.this, VentasActivity.class);
            startActivity(i);
        }
    };

    private View.OnClickListener comprasListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private View.OnClickListener mensajesListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(i);
        }
    };

    private View.OnClickListener stockRealtime = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(DashboardActivity.this, StockRealtimeActivity.class);
            startActivity(i);
        }
    };
}
