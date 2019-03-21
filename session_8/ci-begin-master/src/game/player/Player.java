package game.player;

import game.GameObject;
import game.GameWindow;
import game.Settings;
import game.physics.BoxCollider;
import game.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Player extends GameObject {
    int fireCount;
    int bulletType;
    int changeBulletCount;
    Random random;
    int hp;
    boolean immune;

    public Player() {
//        renderer = new AnimationRenderer(
//                "assets/images/players/straight",
//                10
//        );
        collider = new BoxCollider(this, 15, 15);
        renderer = new PlayerRenderer();
        position.set(200, 500);
        fireCount = 0;
        bulletType = 1;
        changeBulletCount = 0;
        random = new Random();
        hp = 5;
        immune = false;
    }

    @Override
    public void render(Graphics g) {
        renderer.render(g, this);
    }

    @Override
    public void run() {
        super.run();
        playerMove();
        playerLimit();
        playerFire();
        changeBulletType();
        checkImmune();
    }

    int immuneCount;
    private void checkImmune() {
        if(immune) {
            immuneCount++;
            if(immuneCount > 60) {
                immune = false;
                immuneCount = 0;
            }
        }
    }

    private void playerMove() {
        int vX = 0;
        int vY = 0;

        if(GameWindow.isUpPress) { // player move
            vY--;
        }
        if(GameWindow.isRightPress) {
            vX++;
        }
        if(GameWindow.isDownPress) {
            vY++;
        }
        if(GameWindow.isLeftPress) {
            vX--;
        }

        this.velocity.set(vX, vY);
        this.velocity.setLength(1);
    }

    private void playerLimit() {
        double offsetWidth = anchor.x * Settings.PLAYER_WIDTH;
        double offsetHeight = anchor.y * Settings.PLAYER_HEIGHT;
        if(position.x < offsetWidth) { // limit player
            position.x = offsetWidth;
        }
        if(position.x > Settings.BACKGROUND_WIDTH - offsetWidth) {
            position.x = Settings.BACKGROUND_WIDTH - offsetWidth;
        }
        if(position.y < offsetHeight) {
            position.y = offsetHeight;
        }
        if(position.y > Settings.GAME_HEIGHT - offsetHeight) {
            position.y = Settings.GAME_HEIGHT - offsetHeight;
        }
    }

    private void playerFire() {
        fireCount++;
        if(GameWindow.isFirePress && fireCount > 20) {
            for (int i = 0; i < 1; i++) {
//                PlayerBullet bullet = new PlayerBullet();
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
                bullet.position.set(this.position.x, this.position.y);
                bullet.velocity.setAngle(-Math.PI * 0.5);
            }

            fireCount = 0;
        }
    }

    private void changeBulletType() {
        changeBulletCount++;
        if(changeBulletCount > 300) {
            bulletType = 1 + random.nextInt(3);
            changeBulletCount = 0;
        }
    }

    public void takeDamage(int damage) {
        if(immune) {
            return;
        }
        hp -= damage;
        if(hp <= 0) {
            hp = 0;
            deactive();
        } else {
            immune = true;
        }
    }
}
