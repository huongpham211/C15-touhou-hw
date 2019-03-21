package game.player;

import game.GameObject;
import game.renderer.AnimationRenderer;
import game.renderer.Renderer;

import java.awt.*;

public class PlayerRenderer extends Renderer {
    AnimationRenderer leftAnimation;
    AnimationRenderer rightAnimation;
    AnimationRenderer straightAnimation;

    public PlayerRenderer() {
        leftAnimation = new AnimationRenderer(
                "assets/images/players/left",
                10
        );
        rightAnimation = new AnimationRenderer(
                "assets/images/players/right",
                10
        );
        straightAnimation = new AnimationRenderer(
                "assets/images/players/straight",
                10
        );
    }

    int renderCount;
    @Override
    public void render(Graphics g, GameObject master) {
        Player player = (Player) master;
        renderCount++;
        if(player.immune && renderCount % 2 == 0) {
            return;
        }
        if(master.velocity.x < 0) {
            leftAnimation.render(g, master);
        } else if(master.velocity.x > 0) {
            rightAnimation.render(g, master);
        } else {
            straightAnimation.render(g, master);
        }
    }
}
