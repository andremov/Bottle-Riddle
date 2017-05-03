/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bottleriddle;

import java.awt.Canvas;
import java.awt.Graphics;

/**
 *
 * @author Andres
 */
public class Display extends Canvas implements Runnable {
	
	
	
	@Override
	public void paint(Graphics g) {
		
	}

	@Override
	public void run() {
		createBufferStrategy(2);
		while(true) {
			
			Graphics g = getBufferStrategy().getDrawGraphics();
			g.clearRect(0, 0, 300, 180);
			Bottle[] currentBottles = Handler.steps.get(Handler.currentStep).getBottles();
			for (int i = 0; i < currentBottles.length; i++) {
				int x = 5 + (i* 100);
				int y = 5;
				g.drawImage(currentBottles[i].getDisplay(), x, y, null);
			}
			g.drawString("Target: "+Handler.getTargetString(), 125, 160);
			g.drawString("Water Used: "+Handler.getWaterUsedString(), 110, 175);
			
			getBufferStrategy().show();
			
			try {
				Thread.sleep(100);
			} catch (Exception ex) {
				
			}
		}
	}
	
}
