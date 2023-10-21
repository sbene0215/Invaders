package entity;

import java.awt.Color;
import java.util.Set;

import effect.BulletEffect;
import engine.DrawManager.SpriteType;

/**
 * Implements a bullet that moves vertically up or down.
 *
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 *
 */
public class BulletY extends Entity {

    /**
     * Speed of the bullet, positive or negative depending on direction -
     * positive is down.
     */
    private int speed;
    /** Damage of the bullet of ship. */	
    private int damage;
    private BulletEffect bulletEffect;
    private int effectBullet;

    /**
     * Constructor, establishes the bullet's properties.
     *
     * @param positionX
     *            Initial position of the bullet in the X axis.
     * @param positionY
     *            Initial position of the bullet in the Y axis.
     * @param speed
     *            Speed of the bullet, positive or negative depending on
     *            direction - positive is down.
     */
    public BulletY(final int positionX, final int positionY, final int speed, final int attackDamage) {
        super(positionX, positionY, 5*2 , 7*2 , Color.YELLOW);
        this.speed = speed;
        this.damage = 3 + attackDamage;
        setSprite();
    }

    /**
     * Sets correct sprite for the bullet, based on speed.
     */
    public final void setSprite() {
        if (speed < 0)
            this.spriteType = SpriteType.BulletY;
        else
            this.spriteType = SpriteType.EnemyBullet;
    }

    /**
     * Updates the bullet's position.
     */
    public final void update() {
        this.positionY += this.speed;
    }

    /**
     * Setter of the speed of the bullet.
     *
     * @param speed
     *            New speed of the bullet.
     */
    public final void setSpeed(final int speed) {
        this.speed = speed;
    }

	/**
	 * Sets damage of attack.
	 */
	public final void setDamage(final int attackDamage) {
		this.damage = 3 + attackDamage;
	}


    /**
     * Getter for the speed of the bullet.
     *
     * @return Speed of the bullet.
     */

    public final void splash(Set<Bullet> bullets) {
        bulletEffect.splashEffect(bullets);
    }
    public final int getSpeed() {
        return this.speed;
    }

    public final int getDamage() { return this.damage; }
    public final int isEffectBullet() {return this.effectBullet; }
    public void setEffectBullet(int n) {this.effectBullet = n;}
}