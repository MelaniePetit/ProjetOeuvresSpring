package com.lejeme.metier.CRUD;


import com.lejeme.metier.Proprietaire;

import java.util.List;

/**
 * Created by Mel on 01/03/2017.
 */
public class ProprietaireCRUDForm extends AbstractCRUDForm<Proprietaire> {
    public ProprietaireCRUDForm(List<Proprietaire> data) {
        super(data);
    }

    protected String getId(Proprietaire record) {
        return "" + record.getIdProprietaire();
    }

    public String getTypeName() {
        return "owner";
    }

    public int getNumberOfFields() {
        return 3;
    }

    public String[] getArrayOfColumnNames() {
        return new String[]{"Id","Lastname","Firstname"};
    }

    protected String getDataAtColumn(Proprietaire record, int column) {
        switch (column) {
            case 0:
                return "" + record.getIdProprietaire();
            case 1:
                return record.getNomProprietaire();
            case 2:
                return record.getPrenomProprietaire();
            default:
                return "a";
//                throw new IllegalArgumentException(Messages.getString("DataValidationConfigurationCrudForm.YouShouldntHaveGottenHere")); //$NON-NLS-1$
        }
    }
}
