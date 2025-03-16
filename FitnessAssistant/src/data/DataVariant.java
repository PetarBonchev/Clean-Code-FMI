package data;

import java.util.ArrayList;

abstract class DataVariant {

    public DataVariant(ArrayList<String> data) {}

    public abstract String getDataAsMessage();

    public abstract String getDataAsTokenLine();

    public abstract ArrayList<String> getData();
}