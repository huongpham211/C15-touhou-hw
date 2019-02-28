package game;

import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject {
    static BufferedImage type1Image = SpriteUtils.loadImage("D:\\CI15-hw\\session_5\\ci-begin-master\\assets\\images\\enemies\\bullets\\pink.png");
    static BufferedImage type2Image = SpriteUtils.loadImage("D:\\CI15-hw\\session_5\\ci-begin-master\\assets\\images\\enemies\\bullets\\red.png");
    static BufferedImage type3Image = SpriteUtils.loadImage("D:\\CI15-hw\\session_5\\ci-begin-master\\assets\\images\\enemies\\bullets\\white.png");

    public EnemyBullet() {
        velocity.set(1,1);
        velocity.setLength(Settings.ENEMY_SPEED_BULLET);
    }

    public void loadImageByType(int type) {
        switch (type) {
            case 1:
                this.image = type1Image;
                break;
            case 2:
                this.image = type2Image;
                break;
            case 3:
                this.image = type3Image;
                break;
            default:
                this.image = type1Image;
        }
    }
}
