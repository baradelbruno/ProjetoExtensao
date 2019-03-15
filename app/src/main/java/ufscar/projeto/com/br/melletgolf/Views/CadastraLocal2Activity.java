package ufscar.projeto.com.br.melletgolf.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastraLocal2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_local3);
        final ArrayList<String> pares = getIntent().getExtras().getStringArrayList("pares");

        Button btnEncerrar = (Button) findViewById(R.id.buttonEncerrar);
        btnEncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (salvaPares(pares)) {
                    //salva no bd
                    Toast.makeText(CadastraLocalActivity.this, "Deu bom", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CadastraLocalActivity.this, "Existem alguns campos vazios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean salvaPares(ArrayList<String> pares) {
        EditText text;

        text = findViewById(R.id.par1);
        if (TextUtils.isEmpty(text.getText().toString())) {
            return false;
        } else {
            pares.set(9, text.getText().toString());
        }

        text = findViewById(R.id.par2);
        if (TextUtils.isEmpty(text.getText().toString())) {
            return false;
        } else {
            pares.set(10, text.getText().toString());
        }

        text = findViewById(R.id.par3);
        if (TextUtils.isEmpty(text.getText().toString())) {
            return false;
        } else {
            pares.set(11, text.getText().toString());
        }

        text = findViewById(R.id.par4);
        if (TextUtils.isEmpty(text.getText().toString())) {
            return false;
        } else {
            pares.set(12, text.getText().toString());
        }

        text = findViewById(R.id.par5);
        if (TextUtils.isEmpty(text.getText().toString())) {
            return false;
        } else {
            pares.set(13, text.getText().toString());
        }

        text = findViewById(R.id.par6);
        if (TextUtils.isEmpty(text.getText().toString())) {
            return false;
        } else {
            pares.set(14, text.getText().toString());
        }

        text = findViewById(R.id.par7);
        if (TextUtils.isEmpty(text.getText().toString())) {
            return false;
        } else {
            pares.set(15, text.getText().toString());
        }

        text = findViewById(R.id.par8);
        if (TextUtils.isEmpty(text.getText().toString())) {
            return false;
        } else {
            pares.set(16, text.getText().toString());
        }

        text = findViewById(R.id.par9);
        if (TextUtils.isEmpty(text.getText().toString())) {
            return false;
        } else {
            pares.set(17, text.getText().toString());
        }

        return true;
    }
}