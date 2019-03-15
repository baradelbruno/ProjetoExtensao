package ufscar.projeto.com.br.melletgolf.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class cartaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao);

        final ArrayList<String> scores;

        if (getIntent().getExtras() == null) {
            scores = novoArrayList(18);
        } else {
            scores = getIntent().getExtras().getStringArrayList("valores");
            colocaTexto(scores);
        }

        Button btnProximo = findViewById(R.id.buttonEncerrar);
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

    public void colocaTexto(ArrayList<String> scores){
        EditText text;

        text = findViewById(R.id.score1);
        if (!scores.get(0).equals("0")) {
            text.setText(scores.get(0));
        }

        text = findViewById(R.id.score2);
        if (!scores.get(1).equals("0")) {
            text.setText(scores.get(1));
        }

        text = findViewById(R.id.score3);
        if (!scores.get(2).equals("0")) {
            text.setText(scores.get(2));
        }

        text = findViewById(R.id.score4);
        if (!scores.get(3).equals("0")) {
            text.setText(scores.get(3));
        }

        text = findViewById(R.id.score5);
        if (!scores.get(4).equals("0")) {
            text.setText(scores.get(4));
        }

        text = findViewById(R.id.score6);
        if (!scores.get(5).equals("0")) {
            text.setText(scores.get(5));
        }

        text = findViewById(R.id.score7);
        if (!scores.get(6).equals("0")) {
            text.setText(scores.get(6));
        }

        text = findViewById(R.id.score8);
        if (!scores.get(7).equals("0")) {
            text.setText(scores.get(7));
        }

        text = findViewById(R.id.score9);
        if (!scores.get(8).equals("0")) {
            text.setText(scores.get(8));
        }
    }

    private void salvaScores(ArrayList<String> scores){
        EditText text;

        text = findViewById(R.id.score1);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(0, text.getText().toString());
        }

        text = findViewById(R.id.score2);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(1, text.getText().toString());
        }

        text = findViewById(R.id.score3);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(2, text.getText().toString());
        }

        text = findViewById(R.id.score4);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(3, text.getText().toString());
        }

        text = findViewById(R.id.score5);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(4, text.getText().toString());
        }

        text = findViewById(R.id.score6);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(5, text.getText().toString());
        }

        text = findViewById(R.id.score7);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(6, text.getText().toString());
        }

        text = findViewById(R.id.score8);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(7, text.getText().toString());
        }

        text = findViewById(R.id.score9);
        if (!TextUtils.isEmpty(text.getText().toString())){
            scores.set(8, text.getText().toString());
        }
    }
}


