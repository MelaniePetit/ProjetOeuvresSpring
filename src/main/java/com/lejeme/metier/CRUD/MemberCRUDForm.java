package com.lejeme.metier.CRUD;


import com.lejeme.metier.Member;

import java.util.List;

public class MemberCRUDForm extends AbstractCRUDForm<Member> {

    public MemberCRUDForm(List<Member> data) {
        super(data);
    }

    protected String getId(Member record) {
        return "" + record.getId();
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

    protected String getDataAtColumn(Member record, int column) {
        switch (column) {
            case 0:
                return "" + record.getId();
            case 1:
                return record.getName();
            case 2:
                return record.getFirstName();
            case 3:
                return record.getCity();
            default:
                return "a";
//                throw new IllegalArgumentException(Messages.getString("DataValidationConfigurationCrudForm.YouShouldntHaveGottenHere")); //$NON-NLS-1$
        }
    }
}
