package ufscar.projeto.com.br.melletgolf;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import ufscar.projeto.com.br.melletgolf.DAO.ConfiguracaoFirebase;
import ufscar.projeto.com.br.melletgolf.modelo.DadosJogador;
import ufscar.projeto.com.br.melletgolf.modelo.NovoCampo;

public class addNovoJogoActivity extends AppCompatActivity {

    private String pegarCod;
    private String pegarNome;
    private String pegarSobrenome;
    private String pegarDate;
    private String pegarFoto;

    private EditText categoria;
    private EditText grupo;
    private EditText entidade;
    private EditText local;
    private EditText datajogo;

    private Calendar c;

    private NovoCampo novoCampo;
    private DadosJogador jogador;

    private DatabaseReference myRef;
    private String myFormat = "dd/MM/yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_novo_jogo);

        pegarCod = getIntent().getExtras().getString("codigo");
        pegarNome = getIntent().getExtras().getString("nome");
        pegarSobrenome = getIntent().getExtras().getString("sobrenome");
        pegarDate = getIntent().getExtras().getString("data");
        pegarFoto = getIntent().getExtras().getString("foto");

        categoria = findViewById(R.id.editarCategoria);
        grupo = findViewById(R.id.editarGrupo);
        entidade = findViewById(R.id.editarEntidade);
        local = findViewById(R.id.editarLocalJogo);
        datajogo = findViewById(R.id.editarData);

        Button bnt = findViewById(R.id.saveNewGameButton);
        Button editcard = findViewById(R.id.editCardButton);
        calendario();
        inicializarFirebase();
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarDados();
            }
        });
        editcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irparacartao = new Intent(addNovoJogoActivity.this,cartaoActivity.class);
                startActivity(irparacartao);
            }
        });

    }

    private void calendario() {
        c = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        datajogo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(addNovoJogoActivity.this, date,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        datajogo.setText(sdf.format(c.getTime()));

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(addNovoJogoActivity.this);
        myRef = ConfiguracaoFirebase.getFirebase();
    }

    private void salvarDados() {


        novoCampo = new NovoCampo();
        jogador = new DadosJogador();

       // Intent prox = new Intent(addNovoJogoActivity.this,addNovoJogoActivity.class);

        /*atualizar jogador*/
        jogador.setCod(pegarCod);
        jogador.setNome(pegarNome);
        jogador.setSobrenome(pegarSobrenome);

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        try {
            Date val_data = sdf.parse(pegarDate);
            jogador.setDataNascimento(val_data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i("Foto",pegarFoto);

        jogador.setFoto(pegarFoto);
        /*falta passar a foto e verificar se é null*/

        jogador.setCategoria(categoria.getText().toString());
        jogador.setEntidade(entidade.getText().toString());

        //int intgrupo = Integer.parseInt(grupo.getText().toString());

        novoCampo.setIdUI(UUID.randomUUID().toString());
        novoCampo.setIdJogador(pegarCod);
        setData();

        myRef.child("Jogador").child(pegarCod).setValue(jogador);
        myRef.child("NovoCampo").child(novoCampo.getIdUI()).setValue(novoCampo);

        limparcampo();
    }

    private void limparcampo() {
        categoria.setText("");
        grupo.setText("");
        entidade.setText("");
        local.setText("");
        datajogo.setText("");
    }

    private void setData() {
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        try {
            Date val_data = sdf.parse(datajogo.getText().toString());
            novoCampo.setDataJogo(val_data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
