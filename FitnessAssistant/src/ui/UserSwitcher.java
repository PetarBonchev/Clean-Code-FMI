package org.example.ui;

import org.example.commands.CommandContainer;
import org.example.commands.CommandFactory;
import org.example.commands.concreteCommands.ChangeUser;
import org.example.database.LoadSaveManager;
import org.example.repository.RepositoryContainer;
import org.example.repository.RepositoryFactory;
import org.example.repository.RepositorySaver;

public final class UserSwitcher {

    private String currentUser;
    private RepositoryContainer repositories;
    private CommandContainer commands;
    private final LoadSaveManager database;
    private final CommunicationChannel io;
    private RepositorySaver repositorySaver;
    private CommandReader commandReader;

    public UserSwitcher(CommunicationChannel communicationChannel,
                        LoadSaveManager loadSaveManager) {
        this.io = communicationChannel;
        this.database = loadSaveManager;
        this.currentUser = "default";
        this.repositorySaver = new RepositorySaver(loadSaveManager);
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String username) {
        if (username.equals(currentUser)) {
            return;
        }

        savePreviousUserData();
        currentUser = username;
        loadNextUserData();
        commandReader.setCommands(commands);
    }

    public void load() {
        loadNextUserData();
    }

    public void save() {
        if (repositories != null && !currentUser.isEmpty()) {
            repositorySaver.save(repositories, currentUser);
        }
    }

    private void savePreviousUserData() {
        if (repositories != null && !currentUser.isEmpty()) {
            repositorySaver.save(repositories, currentUser);
        }
    }

    private void loadNextUserData() {
        RepositoryFactory repositoryFactory = new RepositoryFactory(database);
        repositories = repositoryFactory.createRepositories(currentUser);
        CommandFactory commandFactory = new CommandFactory(io, repositories);
        commands = commandFactory.createCommands();
        ChangeUser specialCommand = new ChangeUser(io, this);
        commands.add(specialCommand);
    }

    public CommandContainer getCommands() {
        return commands;
    }

    public void setCommandReader(CommandReader reader) {
        this.commandReader = reader;
    }
}
