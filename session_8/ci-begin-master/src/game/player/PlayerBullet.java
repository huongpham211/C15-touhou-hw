package game.player;

import game.GameObject;
import game.Settings;
import game.enemy.Enemy;
import game.physics.BoxCollider;
import game.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerBullet extends GameObject {
    public int damage;

    public PlayerBullet() {
        renderer = new AnimationRenderer(
                "assets/images/player-bullets/a",
                5
        );
        velocity.set(1, 1);
        velocity.setLength(Settings.PLAYER_BULLET_SPEED);
        collider = new BoxCollider(this, 10, 10);
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
        deactiveIfNeeded();
        checkIntersects();
    }

    private void checkIntersects() {
        Enemy enemy = GameObject.findIntersects(Enemy.class, collider);
        if(enemy != null) {
            deactive();
            enemy.takeDamage(damage);
        }
    }

    private void deactiveIfNeeded() {
        if(this.position.y < -30) {
            this.deactive();
        }
    }
}
