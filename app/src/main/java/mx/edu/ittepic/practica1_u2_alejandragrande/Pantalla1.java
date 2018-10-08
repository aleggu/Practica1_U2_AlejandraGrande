package mx.edu.ittepic.practica1_u2_alejandragrande;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Pantalla1 extends AppCompatActivity {

    ListView lista;
    LinearLayout layo;
    TextView agregar;//guardar las cadenas
    String [] listamenu;
    String  [] vector ;
    String  [] vectortitulos ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1);

        layo=findViewById(R.id.linear);
        lista=findViewById(R.id.lista);
        agregar=findViewById(R.id.enunciado);
        listamenu=new String[20];
        vectortitulos=new String[20];
        vector=new String[20];
        for(int i=0; i<20; i++)//crear los 20  espacios en el arreglo
        {
          listamenu[i]="";
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent llamadaventana2= new Intent(Pantalla1.this, Pantalla2.class);
                startActivityForResult(llamadaventana2, 1);
            }
        });
    }

    protected  void onActivityResult( int requestCode, int resultCode, Intent data)
    {
        int pos=data.getIntExtra("posicion", 10);
        String cadena=data.getStringExtra("cadena");
        String enunciado=data.getStringExtra("titulo");
        if (resultCode==12)
        {
            borrar(pos, cadena, enunciado);
        }
        else
        {
            mostrarLista(pos,cadena, enunciado);
        }

    }

    private void borrar(int pos, String cadena, String enunciado)
    {
        int vacios=0;
        if (cadena=="" && enunciado=="")
        {
            Toast.makeText(this, "se borro algo", Toast.LENGTH_LONG).show();
            for(int i=0; i<=vector.length; i++)
            {
                if(vectortitulos[i] =="" && vector[i]=="" )
                {
                    vacios++;
                }
            }
        }

        if(vacios==20)
        {
            Intent misma = new Intent(Pantalla1.this, Pantalla1.class);
            startActivity(misma);
        }
        else
        {
            mostrarLista(pos,cadena, enunciado);
        }
    }

    private void mostrarLista(final int pos , final String cadena, final String enunciado)
    {

        layo.removeView(agregar); //borras el texto de agregar
            listamenu[pos] = enunciado;
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(Pantalla1.this, android.R.layout.simple_list_item_1,
                        listamenu);
                lista.setAdapter(adaptador);
                vector[pos] = cadena;
                vectortitulos[pos] = enunciado;


                Toast.makeText(this, "" + vectortitulos[pos] + "", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "" + vector[pos] + "", Toast.LENGTH_LONG).show();

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                        String c = vector[pos];
                        String e = vectortitulos[pos];
                        Intent llamadaventana3 = new Intent(Pantalla1.this, PantallaN.class);
                        llamadaventana3.putExtra("cadena1", c);
                        llamadaventana3.putExtra("enunciado1", e);
                        llamadaventana3.putExtra("posicion1", pos);

                        startActivityForResult(llamadaventana3, 4);

                    }
                });
        }
        }


