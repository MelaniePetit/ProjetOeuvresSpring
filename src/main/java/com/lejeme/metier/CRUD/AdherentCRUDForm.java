package com.lejeme.metier.CRUD;


import com.lejeme.metier.Adherent;
import java.util.List;

public class AdherentCRUDForm extends AbstractCRUDForm<Adherent> {

    public AdherentCRUDForm(List<Adherent> data) {
        super(data);
    }

    protected String getId(Adherent record) {
        return "" + record.getIdAdherent();
    }

    public String getTypeName() {
        return "members";
    }

    public int getNumberOfFields() {
        return 4;
    }

    public String[] getArrayOfColumnNames() {
        return new String[] {"Id","Lastname","Firstname","City"};
    }

    protected String getDataAtColumn(Adherent record, int column) {
        switch (column) {
            case 0:
                return "" + record.getIdAdherent();
            case 1:
                return record.getNomAdherent();
            case 2:
                return record.getPrenomAdherent();
            case 3:
                return record.getVilleAdherent();
            default:
                return "a";
//                throw new IllegalArgumentException(Messages.getString("DataValidationConfigurationCrudForm.YouShouldntHaveGottenHere")); //$NON-NLS-1$
        }
    }
}
