/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bottleriddle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Andres
 */
public class Window extends JFrame implements ActionListener {

	Display display;
	JButton nextStep;
	JButton prevStep;
	JLabel displayStep;
	JButton changeConfig;
	
	public Window() {
		setSize(315, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setTitle("Bottle Riddle");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		init();
		
		setVisible(true);
		
		new Thread(display).start();
	}
	
	private void init() {
		
		display = new Display();
		display.setSize(300,180);
		add(display);
		
		nextStep = new JButton(">");
		nextStep.setFocusable(false);
		nextStep.setSize(50,50);
		nextStep.addActionListener(this);
		nextStep.setLocation(getWidth()-60, 220);
		add(nextStep);
		
		prevStep = new JButton("<");
		prevStep.setFocusable(false);
		prevStep.setSize(50,50);
		prevStep.addActionListener(this);
		prevStep.setLocation(5, 220);
		add(prevStep);
		
		changeConfig = new JButton("Change Problem");
		changeConfig.setFocusable(false);
		changeConfig.setSize(150,50);
		changeConfig.addActionListener(this);
		changeConfig.setLocation((getWidth()-150)/2, 220);
		add(changeConfig);
		
		displayStep = new JLabel("");
		displayStep.setSize(20,50);
		displayStep.setLocation(getWidth()/2 - 20/2, 180);
		add(displayStep);
		
		refreshButtons();
	}
	
	public void refreshButtons() {
		prevStep.setEnabled(Handler.currentStep > 0);
		nextStep.setEnabled(Handler.currentStep+1 < Handler.steps.size());
		displayStep.setText((Handler.currentStep+1) + "/" + Handler.steps.size());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextStep) {
			Handler.currentStep++;
		} else if (e.getSource() == prevStep) {
			Handler.currentStep--;
		} else if (e.getSource() == changeConfig) {
			Handler.changer.setVisible(true);
		}
		refreshButtons();
	}
	
}
