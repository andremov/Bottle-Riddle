/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bottleriddle;

import static bottleriddle.Handler.NUM_BOTTLES;

/**
 *
 * @author Andres
 */
public class Step {
	
	private Bottle[] bottles;
	private int startBottle;
	private int endBottle;
	private int waterMoved;
	
	public Step(Bottle[] bottles) {
		this.bottles = bottles;
	}
	
	public Step(Bottle bottle1, Bottle bottle2, Bottle bottle3) {
		this.bottles = new Bottle[NUM_BOTTLES];
		this.bottles[0] = bottle1;
		this.bottles[1] = bottle2;
		this.bottles[2] = bottle3;
	}

	@Override
	public String toString() {
		return "Step: [ " + this.bottles[0].toString()+ " | " + this.bottles[1].toString() + " | " + this.bottles[2].toString() + " ]";
	}

	public boolean equals(Step otherStep) {
		boolean result = false;
		if (otherStep != null) {
			result = true;
			for (int i = 0; i < NUM_BOTTLES; i++) {
				if (otherStep.getBottles()[i].getContents() != this.getBottles()[i].getContents()){
					result = false;
				}
			}
		}
		return result;
	}

	/**
	 * @return the startBottle
	 */
	public int getStartBottle() {
		return startBottle;
	}

	/**
	 * @param startBottle the startBottle to set
	 */
	public void setStartBottle(int startBottle) {
		this.startBottle = startBottle;
	}

	/**
	 * @return the endBottle
	 */
	public int getEndBottle() {
		return endBottle;
	}

	/**
	 * @param endBottle the endBottle to set
	 */
	public void setEndBottle(int endBottle) {
		this.endBottle = endBottle;
	}

	/**
	 * @return the bottles
	 */
	public Bottle[] getBottles() {
		return bottles;
	}
	
	/**
	 * @param target the target aiming to reach
	 * @return difference to target
	 */
	public int getDifference(int target) {
		int leastDifference = 200;
		for (int i = 0; i < NUM_BOTTLES; i++) {
			if (this.getBottles()[i].getContents() <= target) {
				if (target - this.getBottles()[i].getContents() < leastDifference) {
					leastDifference = target - this.getBottles()[i].getContents();
				}
			}
		}
		return leastDifference;
	}

	/**
	 * @return the waterMoved
	 */
	public int getWaterMoved() {
		return waterMoved;
	}

	/**
	 * @param waterMoved the waterMoved to set
	 */
	public void setWaterMoved(int waterMoved) {
		this.waterMoved = waterMoved;
	}
}
