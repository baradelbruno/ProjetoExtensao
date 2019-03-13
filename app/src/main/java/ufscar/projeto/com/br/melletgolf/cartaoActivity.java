package ufscar.projeto.com.br.melletgolf;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class cartaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao);

        final ArrayList<String> scores = novoArrayList(18);

        Button btnProximo = findViewById(R.id.buttonConfirmar);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent novaTela = new Intent(getApplicationContext(), cartao2Activity.class);

                salvaScores(scores);
                novaTela.putStringArrayListExtra("valores", scores);
                startActivity(novaTela);
            }
        });
    }

    private ArrayList<String> novoArrayList(int tam){
        ArrayList<String> novo = new ArrayList<>();

        for (int i = 0; i < tam; i++){
            novo.add("0");
        }

        return novo;
    }

    public void colocaTexto(){
        EditText text;
        ArrayList<String> scores;

        scores = getIntent().getExtras().getStringArrayList("valores");

        if (scores.get(0) != null) {
            text = findViewById(R.id.score1);
            text.setText(scores.get(0));
        }

        if (scores.get(1) != null) {
            text = findViewById(R.id.score2);
            text.setText(scores.get(1));
        }

        if (scores.get(2) != null) {
            text = findViewById(R.id.score3);
            text.setText(scores.get(2));
        }
    }

    private void salvaScores(ArrayList<String> scores){
        EditText text;

        text = findViewById(R.id.score1);
        scores.set(0, text.getText().toString());

        text = findViewById(R.id.score2);
        scores.set(1, text.getText().toString());

        text = findViewById(R.id.score3);
        scores.set(2, text.getText().toString());
    }
}