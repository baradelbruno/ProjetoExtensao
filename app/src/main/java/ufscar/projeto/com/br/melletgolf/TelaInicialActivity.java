package ufscar.projeto.com.br.melletgolf;

import android.Manifest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ufscar.projeto.com.br.melletgolf.DAO.ConfiguracaoFirebase;
import ufscar.projeto.com.br.melletgolf.modelo.DadosJogador;

@RequiresApi(api = Build.VERSION_CODES.M)
public class TelaInicialActivity extends AppCompatActivity {

    private DadosJogador jogador;
    private ImageView perfil_img;
    private TextView nome;
    private TextView codigotext;
    private TextView Sobrenome;
    private String pegarCod;
    private String pegarNome;
    private String pegarSobrenome;
    /*Identificador para a camera e a galeria*/
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_GALERIA = 0;
    String caminhofoto = null;
    CharSequence[] items = {"Câmera","Galeria"};
    private String pegarData;
    private DatabaseReference myRef;
    String myFormat = "dd/MM/yyyy";
    Button novo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        pegarCod = getIntent().getExtras().getString("codigo");
        pegarNome = getIntent().getExtras().getString("nome");
        pegarData = getIntent().getExtras().getString("data");
        pegarSobrenome = getIntent().getExtras().getString("sobrenome");

        perfil_img = findViewById(R.id.image_view_tela_inicial_perfil);
        nome = findViewById(R.id.txt_inicial_nome);
        codigotext = findViewById(R.id.txt_inicial_cod);
        Sobrenome = findViewById(R.id.txt_inicial_sobrenome);
        novo = findViewById(R.id.bntNovo_jogo);

        inicializarFirebase();
        recuperarDados();

        perfil_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*permissao para galeria e camera*/
                getpermissao();
            }
        });
    }
    private void recuperarDados() {

        jogador = new DadosJogador();
        myRef.child("Jogador").child(pegarCod).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                jogador = dataSnapshot.getValue(DadosJogador.class);

                if(dataSnapshot.child("foto").exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(jogador.getFoto());
                    perfil_img.setImageBitmap(bitmap);
                }else{
                    perfil_img.setImageResource(R.drawable.person);
                }
                nome.setText(jogador.getNome());
                Sobrenome.setText(jogador.getSobrenome());
                codigotext.setText(jogador.getCod());


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TelaInicialActivity.this,"Erro de leitura no banco de dados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(TelaInicialActivity.this);
        myRef = ConfiguracaoFirebase.getFirebase();
    }

    /*funcao para permissao*/
    private void getpermissao() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        else
            selecionarImagem();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selecionarImagem();
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    /* MENU */
    private void selecionarImagem() {


       /* Dialog dialog = new Dialog(TelaInicialActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customizardialog);
        dialog.setTitle("Foto de Perfil");

        TextView galeria = dialog.findViewById(R.id.txt_galeria_sem_img);
        TextView camera = dialog.findViewById(R.id.txt_camera_sem_img);


        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galeriaIntent();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntent();
            }
        });
        dialog.show();*/

       /* Object tag = perfil_img.getTag();

        if(tag != null) {
            boolean possuifoto = (boolean) tag;

            if (possuifoto) {
                items = new CharSequence[]{"Câmera", "Galeria", "Remover foto"};
            }
        }*/
        AlertDialog.Builder builder = new AlertDialog.Builder(TelaInicialActivity.this);
        builder.setTitle("Foto de Perfil");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (items[which].equals("Câmera")) {
                    cameraIntent();
                } else if (items[which].equals("Galeria")) {
                    galeriaIntent();
                }/*else if(items[which].equals("Remover foto")) {
                    removerFoto();
                }*/
            }
        });
        builder.show();
    }

    private void removerFoto() {

        perfil_img.setTag(null);
        perfil_img.setImageResource(R.drawable.usuario96);
        items = new CharSequence[]{"Câmera", "Galeria"};
        /*limpar a variavel caminho ou deletar a imagem do celular*/
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == REQUEST_CAMERA){
            if(resultCode == RESULT_OK) {
                //abrir a foto que foi tirada(camera)
                Bitmap bitmap = BitmapFactory.decodeFile(caminhofoto);

                perfil_img.setImageBitmap(bitmap);
                perfil_img.setTag(caminhofoto);

                setJogador();
            }

        }else if(requestCode == REQUEST_GALERIA){

            if(resultCode == RESULT_OK) {

                Uri imagemSelecionada = data.getData();

                String[] colunas = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imagemSelecionada, colunas, null, null, null);
                cursor.moveToFirst();

                int indexColuna = cursor.getColumnIndex(colunas[0]);
                caminhofoto = cursor.getString(indexColuna);
                cursor.close();

                Bitmap bitmap = BitmapFactory.decodeFile(caminhofoto);
                perfil_img.setImageBitmap(bitmap);
                perfil_img.setTag(caminhofoto);

                setJogador();
            }
        }
    }

    private void setJogador() {
        jogador = new DadosJogador();

        jogador.setCod(pegarCod);
        jogador.setNome(pegarNome);
        jogador.setSobrenome(pegarSobrenome);

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        try {
            Date val_data = sdf.parse(pegarData);
            jogador.setDataNascimento(val_data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jogador.setFoto((String) perfil_img.getTag());
        
        myRef.child("Jogador").child(pegarCod).setValue(jogador);
    }

    /*tira foto pelo camera*/
    private void cameraIntent() {

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        caminhofoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
        File arquivoFoto = new File(caminhofoto);
        Uri fotoURI = FileProvider.getUriForFile(TelaInicialActivity.this, BuildConfig.APPLICATION_ID + ".provider", arquivoFoto);

        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT,fotoURI);
        startActivityForResult(intentCamera,REQUEST_CAMERA);
    }

    /*Pegar da Galeria*/
    private void galeriaIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent,"Selecionar Imagem"),REQUEST_GALERIA);
    }


    public void btnNovo(View view) {

        Intent proxtela = new Intent(TelaInicialActivity.this,addNovoJogoActivity.class);
        startActivity(proxtela);
    }
}


