package mx.edu.ittepic.practica1_u2_alejandragrande;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pantalla2 extends AppCompatActivity {
    Button guardar;
    EditText titulo, fecha, materia, descripcion;
    String [] vectortitulos;
    String [] vector;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        titulo=findViewById(R.id.campotitulo);
        fecha=findViewById(R.id.campofecha);
        materia=findViewById(R.id.campomateria);
        descripcion=findViewById(R.id.campodescripcion);
        guardar=findViewById(R.id.botonguardar);

        vectortitulos=new String[20];
        vector=new String[20];

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturar();
            }
        });
    }

    private void capturar()
    {
        AlertDialog.Builder alerta=new AlertDialog.Builder(this);
        final EditText posicion=new EditText(this);
        posicion.setHint("Rango de 1 a 20");
        posicion.setInputType(InputType.TYPE_CLASS_NUMBER);
        alerta.setTitle("GUARDANDO.....")
                .setMessage("Escribe la posicion donde se va a guardar")
                .setView(posicion)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        guardarEnVector(posicion.getText().toString());
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }

    private void guardarEnVector(String posicion)
    {
        int pos=Integer.parseInt(posicion);
        pos--;
        if(pos>=0 && pos<=19)
        {
            vectortitulos[pos]=titulo.getText().toString();
            vector[pos]=titulo.getText().toString()+"&"+fecha.getText().toString()+"&"+materia.getText().toString()+"&"+descripcion.getText().toString();
        }
        else
        {
            Toast.makeText(this, "Error el valor"+posicion+"no es correcto", Toast.LENGTH_LONG).show();
        }

        String cadena= vector[pos];
        String enunciado= vectortitulos[pos];

        Intent llamadaventana1 =new Intent();


        llamadaventana1.putExtra("posicion",pos);
        llamadaventana1.putExtra("cadena",cadena);
        llamadaventana1.putExtra("titulo",enunciado);

        setResult(9 ,llamadaventana1);
        finish();


    }

}
