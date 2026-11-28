import java.io.PrintStream;
import java.util.Scanner;

public class GamePlayUI
{
    private final Scanner input;
    private final PrintStream output;

    public GamePlayUI(Scanner input, PrintStream output)
    {
        this.input = input;
        this.output = output;
    }

    public Difficulty chooseDifficulty()
    {
        output.println("Select difficulty:");
        Difficulty[] difficulties = Difficulty.values();
        for (int i = 0; i < difficulties.length; i++)
        {
            output.println((i + 1) + ". " + difficulties[i]);
        }

        return difficulties[readChoice(1, difficulties.length) - 1];
    }

    public GameAction chooseAction(GameState state)
    {
        while (true)
        {
            GameCharacterStatus player = state.getPlayer();
            output.println("Choose an action:");
            output.println("1. Attack");
            output.println("2. Skill" + (player.isSkillOnCooldown() ? " (cooldown)" : ""));
            output.println("3. Use potion (" + player.getPotionCount() + " remaining)");

            switch (readChoice(1, 3))
            {
                case 1:
                    return GameAction.ATTACK;
                case 2:
                    if (!player.isSkillOnCooldown())
                    {
                        return GameAction.SKILL;
                    }
                    output.println("Skill is on cooldown.");
                    break;
                case 3:
                    if (player.getPotionCount() > 0)
                    {
                        return GameAction.USE_POTION;
                    }
                    output.println("No potions remaining.");
                    break;
                default:
                    throw new IllegalStateException("Unexpected action selection.");
            }
        }
    }

    public void showGameState(GameState state)
    {
        output.println();
        printCharacter("Player", state.getPlayer());
        printCharacter("Enemy", state.getEnemy());
        if (state.getMessage() != null && !state.getMessage().isEmpty())
        {
            output.println(state.getMessage());
        }
    }

    public void showGameOver(GameState state)
    {
        output.println(state.getResult() == GameState.Result.VICTORY
                ? "Victory!" : "Defeat.");
        output.println("Game over.");
    }

    private void printCharacter(String label, GameCharacterStatus character)
    {
        output.println(label + ": " + character.getName());
        output.println("HP: " + character.getHealthPoint() + "/" + character.getMaxHealthPoint());
        output.println("MP: " + character.getMagicPoint() + "/" + character.getMaxMagicPoint());
        output.println("Skill cooldown: " + (character.isSkillOnCooldown() ? "Yes" : "No"));
        output.println("Potions: " + character.getPotionCount());
    }

    private int readChoice(int minimum, int maximum)
    {
        while (true)
        {
            output.print("> ");
            if (!input.hasNextLine())
            {
                throw new IllegalStateException("Input ended before the game was complete.");
            }

            String line = input.nextLine().trim();
            try
            {
                int choice = Integer.parseInt(line);
                if (choice >= minimum && choice <= maximum)
                {
                    return choice;
                }
            }
            catch (NumberFormatException ignored)
            {
                // Prompt again for a valid menu selection.
            }
            output.println("Enter a number from " + minimum + " to " + maximum + ".");
        }
    }
}
