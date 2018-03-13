package es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Vista;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Constants;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.Modelo.Parada;
import es.uv.jorgemartinezhernandez.ignaciogomislli.valenbisi.R;

public class parada extends AppCompatActivity {

    private Parada p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parada);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        p = getIntent().getParcelableExtra(Constants.CLASS_PARADA);
    }

}
