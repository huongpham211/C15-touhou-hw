package game.enemy;

import game.GameObject;

public class EnemySummoner extends GameObject {
    int wayCount;
    int summonCount;
    int enemyCount;

    public EnemySummoner() {
        wayCount = 0;
        summonCount = 0;
        enemyCount = 0;
    }

    @Override
    public void run() {
        super.run();
        summonEnemy();
    }

    private void summonEnemy() {
        wayCount++;
        if(wayCount > 120) {
            summonCount++;
            if(summonCount > 10) {
                Enemy enemy = GameObject.recycle(Enemy.class);
                summonCount = 0;
                enemyCount++;
                if(enemyCount >= 5) {
                    wayCount = 0;
                    enemyCount = 0;
                }
            }
        }
    }
}
