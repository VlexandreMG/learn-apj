/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facture;

import bean.CGenUtil;
import bean.ClassMAPTable;
import java.sql.Connection;


public class ReferenceFacture extends ClassMAPTable{

    private String id,idFacture,reference;

    public ReferenceFacture(){
        this.setNomTable("REFERENCEFACTURE");
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("RFAC", "GETSEQREFERENCEFACTURE");
        this.setId(makePK(c));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(String idFacture)throws Exception {
        if(idFacture.equalsIgnoreCase("")||idFacture==null){
            throw new Exception("La Facture ne peut pas \u00EAtre vide !");
        }
        this.idFacture = idFacture;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public ClassMAPTable createObject(String u, Connection c) throws Exception {
        ReferenceFacture[] ref = (ReferenceFacture[]) CGenUtil.rechercher(new ReferenceFacture(), null, null, c, " and idFacture = '"+this.getIdFacture()+"' ");
        ClassMAPTable a = null;
        if (ref.length>0) {
            throw new Exception("La facture a d\u00E9j\u00E0 comme r\u00E9f\u00E9rence :"+ ref[0].getReference());
        }
        try {
            a = super.createObject(u, c);
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        return a;
    }
}
