package com.lejeme.metier.CRUD;


import com.lejeme.metier.Reservation;

import java.util.List;

public class ReservationCRUDForm extends AbstractCRUDForm<Reservation> {

    public ReservationCRUDForm(List<Reservation> data) {
        super(data);
    }

    protected String getId(Reservation record) {
        return "" + record.getWorkOfArt().getId();
    }

    public String getTypeName() {
        return "reservations";
    }

    public int getNumberOfFields() {
        return 3;
    }

    public String[] getArrayOfColumnNames() {
        return new String[] {"Date","Member","Work of art"};
    }

    protected String getDataAtColumn(Reservation record, int column) {
        switch (column) {
            case 0:
                return "" + record.getDate();
            case 1:
                return "" + record.getMember().getName() + " " +record.getMember().getFirstName();
            case 2:
                return record.getWorkOfArt().getTitle();
            default:
                return "a";
//                throw new IllegalArgumentException(Messages.getString("DataValidationConfigurationCrudForm.YouShouldntHaveGottenHere")); //$NON-NLS-1$
        }
    }
}
