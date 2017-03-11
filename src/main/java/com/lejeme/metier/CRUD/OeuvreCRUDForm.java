package com.lejeme.metier.CRUD;


import com.lejeme.metier.Oeuvrevente;

import java.util.List;

/**
 * Created by jeremy on 19/02/2017.
 */
public class OeuvreCRUDForm extends AbstractCRUDForm<Oeuvrevente> {

    public OeuvreCRUDForm(List<Oeuvrevente> data) {
        super(data);
    }

    protected String getId(Oeuvrevente record) {
        return "" + record.getIdOeuvrevente();
    }

    public String getTypeName() {
        return "workofart";
    }

    public int getNumberOfFields() {
        return 3;
    }

    public String[] getArrayOfColumnNames() {
        return new String[] {"Title","Price","Owner"};
    }

    protected String getDataAtColumn(Oeuvrevente record, int column) {
        switch (column) {
            case 0:
                return "" + record.getTitreOeuvrevente();
            case 1:
                return "" + record.getPrixOeuvrevente();
            case 2:
                return record.getProprietaire().getNomProprietaire() + " " + record.getProprietaire().getPrenomProprietaire();
            default:
                return "a";
//                throw new IllegalArgumentException(Messages.getString("DataValidationConfigurationCrudForm.YouShouldntHaveGottenHere")); //$NON-NLS-1$
        }
    }
}
