public class GameState
{
    public enum Result
    {
        IN_PROGRESS,
        VICTORY,
        DEFEAT
    }

    private final GameCharacterStatus player;
    private final GameCharacterStatus enemy;
    private final Result result;
    private final String message;

    public GameState(GameCharacterStatus player, GameCharacterStatus enemy,
                     Result result, String message)
    {
        this.player = player;
        this.enemy = enemy;
        this.result = result;
        this.message = message;
    }

    public GameCharacterStatus getPlayer()
    {
        return player;
    }

    public GameCharacterStatus getEnemy()
    {
        return enemy;
    }

    public Result getResult()
    {
        return result;
    }

    public String getMessage()
    {
        return message;
    }
}
