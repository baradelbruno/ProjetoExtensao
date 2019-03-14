package ufscar.projeto.com.br.melletgolf;

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
    }

    public void colocaTexto(ArrayList<String> scores) {
        EditText text;
        scores = getIntent().getExtras().getStringArrayList("valores");

        text = findViewById(R.id.score1);
        if (!scores.get(9).equals("0")) {
            text.setText(scores.get(9));
        }

        text = findViewById(R.id.score2);
        if (!scores.get(10).equals("0")) {
            text.setText(scores.get(10));
        }

        text = findViewById(R.id.score3);
        if (!scores.get(11).equals("0")) {
            text.setText(scores.get(11));
        }

    }

    private void salvaScores(ArrayList<String> scores){
        EditText text;

        text = findViewById(R.id.score1);
        scores.set(9, text.getText().toString());

        text = findViewById(R.id.score2);
        scores.set(10, text.getText().toString());

        text = findViewById(R.id.score3);
        scores.set(11, text.getText().toString());

        text = findViewById(R.id.score4);
        scores.set(12, text.getText().toString());

        text = findViewById(R.id.score5);
        scores.set(13, text.getText().toString());

        text = findViewById(R.id.score6);
        scores.set(14, text.getText().toString());

        text = findViewById(R.id.score7);
        scores.set(15, text.getText().toString());

        text = findViewById(R.id.score8);
        scores.set(16, text.getText().toString());

        text = findViewById(R.id.score9);
        scores.set(17, text.getText().toString());
    }
}
