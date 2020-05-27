package Models;

import Models.Part;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InHouse extends Part {

    private int machineID;

    public InHouse(int id, String name, int stock, double price, int min, int max, int machineID) {
        setID(id);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMax(max);
        setMin(min);
        setMachineID(machineID);
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID;
    }

}