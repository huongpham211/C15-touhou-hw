package game.enemy;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject {
    int damage;

    public EnemyBullet() {
        this.collider = new BoxCollider(this, 10, 10);
        this.renderer = new SingleImageRenderer(
                SpriteUtils.loadImage("assets/images/enemies/bullets/red.png")
        );
        this.velocity.set(0, 4);
        this.damage = 1;
    }

    @Override
    public void run() {
        super.run();
        this.deactiveIfNeeded();
        this.checkIntersects();
    }

    private void checkIntersects() {
        Player player = GameObject.findIntersects(
                Player.class,
                this.collider
        );
        if(player != null) {
            player.takeDamage(this.damage);
            this.deactive();
        }
    }

    private void deactiveIfNeeded() {
        if(this.position.y < -30
            || this.position.y > Settings.GAME_HEIGHT + 30
            || this.position.x < -30
            || this.position.x > Settings.BACKGROUND_WIDTH + 30) {
            this.deactive();
        }
    }
}
