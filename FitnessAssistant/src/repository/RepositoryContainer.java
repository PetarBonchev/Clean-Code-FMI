package org.example.repository;

import java.util.ArrayList;
import java.util.List;

public final class RepositoryContainer {
    private final ArrayList<NameableRepository> repositories =
            new ArrayList<>();

    public void add(NameableRepository repository) {
        repositories.add(repository);
    }

    public void clear() {
        repositories.clear();
    }

    public NameableRepository getByIndex(int index) {
        return repositories.get(index);
    }

    public NameableRepository getByName(String name) {
        for (NameableRepository repo : repositories) {
            if (repo.getName().equals(name)) {
                return repo;
            }
        }
        return null;
    }

    public List<NameableRepository> getAllRepositories() {
        return new ArrayList<>(repositories);
    }
}
