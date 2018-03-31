package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Vista;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Constants;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Partes_class;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.R;

public class partes extends AppCompatActivity {

    private Partes_class partes_class;
    private EditText asunto, descripcion;
    private Spinner estado, tipo;
    private ArrayAdapter<String> estadoAdapter, tipoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partes);

        if (Constants.DATA == getIntent().getIntExtra(Constants.DATA_RECOVER,Constants.NO_DATA))
            partes_class = getIntent().getParcelableExtra(Constants.CLASS_PARTES);
        else
            partes_class = null;

        asunto = findViewById(R.id.asunto);
        descripcion = findViewById(R.id.descripcion);
        estado = findViewById(R.id.estado);
        tipo = findViewById(R.id.tipo);

        estadoAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.estados));
        tipoAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.tipos));

        estado.setAdapter(estadoAdapter);
        tipo.setAdapter(tipoAdapter);

        estadoAdapter.notifyDataSetChanged();
        tipoAdapter.notifyDataSetChanged();

        if(partes_class != null){
            asunto.setText(partes_class.getNombre());
            descripcion.setText(partes_class.getDescripcion());
            estado.setSelection(partes_class.getEstado());
            tipo.setSelection(partes_class.getTipo());
        }

        FloatingActionButton guardar = findViewById(R.id.ok);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton eliminar  = findViewById(R.id.delete);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });;
    }
}
