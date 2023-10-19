package screen;

import java.awt.event.KeyEvent;
import engine.Cooldown;
import engine.Core;
import engine.GameState;
import engine.SoundEffect;
import entity.Coin;

public class StoreScreen extends Screen {
    /** Milliseconds between changes in user selection. */
    private static final int SELECTION_TIME = 200;

    /** Time between changes in user selection. */
    private Cooldown selectionCooldown;
    /** For selection moving sound */
    private SoundEffect soundEffect;

    private Coin coin;

    /**
     * Constructor, establishes the properties of the screen.
     *
     * @param width
     *               Screen width.
     * @param height
     *               Screen height.
     * @param fps
     *               Frames per second, frame rate at which the game is run.
     */
    public StoreScreen(final int width, final int height, final int fps, final GameState gameState) {
        super(width, height, fps);
        // Defaults to play.
        this.returnCode = 13;
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
        this.coin = gameState.getCoin();

        soundEffect = new SoundEffect();
    }

    /**
     * Starts the action.
     *
     * @return Next screen code.
     */
    public final int run() {
        super.run();

        return this.returnCode;
    }

    /**
     * Updates the elements on screen and checks for events.
     */
    protected final void update() {
        super.update();

        draw();
        if (this.selectionCooldown.checkFinished()
                && this.inputDelay.checkFinished()) {
            if (inputManager.isKeyDown(KeyEvent.VK_UP)
                    || inputManager.isKeyDown(KeyEvent.VK_W)) {
                soundEffect.playButtonClickSound();
                previousMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
                    || inputManager.isKeyDown(KeyEvent.VK_S)) {
                soundEffect.playButtonClickSound();
                nextMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_SPACE)) {
                soundEffect.playSpaceButtonSound();
                this.isRunning = false;
            }
        }
    }
    private void nextMenuItem() {
        if (this.returnCode == 2)
            this.returnCode = 14;
        else 
            this.returnCode = 15;
    }

    /**
     * Shifts the focus to the previous menu item.
     */
    private void previousMenuItem() {
        if (this.returnCode == 15)
            this.returnCode = 14;
        else 
            this.returnCode = 2;
    }
    /**
     * Draws the elements associated with the screen.123
     */
    private void draw() {
        drawManager.initDrawing(this);
        drawManager.drawCoin(this, this.coin, 2);
        drawManager.drawItemStore(this, this.returnCode);
        drawManager.completeDrawing(this);
    }
}
