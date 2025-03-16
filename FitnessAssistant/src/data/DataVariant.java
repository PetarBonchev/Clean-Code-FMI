package data;

import java.util.ArrayList;

abstract class DataVariant {

    public DataVariant(ArrayList<String> data) {}

    public abstract String getDataAsMessage();

    public String getDataAsTokenLine() {
        ArrayList<String> tokens = getData();
        StringBuilder tokenLine = new StringBuilder();
        for(String token : tokens) {
            tokenLine.append(token.length()).append(TokenTable.TOKEN_SIZE_CONTENT_SEPARATOR);
            tokenLine.append(token).append(" ");
        }
        return tokenLine.toString();
    }

    public abstract ArrayList<String> getData();
}