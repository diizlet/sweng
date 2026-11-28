public class Mage extends GameCharacter
{
    /* data */
    private int magicPoint;

    public Mage(String n)
    {
        super(n);
        /*
        setName(n);
        setHealthPoint(1234); // healthPoint = 1234;
        setStrength(55);    // strength = 55;
        */
        magicPoint = 33;
    }

    /* methods */
    public void castSpell(GameCharacter enemy)
    {
        System.out.println(getName() + " casts a fireaball!");
        enemy.setHealthPoint(enemy.getHealthPoint() - magicPoint);
    }

    public void printStatus()
    {
        super.printStatus();
        System.out.println("Magic Point: " + magicPoint);
    }

    public static void main(String[] args)
    {
        Mage mage = new Mage("Wizard the Powerful");
        GameCharacter enemy = new GameCharacter("Slime");

        mage.printStatus();
        enemy.printStatus();
        System.out.println();

        mage.attack(enemy);
        mage.castSpell(enemy);

        mage.printStatus();
        enemy.printStatus();
        System.out.println();
    }
}
