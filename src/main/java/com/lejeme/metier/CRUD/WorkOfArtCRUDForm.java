package com.lejeme.metier.CRUD;

import com.lejeme.metier.WorkOfArt;

import java.util.List;

public class WorkOfArtCRUDForm extends AbstractCRUDForm<WorkOfArt> {

    public WorkOfArtCRUDForm(List<WorkOfArt> data) {
        super(data);
    }

    protected String getId(WorkOfArt record) {
        return "" + record.getId();
    }

    public String getTypeName() {
        return "worksofart";
    }

    public int getNumberOfFields() {
        return 3;
    }

    public String[] getArrayOfColumnNames() {
        return new String[] {"Title","Price","Owner"};
    }

    protected String getDataAtColumn(WorkOfArt record, int column) {
        switch (column) {
            case 0:
                return "" + record.getTitle();
            case 1:
                return "" + record.getPrice();
            case 2:
                return record.getOwner().getName() + " " + record.getOwner().getFirstName();
            default:
                return "a";
//                throw new IllegalArgumentException(Messages.getString("DataValidationConfigurationCrudForm.YouShouldntHaveGottenHere")); //$NON-NLS-1$
        }
    }
}
