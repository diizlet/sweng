public class DemoGameBackendClient implements GameBackendClient
{
    private int turn;

    @Override
    public GameState startGame(Difficulty difficulty)
    {
        turn = 0;
        return createState(GameState.Result.IN_PROGRESS, difficulty + " demo battle started.");
    }

    @Override
    public GameState submitAction(GameAction action)
    {
        turn++;
        if (turn >= 3)
        {
            return createState(GameState.Result.VICTORY, "The demo enemy has been defeated.");
        }
        return createState(GameState.Result.IN_PROGRESS, "Demo response received for " + action + ".");
    }

    private GameState createState(GameState.Result result, String message)
    {
        int playerHp = Math.max(100 - turn * 10, 70);
        int enemyHp = Math.max(80 - turn * 30, 0);
        GameCharacterStatus player = new GameCharacterStatus(
                "Player", playerHp, 100, 30 - turn * 5, 30,
                20, turn == 1, Math.max(2 - turn, 0));
        GameCharacterStatus enemy = new GameCharacterStatus(
                "Slime", enemyHp, 80, 0, 0, 10, false, 0);
        return new GameState(player, enemy, result, message);
    }
}
