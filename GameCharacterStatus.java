public class GameCharacterStatus extends GameCharacter
{
    private final int maxHealthPoint;
    private final int magicPoint;
    private final int maxMagicPoint;
    private final boolean skillOnCooldown;
    private final int potionCount;

    public GameCharacterStatus(String name, int healthPoint, int maxHealthPoint,
                               int magicPoint, int maxMagicPoint, int strength,
                               boolean skillOnCooldown, int potionCount)
    {
        super(name, healthPoint, strength);
        this.maxHealthPoint = maxHealthPoint;
        this.magicPoint = magicPoint;
        this.maxMagicPoint = maxMagicPoint;
        this.skillOnCooldown = skillOnCooldown;
        this.potionCount = potionCount;
    }

    public int getMaxHealthPoint()
    {
        return maxHealthPoint;
    }

    public int getMagicPoint()
    {
        return magicPoint;
    }

    public int getMaxMagicPoint()
    {
        return maxMagicPoint;
    }

    public boolean isSkillOnCooldown()
    {
        return skillOnCooldown;
    }

    public int getPotionCount()
    {
        return potionCount;
    }
}
