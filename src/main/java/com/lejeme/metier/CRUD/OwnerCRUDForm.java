package com.lejeme.metier.CRUD;


import com.lejeme.metier.Owner;

import java.util.List;

/**
 * Created by Mel on 01/03/2017.
 */
public class OwnerCRUDForm extends AbstractCRUDForm<Owner> {
    public OwnerCRUDForm(List<Owner> data) {
        super(data);
    }

    protected String getId(Owner record) {
        return "" + record.getId();
    }

    public String getTypeName() {
        return "owners";
    }

    public int getNumberOfFields() {
        return 3;
    }

    public String[] getArrayOfColumnNames() {
        return new String[]{"Id","Lastname","Firstname"};
    }

    protected String getDataAtColumn(Owner record, int column) {
        switch (column) {
            case 0:
                return "" + record.getId();
            case 1:
                return record.getName();
            case 2:
                return record.getFirstName();
            default:
                return "a";
//                throw new IllegalArgumentException(Messages.getString("DataValidationConfigurationCrudForm.YouShouldntHaveGottenHere")); //$NON-NLS-1$
        }
    }
}
