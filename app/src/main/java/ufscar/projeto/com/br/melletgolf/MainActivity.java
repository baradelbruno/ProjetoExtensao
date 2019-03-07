package ufscar.projeto.com.br.melletgolf;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ufscar.projeto.com.br.melletgolf.DAO.ConfiguracaoFirebase;
import ufscar.projeto.com.br.melletgolf.modelo.DadosJogador;

public class MainActivity extends AppCompatActivity {

    private DadosJogador jogador;

    private EditText data;
    private EditText nome;
    private EditText codigo;

    private Calendar c;

    private TextInputLayout textInputNome;
    private TextInputLayout textInputData;
    private TextInputLayout textInputCodigo;

    private DatabaseReference myRef;

    String myFormat = "dd/MM/yyyy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.edit_text_main_data1);
        nome = findViewById(R.id.edit_main_nome);
        codigo = findViewById(R.id.edit_main_codigo);
        textInputData = findViewById(R.id.edit_text_main_data);
        textInputNome = findViewById(R.id.edit_text_main_nome);
        textInputCodigo = findViewById(R.id.edit_text_main_codigo);

        calendario();
        inicializarFirebase();
    }

    private void VerificarExistencia() {

        final String reconheceCod = codigo.getText().toString().trim();
        final String reconheceNome = nome.getText().toString().trim();
        final String reconhecedata = data.getText().toString().trim();

        validarCampo(reconheceCod,reconheceNome,reconhecedata);

        jogador.setNomeCompleto(reconheceNome);
        jogador.setCod(reconheceCod);
        setData();

        final String dataconvert = convertDateforString(jogador.getDataNascimento());

        /*buscar no firebase*/
        myRef.child("Jogador").child(jogador.getCod()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Intent proxtela = new Intent(MainActivity.this,TelaInicialActivity.class);

                if (dataSnapshot.exists()) {
                    jogador = dataSnapshot.getValue(DadosJogador.class);
                    /*verificar se houve valor repetido e jogador novo*/
                    if (reconheceCod.equals(jogador.getCod())){
                        if(!(reconheceNome.equals(jogador.getNomeCompleto())) ||
                                (!reconhecedata.equals(dataconvert))){
                            Toast.makeText(MainActivity.this, "Esse código já pertence a um jogador ", Toast.LENGTH_LONG).show();

                        }else if(reconheceNome.equals(jogador.getNomeCompleto()) &&
                                    reconhecedata.equals(dataconvert)){

                            passarDadosActivity(proxtela, dataconvert);

                            startActivity(proxtela);
                        }
                    }
                }else{

                    myRef.child("Jogador").child(jogador.getCod()).setValue(jogador);
                    passarDadosActivity(proxtela, dataconvert);
                    startActivity(proxtela);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"Erro de leitura do banco "+ databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void passarDadosActivity(Intent proxtela, String dataconvert) {

        Bundle bundle = new Bundle();

        String cod = jogador.getCod();
        bundle.putString("codigo",cod);
        proxtela.putExtras(bundle);

        String nomeC = jogador.getNomeCompleto();
        bundle.putString("nome",nomeC);
        proxtela.putExtras(bundle);

        String dataP = dataconvert;
        bundle.putString("data", dataP);
        proxtela.putExtras(bundle);
    }

    private void validarCampo(String reconheceCod, String reconheceNome, String reconhecedata) {

        boolean res = false;

        if(isCampVazio(reconheceNome)){
            textInputNome.requestFocus();
            res = true;
        }else if(isCampVazio(reconhecedata)){
            data.requestFocus();
            res = true;
        }else if(isCampVazio(reconheceCod)){
            codigo.requestFocus();
            res = true;
        }

        if(res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos vazios");
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }
    }

    private boolean isCampVazio(String valor){

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return  resultado;
    }

    private String convertDateforString(Date dataNascimento) {

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        try {
            String data = sdf.format(dataNascimento);
            return data;

        }catch ( NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(MainActivity.this);
        myRef = ConfiguracaoFirebase.getFirebase();
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
        data.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel() {

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        data.setText(sdf.format(c.getTime()));

    }

    public void confirmarInput(View v ){

        jogador = new DadosJogador();
        jogador.setNomeCompleto(nome.getText().toString());
        setData();
        jogador.setCod(codigo.getText().toString());

        VerificarExistencia();

    }

    @Override
    protected void onStop() {
        limparcampo();
        super.onStop();
    }

    private void setData() {
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        try {
            Date val_data = sdf.parse(data.getText().toString());
            jogador.setDataNascimento(val_data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void limparcampo() {
        nome.setText("");
        codigo.setText("");
        data.setText("");
    }

}
