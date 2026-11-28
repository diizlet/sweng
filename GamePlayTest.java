import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GamePlayTest
{
    public static void main(String[] args)
    {
        ScriptedBackend backend = new ScriptedBackend(
                state(100, 30, true, 0, 80, GameState.Result.IN_PROGRESS, "Battle started."),
                state(90, 25, true, 1, 60, GameState.Result.IN_PROGRESS, "Turn one."),
                state(80, 20, false, 0, 0, GameState.Result.VICTORY, "You win."));
        ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
        GamePlayUI ui = new GamePlayUI(
                new Scanner("3\n2\n3\n1\n3\n"), new PrintStream(outputBytes));

        new GamePlay(backend, ui).run();

        require(backend.startedDifficulty == Difficulty.HARD, "Difficulty was not sent to backend.");
        require(backend.actions.equals(Arrays.asList(GameAction.ATTACK, GameAction.USE_POTION)),
                "Only available actions should be sent to backend.");
        String output = outputBytes.toString();
        require(output.contains("HP: 90/100"), "Refreshed player status was not rendered.");
        require(output.contains("Victory!") && output.contains("Game over."),
                "Terminal result was not rendered.");
        require(backend.statesReturned == 3, "The game did not stop on the terminal response.");
    }

    private static GameState state(int playerHp, int playerMp, boolean skillOnCooldown,
                                   int potions, int enemyHp, GameState.Result result, String message)
    {
        GameCharacterStatus player = new GameCharacterStatus(
                "Hero", playerHp, 100, playerMp, 30, 20, skillOnCooldown, potions);
        GameCharacterStatus enemy = new GameCharacterStatus(
                "Slime", enemyHp, 80, 0, 0, 10, false, 0);
        return new GameState(player, enemy, result, message);
    }

    private static void require(boolean condition, String message)
    {
        if (!condition)
        {
            throw new AssertionError(message);
        }
    }

    private static class ScriptedBackend implements GameBackendClient
    {
        private final GameState[] responses;
        private final List<GameAction> actions = new ArrayList<GameAction>();
        private Difficulty startedDifficulty;
        private int statesReturned;

        private ScriptedBackend(GameState... responses)
        {
            this.responses = responses;
        }

        @Override
        public GameState startGame(Difficulty difficulty)
        {
            startedDifficulty = difficulty;
            statesReturned++;
            return responses[0];
        }

        @Override
        public GameState submitAction(GameAction action)
        {
            actions.add(action);
            statesReturned++;
            return responses[statesReturned - 1];
        }
    }
}
