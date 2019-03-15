package ufscar.projeto.com.br.melletgolf.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class cartao2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao2);

        final ArrayList<String> scores = getIntent().getExtras().getStringArrayList("valores");
        colocaTexto(scores);

        Button btnVoltar = (Button) findViewById(R.id.buttonVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent novaTela = new Intent(getApplicationContext(), cartaoActivity.class);

            salvaScores(scores);
            novaTela.putStringArrayListExtra("valores", scores);
            startActivity(novaTela);
            }
        });

        Button btnEncerrar = (Button) findViewById(R.id.buttonEncerrar);
        btnEncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            int total = somaScores(scores);
            }
        });
    }

    public void colocaTexto(ArrayList<String> scores) {
        EditText text;
        scores = getIntent().getExtras().getStringArrayList("valores");

        if (!scores.get(9).equals("0")) {
            text = findViewById(R.id.score1);
            text.setText(scores.get(9));
        }

        if (!scores.get(10).equals("0")) {
            text = findViewById(R.id.score2);
            text.setText(scores.get(10));
        }

        if (!scores.get(11).equals("0")) {
            text = findViewById(R.id.score3);
            text.setText(scores.get(11));
        }

        if (!scores.get(12).equals("0")) {
            text = findViewById(R.id.score4);
            text.setText(scores.get(12));
        }

        if (!scores.get(13).equals("0")) {
            text = findViewById(R.id.score5);
            text.setText(scores.get(13));
        }

        if (!scores.get(14).equals("0")) {
            text = findViewById(R.id.score6);
            text.setText(scores.get(14));
        }

        if (!scores.get(15).equals("0")) {
            text = findViewById(R.id.score7);
            text.setText(scores.get(15));
        }

        if (!scores.get(16).equals("0")) {
            text = findViewById(R.id.score8);
            text.setText(scores.get(16));
        }

        if (!scores.get(17).equals("0")) {
            text = findViewById(R.id.score9);
            text.setText(scores.get(17));
        }
    }

    private void salvaScores(ArrayList<String> scores){
        EditText text;

        text = findViewById(R.id.score1);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(9, text.getText().toString());
        }

        text = findViewById(R.id.score2);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(10, text.getText().toString());
        }

        text = findViewById(R.id.score3);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(11, text.getText().toString());
        }

        text = findViewById(R.id.score4);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(12, text.getText().toString());
        }

        text = findViewById(R.id.score5);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(13, text.getText().toString());
        }

        text = findViewById(R.id.score6);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(14, text.getText().toString());
        }

        text = findViewById(R.id.score7);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(15, text.getText().toString());
        }

        text = findViewById(R.id.score8);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(16, text.getText().toString());
        }

        text = findViewById(R.id.score9);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(17, text.getText().toString());
        }
    }

    private int somaScores(ArrayList<String> scores){
        int soma = 0;

        for(int i = 0; i < 18; i++){
            soma += Integer.parseInt(scores.get(i));
        }

        return soma;
    }
}
