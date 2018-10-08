package mx.edu.ittepic.practica1_u2_alejandragrande;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PantallaN extends AppCompatActivity {
    EditText titulo, fecha, materia, descripcion;
    Button atras, editar, borrar, guardar;
    String [] vector;
    String[] vectortitulo;
    LinearLayout layito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_n);
        titulo=findViewById(R.id.ctitulo);
        fecha=findViewById(R.id.cfecha);
        materia=findViewById(R.id.cmateria);
        descripcion=findViewById(R.id.cdescripcion);

        atras=findViewById(R.id.batras);
        editar=findViewById(R.id.beditar);
        borrar=findViewById(R.id.bborrar);
        guardar=findViewById(R.id.bguardar);
        layito=findViewById(R.id.lprincipal);

        vector= new String[20];
        vectortitulo= new String[20];

        final int pos=getIntent().getExtras().getInt("posicion1");
        final String cadena=getIntent().getExtras().getString("cadena1");
        final String enunciado=getIntent().getExtras().getString("enunciado1");

        layito.removeView(guardar);
        String separacion[]=cadena.split("&");
        titulo.setText(separacion[0]);
        fecha.setText(separacion[1]);
        materia.setText(separacion[2]);
        descripcion.setText(separacion[3]);

        vector[pos]=cadena;
        vectortitulo[pos]=enunciado;

        titulo.setEnabled(false);
        fecha.setEnabled(false);
        materia.setEnabled(false);
        descripcion.setEnabled(false);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodoeditar(pos);
            }
        });
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodoatras(pos);
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodoborrar(pos);
            }
        });

    }

    private void metodoborrar(int pos)
    {
        vector[pos]="";
        vectortitulo[pos]="";

        String cad=vector[pos];
        String enu=vectortitulo[pos];
        Intent v1 =new Intent();

        v1.putExtra("posicion",pos);
        v1.putExtra("cadena",cad);
        v1.putExtra("titulo",enu);

        setResult(12 ,v1);
        finish();

    }

    private void metodoatras(int pos)
    {
        String cad=vector[pos];
        String enu=vectortitulo[pos];
        Intent v1 =new Intent();

        v1.putExtra("posicion",pos);
        v1.putExtra("cadena",cad);
        v1.putExtra("titulo",enu);

        setResult(6 ,v1);
        finish();
    }

    private void metodoeditar(final int pos)
    {
        titulo.setEnabled(true);
        fecha.setEnabled(true);
        materia.setEnabled(true);
        descripcion.setEnabled(true);
        layito.addView(guardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarnuevo(pos);
            }
        });


    }

    private void guardarnuevo(int pos)
    {
        vectortitulo[pos]=titulo.getText().toString();
        vector[pos]=titulo.getText().toString()+"&"+fecha.getText().toString()+"&"+materia.getText().toString()+"&"+descripcion.getText().toString();

        String cad=vector[pos];
        String enu=vectortitulo[pos];

        Intent ventana1 =new Intent();

        ventana1.putExtra("posicion",pos);
        ventana1.putExtra("cadena",cad);
        ventana1.putExtra("titulo",enu);

        setResult(5 ,ventana1);
        finish();
    }
}
