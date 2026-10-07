package piece;

public class PieceMachineLib extends PieceMachine {
    private String idPieceLib, idMachineLib;
    public PieceMachineLib() throws Exception {
        this.setNomTable("PieceMachineLib");
    }

    public String getIdPieceLib() {
        return idPieceLib;
    }

    public void setIdPieceLib(String idPieceLib) {
        this.idPieceLib = idPieceLib;
    }

    public String getIdMachineLib() {
        return idMachineLib;
    }

    public void setIdMachineLib(String idMachineLib) {
        this.idMachineLib = idMachineLib;
    }
}
