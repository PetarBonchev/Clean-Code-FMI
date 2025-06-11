package org.example.repository;

import org.example.repository.concrete.LoggedWaterRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RepositoryContainerTest {

    @Test
    void getByIndex() {
        LoggedWaterRepository repo = new LoggedWaterRepository(null);
        RepositoryContainer repositoryContainer = new RepositoryContainer();
        repositoryContainer.add(repo);
        NameableRepository stored = repositoryContainer.getByIndex(0);
        assertEquals(repo, (LoggedWaterRepository) stored);
    }

    @Test
    void getByName() {
        LoggedWaterRepository repo = new LoggedWaterRepository(null);
        RepositoryContainer repositoryContainer = new RepositoryContainer();
        repositoryContainer.add(repo);
        assertEquals(repo, repositoryContainer.getByName("logged water"));
    }

    @Test
    void getByNameNotFound() {
        LoggedWaterRepository repo = new LoggedWaterRepository(null);
        RepositoryContainer repositoryContainer = new RepositoryContainer();
        repositoryContainer.add(repo);
        assertNull(repositoryContainer.getByName("water"));
    }

    @Test
    void getAllRepositories() {
        LoggedWaterRepository repo = new LoggedWaterRepository(null);
        RepositoryContainer repositoryContainer = new RepositoryContainer();
        repositoryContainer.add(repo);
        assertEquals(repo, repositoryContainer.getAllRepositories().get(0));
    }
}
