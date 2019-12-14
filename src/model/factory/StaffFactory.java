package model.factory;

import model.items.Staff;

public class StaffFactory {

    public Staff create() {
        return new Staff("Staff",20,1,3);
    }
}
