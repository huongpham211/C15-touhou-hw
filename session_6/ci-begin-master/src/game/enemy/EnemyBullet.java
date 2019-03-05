package game.enemy;

import game.GameObject;
import game.Settings;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject {
    public EnemyBullet() {
        this.image = SpriteUtils.loadImage("assets/images/enemies/bullets/red.png");
        this.velocity.set(0, 4);
    }

    @Override
    public void run() {
        super.run();
        deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if(this.position.y < -30
                || this.position.y > Settings.GAME_HEIGHT + 2
                || this.position.x < -30
                || this.position.x > Settings.GAME_WIDTH + 2) {
            this.deactive();
        }
    }


}

