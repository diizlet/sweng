public interface GameBackendClient
{
    GameState startGame(Difficulty difficulty);

    GameState submitAction(GameAction action);
}
