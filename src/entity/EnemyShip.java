package entity;

import java.awt.Color;
import java.util.Random;
import java.util.Map;
import java.util.Set;

import engine.Cooldown;
import engine.Core;
import engine.DrawManager.SpriteType;


/**
 * Implements a enemy ship, to be destroyed by the player.
 * 
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 * 
 */
public class EnemyShip extends Entity {
	/** 적들 처치시 점수 설정 */
	/** Point value of a type A enemy. */
	private static final int A_TYPE_POINTS = 10;
	/** Point value of a type B enemy. */
	private static final int B_TYPE_POINTS = 20;
	/** Point value of a type C enemy. */
	private static final int C_TYPE_POINTS = 30;
	/** Point value of a type C enemy. */
	private static final int D_TYPE_POINTS = 40;
	/** Point value of a bonus enemy. */
	private static final int BONUS_TYPE_POINTS = 100;
	/** Point value of a boss enemy. */
	private static final int BOSS_TYPE_POINTS = 1000;
	/** Item drop percent*/
	private final double DROP_ITEM_PROB = 0.05;

	/** 스프라이트 변경 쿨다운. */
	private Cooldown animationCooldown;
	/** Checks if the ship has been hit by a bullet. */
	private boolean isDestroyed;
	/** Values of the ship, in points, when destroyed. */
	private int pointValue;
	/** Lives of ship, ship will be destroyed when life becomes 0. */
	private int EnemyLife;
	/** check which special enemy to generate. */
	private int spVariable;



	/** Check the enemyship is boss */
	private boolean isBoss;




	/**
	 * Constructor, establishes the ship's properties.
	 * 
	 * @param positionX
	 *            Initial position of the ship in the X axis.
	 * @param positionY
	 *            Initial position of the ship in the Y axis.
	 * @param spriteType
	 *            Sprite type, image corresponding to the ship.
	 * @param enemyColor
	 * 			  Color of enemyship.
	 */
	public EnemyShip(final int positionX, final int positionY,
			final SpriteType spriteType, Color enemyColor) {
		super(positionX, positionY, 12 * 2, 8 * 2, enemyColor);

		this.spriteType = spriteType;
		this.animationCooldown = Core.getCooldown(500);
		this.isDestroyed = false;
		this.isBoss = false;

		switch (this.spriteType) {
		case EnemyShipA1:
		case EnemyShipA2:
			this.pointValue = A_TYPE_POINTS;
			this.EnemyLife = 1;
			break;
		case EnemyShipB1:
		case EnemyShipB2:
			this.pointValue = B_TYPE_POINTS;
			this.EnemyLife = 1;
			break;
		case EnemyShipC1:
		case EnemyShipC2:
			this.pointValue = C_TYPE_POINTS;
			this.EnemyLife = 1;
			break;
		case EnemyShipSC1:
		case EnemyShipSC2:
			this.pointValue = C_TYPE_POINTS;
			this.EnemyLife = 2;
			break;
		case EnemyShipD1:
		case EnemyShipD2:
		case EnemyShipD3:
		case EnemyShipD4:
		case EnemyShipD5:
		case EnemyShipD6:
			this.pointValue = D_TYPE_POINTS;
			this.EnemyLife = 3;
			break;
		default:
			this.pointValue = 0;
			break;
		}
	}

	/**
	 * Constructor, establishes the ship's properties for a special ship, with
	 * known starting properties.
	 *
	 * @param specialEnemyColor
	 * 			   Color of the special ship.
	 */
	public EnemyShip(Color specialEnemyColor) {
		super(-32, 60, 16 * 2, 7 * 2, specialEnemyColor);
		spVariable = (int)(Math.random()*2);

		switch (spVariable) {
			case 0:
				this.spriteType = SpriteType.EnemyShipSpecial1;
				break;
			case 1:
				this.spriteType = SpriteType.EnemyShipSpecial2;
				break;
		}


		this.isDestroyed = false;
		this.pointValue = BONUS_TYPE_POINTS;
		this.EnemyLife = 1;
		this.isBoss = false;
	}

	/**
	 * Constructor, establishes the ship's properties for a boss ship.
	 *
	 * @param enemylife
	 *            Lives of the boss ship.
	 * @param bossColor
	 * 			  Color of the boss ship.
	 */
	public EnemyShip(final int positionX, final int positionY, final int enemylife, Color bossColor) {
		super(positionX, positionY, 22 * 2, 13 * 2, Color.RED);
		this.spriteType = SpriteType.BossA1;
		this.animationCooldown = Core.getCooldown(500);
		this.isDestroyed = false;
		this.pointValue = BOSS_TYPE_POINTS;
		this.EnemyLife = enemylife;
		this.isBoss = true;

	}

	/**
	 * Getter for the score bonus if this ship is destroyed.
	 * 
	 * @return Value of the ship.
	 */
	public final int getPointValue() {
		return this.pointValue;
	}

	/**
	 * Moves the ship the specified distance.
	 * 
	 * @param distanceX
	 *            Distance to move in the X axis.
	 * @param distanceY
	 *            Distance to move in the Y axis.
	 */
	public final void move(final int distanceX, final int distanceY) {
		this.positionX += distanceX;
		this.positionY += distanceY;
	}

	/**
	 * Updates attributes, mainly used for animation purposes.
	 */
	public final void update() {
		if (this.animationCooldown.checkFinished()) {
			this.animationCooldown.reset();

			switch (this.spriteType) {

			case EnemyShipA1:
				this.spriteType = SpriteType.EnemyShipA2;
				break;

			case EnemyShipA2:
				this.spriteType = SpriteType.EnemyShipA1;
				break;

			case EnemyShipB1:
				this.spriteType = SpriteType.EnemyShipB2;
				break;

			case EnemyShipB2:
				this.spriteType = SpriteType.EnemyShipB1;
				break;

			/** 2 forms of enemyC - change form whenever life is reduced */
			case EnemyShipSC1:
			case EnemyShipC1:
				if(this.getEnemyLife() < 2) this.spriteType = SpriteType.EnemyShipC2;
				else this.spriteType = SpriteType.EnemyShipSC2;
				break;

			case EnemyShipSC2:
			case EnemyShipC2:
				if(this.getEnemyLife() < 2) this.spriteType = SpriteType.EnemyShipC1;
				else this.spriteType = SpriteType.EnemyShipSC1;
				break;

			/** 3 forms of enemyD - change form whenever life is reduced */
			case EnemyShipD1:
			case EnemyShipD3:
			case EnemyShipD5:
				if(this.getEnemyLife() < 2) this.spriteType = SpriteType.EnemyShipD6;
				else if(this.getEnemyLife() < 3) this.spriteType = SpriteType.EnemyShipD4;
				else this.spriteType = SpriteType.EnemyShipD2;
				break;

			case EnemyShipD2:
			case EnemyShipD4:
			case EnemyShipD6:
				if(this.getEnemyLife() < 2) this.spriteType = SpriteType.EnemyShipD5;
				else if(this.getEnemyLife() < 3) this.spriteType = SpriteType.EnemyShipD3;
				else this.spriteType = SpriteType.EnemyShipD1;
				break;
			case BossA1:
				this.spriteType = SpriteType.BossA2;
				break;
			case BossA2:
				this.spriteType = SpriteType.BossA1;
				break;


			default:
				break;
			}
		}
	}

	/**
	 * Reduces enemy's life when hit
	 */
	public final void reduceEnemyLife(final int attackDamage) {
		this.EnemyLife -= attackDamage;
	}
	
	/**
	 * Getter for the life of enemyship.
	 *
	 * @return the rest of the enemy's life.
	 */
	public final int getEnemyLife() {
		return this.EnemyLife;
	}

	/**
	 * Check the enemyship is boss.
	 *
	 * @return True if the enemyship is boss
	 */
	public final boolean checkIsBoss() {
		return this.isBoss;
	}

	/**
	 * Destroys the ship, causing an explosion.
	 */
	public final void destroy(Set<Item> items) {
		this.isDestroyed = true;
		this.spriteType = randomDestroy();
		if ((Math.random() < DROP_ITEM_PROB + (0.1 * 2 * (this.getSpriteType() == SpriteType.EnemyShipSpecial1 ? 1 : 0)))
				|| (Math.random() < DROP_ITEM_PROB + (0.1 * 2 * (this.getSpriteType() == SpriteType.EnemyShipSpecial2 ? 1 : 0)))) {
			items.add(ItemPool.getItem(this.positionX, this.positionY));
		}
	}

	/**
	 * Checks if the ship has been destroyed.
	 * 
	 * @return True if the ship has been destroyed.
	 */
	public final boolean isDestroyed() {
		return this.isDestroyed;
	}

	/**
	 * Get a random number and select death effect
	 * 
	 * [Clean Code Team] This method was created by NiceGuy1313
	 * 
	 * @return Random Death Effect
	 */
	public final SpriteType randomDestroy(){
		Random random = new Random();
		 SpriteType[] destroys = {SpriteType.Explosion, SpriteType.Explosion2, SpriteType.Explosion3, SpriteType.Explosion4};
		return destroys[random.nextInt(4)];
	}
}