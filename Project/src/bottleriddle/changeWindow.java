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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Andres
 */
public class changeWindow extends JFrame implements ActionListener {
	
	JButton sendBtn;
	JLabel targetLabel;
	JLabel[] bottleLabel;
	JLabel[] maxCapacityLabel;
	JLabel[] contentLabel;
	
	JTextField targetField;
	JTextField[] maxCapacityField;
	JTextField[] contentField;
	
	public changeWindow() {
		setVisible(false);
		setSize(315, 470);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setTitle("Change Problem");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		init();
	}
	
	private void init() {
		
		targetLabel = new JLabel("Target");
		targetLabel.setSize(80,40);
		targetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		targetLabel.setLocation((getWidth()-80)/2,5);
		add(targetLabel);
		
		targetField = new JTextField(7+"");
		targetField.setSize(80,30);
		targetField.setLocation((getWidth()-80)/2,40);
		add(targetField);
		
		bottleLabel = new JLabel[Handler.NUM_BOTTLES];
		maxCapacityLabel = new JLabel[Handler.NUM_BOTTLES];
		contentLabel = new JLabel[Handler.NUM_BOTTLES];
		maxCapacityField = new JTextField[Handler.NUM_BOTTLES];
		contentField = new JTextField[Handler.NUM_BOTTLES];
		
		int bottleTitleStart = 90;
		int fieldTitleStart = 105;
		int fieldStart = 135;
		int bottleHeight = 90;
		
		int[] content = { 0, 0, 10};
		int[] max = { 1, 5, 10};
		for (int i = 0; i < Handler.NUM_BOTTLES; i++) {
		
			bottleLabel[i] = new JLabel("Bottle "+(i+1));
			bottleLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			bottleLabel[i].setSize(80,40);
			bottleLabel[i].setLocation((getWidth()-80)/2, bottleTitleStart + (bottleHeight*i));
			add(bottleLabel[i]);
		
			maxCapacityLabel[i] = new JLabel("Max");
			maxCapacityLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			maxCapacityLabel[i].setSize(80,40);
			maxCapacityLabel[i].setLocation((getWidth()-80)*3/4, fieldTitleStart + (bottleHeight*i));
			add(maxCapacityLabel[i]);
		
			contentLabel[i] = new JLabel("Content");
			contentLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			contentLabel[i].setSize(80,40);
			contentLabel[i].setLocation((getWidth()-80)/4, fieldTitleStart + (bottleHeight*i));
			add(contentLabel[i]);
		
			maxCapacityField[i] = new JTextField(max[i]+"");
			maxCapacityField[i].setSize(80,30);
			maxCapacityField[i].setLocation((getWidth()-80)*3/4, fieldStart + (bottleHeight*i));
			add(maxCapacityField[i]);

			contentField[i] = new JTextField(content[i]+"");
			contentField[i].setSize(80,30);
			contentField[i].setLocation((getWidth()-80)/4, fieldStart + (bottleHeight*i));
			add(contentField[i]);
		}
		
		sendBtn = new JButton("Change Problem");
		sendBtn.setSize(150,60);
		sendBtn.addActionListener(this);
		sendBtn.setLocation((getWidth()-150)/2,getHeight()-110);
		add(sendBtn);
	}
	
	private Bottle[] buildBottles() {
		Bottle[] bottles = new Bottle[Handler.NUM_BOTTLES];
		for (int i = 0; i < Handler.NUM_BOTTLES; i++) {
			bottles[i] = new Bottle(Integer.parseInt(maxCapacityField[i].getText()),Integer.parseInt(contentField[i].getText()));
		}
		return bottles;
	}
	
	private void validateEntries() throws Exception {
		int target = -1;
		try {
			target = Integer.parseInt(targetField.getText());
		} catch (Exception e) {
			throw new Exception("El objetivo no es un numero valido.");
		}
		if (target > Handler.MAX_VOLUME) {
			throw new Exception("El objetivo es mayor al volumen maximo.");
		} else if (target < 0) {
			throw new Exception("El objetivo es negativo.");
		}
		for (int i = 0; i < Handler.NUM_BOTTLES; i++) {
			int capacidad = -1;
			try {
				capacidad = Integer.parseInt(maxCapacityField[i].getText());
			} catch (Exception e) {
				throw new Exception("Una capacidad no es un numero valido.");
			}
			if (capacidad > Handler.MAX_VOLUME) {
				throw new Exception("Una capacidad es mayor al volumen maximo.");
			} else if (capacidad < 0) {
				throw new Exception("Una capacidad es negativa.");
			}
			
			int contenido = -1;
			try {
				contenido = Integer.parseInt(contentField[i].getText());
			} catch (Exception e) {
				throw new Exception("Un contenido no es un numero valido.");
			}
			if (contenido > Handler.MAX_VOLUME) {
				throw new Exception("Un contenido es mayor al volumen maximo.");
			} else if (contenido < 0) {
				throw new Exception("Un contenido es negativo.");
			}
			
			if (contenido > capacidad){
				throw new Exception("Un contenido es mayor a su capacidad.");
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendBtn) {
			try {
				validateEntries();
				Handler.changeData(buildBottles(),Integer.parseInt(targetField.getText()));
				this.setVisible(false);
			} catch (Exception ex) {
				javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
