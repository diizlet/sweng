import java.util.Scanner;

public class GamePlay
{
    private final GameBackendClient backend;
    private final GamePlayUI ui;

    public GamePlay(GameBackendClient backend, GamePlayUI ui)
    {
        this.backend = backend;
        this.ui = ui;
    }

    public void run()
    {
        Difficulty difficulty = ui.chooseDifficulty();
        GameState state = backend.startGame(difficulty);
        ui.showGameState(state);

        while (state.getResult() == GameState.Result.IN_PROGRESS)
        {
            GameAction action = ui.chooseAction(state);
            state = backend.submitAction(action);
            ui.showGameState(state);
        }

        ui.showGameOver(state);
    }

    public static void main(String[] args)
    {
        GameBackendClient backend = new DemoGameBackendClient();
        GamePlayUI ui = new GamePlayUI(new Scanner(System.in), System.out);
        new GamePlay(backend, ui).run();
    }
}
