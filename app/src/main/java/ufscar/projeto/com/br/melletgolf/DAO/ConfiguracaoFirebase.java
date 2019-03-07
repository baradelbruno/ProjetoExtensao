package ufscar.projeto.com.br.melletgolf.DAO;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static DatabaseReference referenceFirebase;
    //private static FirebaseAuth autenticacao;

    public static DatabaseReference getFirebase(){
        if(referenceFirebase == null){
            referenceFirebase = FirebaseDatabase.getInstance().getReference();
            //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        return referenceFirebase;
    }

}

