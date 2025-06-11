package org.example.commands.concreteCommands;

import org.example.common.BreakLoopException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExitApplicationTest {

    @Test
    void getName() {
        ExitApplication exitApplication = new ExitApplication(null);
        assertEquals("Exit", exitApplication.getName());
    }

    @Test
    void execute() {
        ExitApplication exitCommand = new ExitApplication(null);
        assertThrows(BreakLoopException.class, exitCommand::execute);
    }
}
