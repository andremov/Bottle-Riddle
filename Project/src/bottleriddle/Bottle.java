/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bottleriddle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Andres
 */
public class Bottle {
	
	private int maxCapacity;
	private int contents;

	public Bottle(int maxCapacity, int contents) {
		this.maxCapacity = maxCapacity;
		this.contents = contents;
	}

	public Bottle(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.contents = 0;
	}

	/**
	 * @return the maxCapacity
	 */
	public int getMaxCapacity() {
		return maxCapacity;
	}

	/**
	 * @param maxCapacity the maxCapacity to set
	 */
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	/**
	 * @return the contents
	 */
	public int getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(int contents) {
		this.contents = contents;
	}

	/**
	 * @return the free space
	 */
	public int getFreeSpace() {
		return maxCapacity-contents;
	}
	
	/**
	 * @return true if contents = 0
	 */
	public boolean isEmpty() {
		return this.contents == 0;
	}
	
	/**
	 * @return true if contents = max
	 */
	public boolean isFull() {
		return this.contents == this.maxCapacity;
	}
	
	/**
	 * @return the fill percent
	 */
	public float getFillPercent() {
		return ( ((float)contents)/((float)maxCapacity) );
	}
	
	/**
	 * @param percent the percent to fill
	 */
	public void fillPercent(float percent) {
		float floatVolume = percent * maxCapacity;
		int intVolume = (int)Math.ceil(floatVolume);
		if (intVolume == floatVolume) {
			this.setContents(intVolume);
		}
	}
	
	/**
	 * @return the fill percent in string form
	 */
	public String getPercentString() {
		int percent = (int)Math.floor(getFillPercent()*100);
		String percentString = percent+"%";
		if (percent < 100) {
			percentString = " "+percentString;
		}
		if (percent < 10) {
			percentString = " "+percentString;
		}
		return percentString;
	}
	
	/**
	 * @return the max capacity in string form
	 */
	public String getMaxCapacityString() {
		int capacity = this.getMaxCapacity();
		String capacityString = capacity+"";
		if (capacity < 100) {
			capacityString = "0"+capacityString;
		}
		if (capacity < 10) {
			capacityString = "0"+capacityString;
		}
		return capacityString;
	}
	
	/**
	 * @return the contents in string form
	 */
	public String getContentsString() {
		int cont = this.getContents();
		String contString = cont+"";
		if (cont < 100) {
			contString = "0"+contString;
		}
		if (cont < 10) {
			contString = "0"+contString;
		}
		return contString;
	}
	
	/**
	 * @return the max capacity in nice string form
	 */
	public String getMaxCapacityStringNice() {
		int capacity = this.getMaxCapacity();
		String capacityString = capacity+"L";
		if (capacity < 100) {
			capacityString = " "+capacityString;
		}
		if (capacity < 10) {
			capacityString = " "+capacityString;
		}
		return capacityString;
	}
	
	/**
	 * @return the contents in nice string form
	 */
	public String getContentsStringNice() {
		int cont = this.getContents();
		String contString = cont+"L";
		if (cont < 100) {
			contString = " "+contString;
		}
		if (cont < 10) {
			contString = " "+contString;
		}
		return contString;
	}
	
	/**
	 * Fills this bottle, and returns the volume left.
	 * @param inVolume the volume going in
	 * @return the volume going out
	 */
	public int attemptFill(int inVolume) {
		int currentVolume = this.getContents();
		currentVolume = currentVolume + inVolume;
		int outVolume = currentVolume - this.getMaxCapacity();
		if (outVolume < 0) {
			outVolume = 0;
		}
		this.setContents(currentVolume - outVolume);
		return outVolume;
	}

	@Override
	public String toString() {
		return this.getContentsString() + "/" + this.getMaxCapacityString();
	}
	
	public BufferedImage getDisplay() {
		BufferedImage img = new BufferedImage(100, 150, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		
		
		g.setColor(Color.black);
		g.fillRect(5, 5, 90, 90);
		g.setColor(Color.white);
		g.fillRect(7, 5, 86, 88);
		g.setColor(Color.getHSBColor(218.5f/360, 57.8f/100, 92.9f/100));
		int height = (int)Math.ceil(88 * this.getFillPercent());
		g.fillRect(7, 93-height, 86, height);
		
		g.setColor(Color.black);
		g.fillRect(25, 40, 50, 20);
		g.setColor(Color.white);
		g.drawString(this.getPercentString(), 35, 55);
		
		g.setColor(Color.black);
		g.drawString("Contents:" + this.getContentsStringNice(), 15, 110);
		g.drawString("Max:" + this.getMaxCapacityStringNice(), 30, 130);
		
		return img;
	}
	
}
