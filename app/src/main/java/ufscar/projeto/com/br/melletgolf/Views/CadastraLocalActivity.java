package ufscar.projeto.com.br.melletgolf.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastraLocalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_local);

        Button btnProximo = (Button) findViewById(R.id.buttonProximo);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent proxTela = new Intent(getApplicationContext(), CadastraLocal2Activity.class);
            ArrayList<String> pares = novoArrayList(18);
            EditText nomeCampo = findViewById(R.id.textNomeCampo);

            if (!TextUtils.isEmpty(nomeCampo.getText().toString()) && salvaPares(pares)) {
                pares.add(nomeCampo.getText().toString());
                proxTela.putStringArrayListExtra("pares", pares);
                startActivity(proxTela);
            } else {
                Toast.makeText(CadastraLocalActivity.this, "Existem campos vazios", Toast.LENGTH_LONG).show();
            }
            }
        });
    }

    private boolean salvaPares(ArrayList<String> pares){
        EditText text;

        text = findViewById(R.id.par1);
        if (TextUtils.isEmpty(text.getText().toString())){
            return false;
        } else {
            pares.set(0, text.getText().toString());
        }

        text = findViewById(R.id.par2);
        if (TextUtils.isEmpty(text.getText().toString())){
            return false;
        } else {
            pares.set(1, text.getText().toString());
        }

        text = findViewById(R.id.par3);
        if (TextUtils.isEmpty(text.getText().toString())){
            return false;
        } else {
            pares.set(2, text.getText().toString());
        }

        text = findViewById(R.id.par4);
        if (TextUtils.isEmpty(text.getText().toString())){
            return false;
        } else {
            pares.set(3, text.getText().toString());
        }

        text = findViewById(R.id.par5);
        if (TextUtils.isEmpty(text.getText().toString())){
            return false;
        } else {
            pares.set(4, text.getText().toString());
        }

        text = findViewById(R.id.par6);
        if (TextUtils.isEmpty(text.getText().toString())){
            return false;
        } else {
            pares.set(5, text.getText().toString());
        }

        text = findViewById(R.id.par7);
        if (TextUtils.isEmpty(text.getText().toString())){
            return false;
        } else {
            pares.set(6, text.getText().toString());
        }

        text = findViewById(R.id.par8);
        if (TextUtils.isEmpty(text.getText().toString())){
            return false;
        } else {
            pares.set(7, text.getText().toString());
        }

        text = findViewById(R.id.par9);
        if (TextUtils.isEmpty(text.getText().toString())){
            return false;
        } else {
            pares.set(8, text.getText().toString());
        }

        return true;
    }

    private ArrayList<String> novoArrayList(int tam){
        ArrayList<String> novo = new ArrayList<>();

        for (int i = 0; i < tam; i++){
            novo.add("0");
        }

        return novo;
    }
}