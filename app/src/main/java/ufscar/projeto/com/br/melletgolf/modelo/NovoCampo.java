package ufscar.projeto.com.br.melletgolf.modelo;

import java.util.Date;

public class NovoCampo {

   private String idLocal;

   public String getIdLocal() {
      return idLocal;
   }

   public void setIdLocal(String idLocal) {
      this.idLocal = idLocal;
   }

   public String getIdJogador() {
      return idJogador;
   }

   public void setIdJogador(String idJogador) {
      this.idJogador = idJogador;
   }

   public String getIdUI() {
      return idUI;
   }

   public void setIdUI(String idUI) {
      this.idUI = idUI;
   }

   public Date getDataJogo() {
      return dataJogo;
   }

   public void setDataJogo(Date dataJogo) {
      this.dataJogo = dataJogo;
   }

   private String idJogador;
   private String idUI;
   private Date dataJogo;

}
