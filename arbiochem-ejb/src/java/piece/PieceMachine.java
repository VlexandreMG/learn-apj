package piece;

import bean.ClassMAPTable;
import bean.CGenUtil;
import mg.cnaps.compta.ClotureMoisAnnee;
import produits.Recette;

import java.sql.Connection;

public class PieceMachine extends ClassMAPTable {
    private String id;
    private String idPiece;
    private String idMachine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(String idPiece) {
        this.idPiece = idPiece;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }



    public PieceMachine() throws Exception {
        this.setNomTable("PIECEMACHINE");
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("PCM","GET_SEQ_PIECEMACHINE");
        this.setId(makePK(c));
    }

    public void checkPieceDejaAttribueMachine(Connection c) throws Exception {
        PieceMachine pcm = new PieceMachine();
        pcm.setIdMachine(this.getIdMachine());
        pcm.setIdPiece(this.getIdPiece());
        PieceMachine[] cmds = (PieceMachine[]) CGenUtil.rechercher(pcm, null, null, c, " ");
        if(cmds.length>0){
            throw new Exception("La Piece est deja attribue pour l'element");
        }
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        this.checkPieceDejaAttribueMachine(c);
        return super.createObject(u, c);
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
}

