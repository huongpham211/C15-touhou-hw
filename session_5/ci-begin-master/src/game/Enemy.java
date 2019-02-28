package game;

import tklibs.SpriteUtils;

import java.util.Random;

public class Enemy extends GameObject {
    int fireCount;
    int bulletType;
    int changeBulletCount;
    Random random;

    public Enemy() {
        image = SpriteUtils.loadImage("D:\\CI15-hw\\session_5\\ci-begin-master\\assets\\images\\enemies\\level0\\pink\\0.png");
        position.set(-50, -50);
        velocity.set(2, 2);
        velocity.setAngle(Math.PI / 18);
        velocity.setLength(Settings.ENEMY_SPEED);

        fireCount = 0;
        bulletType = 1;
        changeBulletCount = 0;
        random = new Random();
    }

    @Override
    public void run() {
        super.run();
        changeVelocity();
        enemyFire();
        changeBulletType();
    }

    private void changeBulletType() {
        changeBulletCount++;
        if(changeBulletCount > 300) {
            bulletType = 1 + random.nextInt(3);
            changeBulletCount = 0;
        }
    }

    private void enemyFire() {
        fireCount++;
        if(fireCount > 20) {
            for (int i = 0; i < 1; i++) {
                EnemyBullet bullet = new EnemyBullet();
                bullet.loadImageByType(bulletType);
                bullet.position.set(this.position.x, this.position.y);
                bullet.velocity.setAngle(Math.PI * 0.5);
            }

            fireCount = 0;
        }
    }

    private void changeVelocity() {
        if(position.x > Settings.BACKGROUND_WIDTH - Settings.ENEMY_WIDTH
                && velocity.x > 0) {
            velocity.set(-velocity.x, velocity.y);
        }
        if(position.x < 0 && velocity.x < 0) {
            velocity.set(-velocity.x, velocity.y);
        }
        if(position.y > Settings.GAME_HEIGHT - Settings.ENEMY_HEIGHT
                && velocity.y > 0) {
            velocity.set(velocity.x, -velocity.y);
        }
        if(position.y < 0 && velocity.y < 0) {
            velocity.set(velocity.x, -velocity.y);
        }
    }


}
