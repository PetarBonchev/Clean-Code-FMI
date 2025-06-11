package org.example;

import org.example.ui.CommunicationChannel;
import org.example.database.LoadSaveManager;
import org.example.ui.ApplicationRunner;

public final class Main {

    private Main() { }

    public static void main(String[] args) {

        CommunicationChannel io = new CommunicationChannel(System.in,
                System.out);
        LoadSaveManager loadSaveManager = new LoadSaveManager();

        ApplicationRunner applicationRunner =
                new ApplicationRunner(io, loadSaveManager);
        try {
            applicationRunner.run();
        } catch (Exception e) {
            System.err.println("CRASH: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
