package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Vista;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Constants;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.DatabaseConnector;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.GeneralMethods;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Parada_class;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Partes_class;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.R;

public class partes extends AppCompatActivity {

    private Partes_class partes_class;
    private EditText asunto, descripcion;
    private Spinner estado, tipo;
    private ArrayAdapter<String> estadoAdapter, tipoAdapter;
    private DatabaseConnector databaseConnector;
    private Parada_class parada;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partes);
        parada = getIntent().getParcelableExtra(Constants.CLASS_PARADA);
        databaseConnector = new DatabaseConnector(this);
        if (Constants.DATA == getIntent().getIntExtra(Constants.DATA_RECOVER, Constants.NO_DATA)) {
            partes_class = getIntent().getParcelableExtra(Constants.CLASS_PARTES);
            if (partes_class.getDate() == null)
                partes_class.setDate(GeneralMethods.stringToDate(getIntent().getStringExtra(Constants.DATE)));
            date = partes_class.getDate();
            Log.d(Constants.CLASS_PARADA, "Seleccionado parte del dia: " + GeneralMethods.dateToString(partes_class.getDate()));
        } else {
            partes_class = null;
        }

        asunto = findViewById(R.id.asunto);
        descripcion = findViewById(R.id.descripcion);
        estado = findViewById(R.id.estado);
        tipo = findViewById(R.id.tipo);

        estadoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.estados));
        tipoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tipos));

        estado.setAdapter(estadoAdapter);
        tipo.setAdapter(tipoAdapter);

        estadoAdapter.notifyDataSetChanged();
        tipoAdapter.notifyDataSetChanged();

        if (partes_class != null) {
            asunto.setText(partes_class.getNombre());
            descripcion.setText(partes_class.getDescripcion());
            estado.setSelection(partes_class.getEstado());
            tipo.setSelection(partes_class.getTipo());
        }

        FloatingActionButton guardar = findViewById(R.id.ok);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (partes_class == null) { ///< Insert
                    partes_class = new Partes_class(asunto.getText().toString(), descripcion.getText().toString(), parada.getName(), parada.getNumber(), estado.getSelectedItemPosition(), tipo.getSelectedItemPosition());
                    partes_class.setDate(Calendar.getInstance().getTime());
                    String[] campos = partes_class.getCampos();
                    String[] valores = partes_class.getValores();
                    databaseConnector.InsertarComunicado(Constants.tabla, campos, valores);
                } else {                            ///< Update
                    partes_class = new Partes_class(asunto.getText().toString(), descripcion.getText().toString(), parada.getName(), parada.getNumber(), estado.getSelectedItemPosition(), tipo.getSelectedItemPosition());
                    String[] campos = partes_class.getCampos();
                    if (partes_class.getDate() == null) {
                        partes_class.setDate(date);
                    }

                    String[] valores = partes_class.getValores();
                    Log.d(Constants.CLASS_PARTES, "Recuperados los valores");
                    databaseConnector.ActualizarComunicado(Constants.tabla, campos, valores, Constants.date + " = '" + GeneralMethods.dateToString(partes_class.getDate()) + "'" +
                            " and " + Constants.paradaID + " = " + partes_class.getParadaID());
                }
                onBackPressed();
            }
        });
        FloatingActionButton eliminar = findViewById(R.id.delete);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Esto elimina.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                databaseConnector.BorrarComunicado(Constants.tabla, Constants.date + " = '" + GeneralMethods.dateToString(partes_class.getDate()) + "'" +
                        " and " + Constants.paradaID + " = " + partes_class.getParadaID());
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
