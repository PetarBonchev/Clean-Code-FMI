package org.example.database.dataTypes;

import java.time.LocalDate;

public record LoggedWater(LocalDate date, int amount) {

    public String toString() {
        return amount + "ml";
    }
}
