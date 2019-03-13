package ufscar.projeto.com.br.melletgolf;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class CadastroLocalActivity extends AppCompatActivity {

        LinearLayout layoutMain;
        EditText qtdHole;
        //String hole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layoutMain = findViewById(R.id.content_main);
        qtdHole = findViewById(R.id.edit_qnt_hole);


        /*if(!hole.equals("")){
            addDefaultLayout();
            Toast.makeText(CadastroLocalActivity.this, "valor" + hole, Toast.LENGTH_LONG).show();
        }*/


        qtdHole.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                String hole = qtdHole.getText().toString().trim();

                if(!hole.isEmpty() && hasFocus) {
                        addDefaultLayout();
                        Toast.makeText(CadastroLocalActivity.this, "valor" + hole, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void addDefaultLayout() {

        View layout = LayoutInflater.from(this).inflate(R.layout.add_campos, null);
        layoutMain.addView(layout);

    }
    private void addVazio(){
        View layout = LayoutInflater.from(this).inflate(R.layout.addvazio, null);
        layoutMain.addView(layout);
    }



}
