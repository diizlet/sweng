public enum GameAction
{
    ATTACK("Attack"),
    SKILL("Skill"),
    USE_POTION("Use potion");

    private final String displayName;

    GameAction(String displayName)
    {
        this.displayName = displayName;
    }

    @Override
    public String toString()
    {
        return displayName;
    }
}
