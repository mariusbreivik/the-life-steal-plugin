package no.breaks.tlsp.listener;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.command.CommandResult;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import no.breaks.tlsp.TheLifeStealPlugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerListenerTest {

    private ServerMock server;
    private TheLifeStealPlugin plugin;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(TheLifeStealPlugin.class);
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void revivePlayerShouldResultInFailedCommandResultAndMessage() {
        PlayerMock playerMock = server.addPlayer("MockPlayer");
        CommandResult commandResult = server.executeConsole("lsp-revive", "MockPlayer");
        commandResult.assertFailed();
        commandResult.assertResponse("Player " + playerMock.getDisplayName() + " not dead");
    }

    @Test
    void revivePlayerShouldResultInSucceededCommandResultAndMessage() {
        PlayerMock killedPlayer = server.addPlayer("KilledPlayer");
        PlayerMock killer = server.addPlayer("Killer");
        killedPlayer.damage(500, killer);
        assertTrue(killer.performCommand("lsp-revive KilledPlayer"));
    }
}