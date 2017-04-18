/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bottleriddle;

import java.util.ArrayList;

/**
 *
 * @author Andres
 */
public abstract class Handler {
	
	static final int NUM_BOTTLES = 3;
	static final int MAX_VOLUME = 200;
	
	static Window mainWindow;
	static changeWindow changer;
	static int target;
	static ArrayList<Step> steps;
	static int currentStep;
	
	public static void init() {
		steps = new ArrayList<>();
		currentStep = 0;
		
		Bottle bottle1 = new Bottle(1);
		Bottle bottle2 = new Bottle(5);
		Bottle bottle3 = new Bottle(10);
		bottle3.fillPercent(1);
		steps.add(new Step(bottle1,bottle2,bottle3));
		
		target = 7;
		solve(steps);
		
		mainWindow = new Window();
		changer = new changeWindow();
	}
	
	public static void changeData(Bottle[] bottles, int pTarget) {
		steps = new ArrayList<>();
		currentStep = 0;
		
		steps.add(new Step(bottles));
		
		target = pTarget;
		solve(steps);
		
		mainWindow.refreshButtons();
	}
	
	private static void solve(ArrayList<Step> pastSteps) {
		Bottle[] bottles = pastSteps.get(pastSteps.size()-1).getBottles();
		
		ArrayList<Step> newSteps = new ArrayList<>();

		for (int i = 0; i < NUM_BOTTLES; i++) {
			for (int j = 0; j < NUM_BOTTLES; j++) {
				if (i != j && !bottles[i].isEmpty() && !bottles[j].isFull()) {
					
					Step newStep = new Step(copyBottles(bottles));
					int remainder = newStep.getBottles()[j].attemptFill(newStep.getBottles()[i].getContents());
					int waterMoved = newStep.getBottles()[i].getContents() - remainder;
					newStep.getBottles()[i].setContents(remainder);
					
					newStep.setStartBottle(i);
					newStep.setEndBottle(j);
					newStep.setWaterMoved(waterMoved);
					
					if (canStep(pastSteps, newStep)) {
						newSteps.add(newStep);
					}

				}
			}
		}

		for (int i = 0; i < newSteps.size(); i++) {
			ArrayList<Step> newList = copySteps(pastSteps);
			newList.add(newSteps.get(i));
			solve(newList);
		}
		
		
		int currentDifference = steps.get(steps.size()-1).getDifference(target);
		int thisDifference = pastSteps.get(pastSteps.size()-1).getDifference(target);
		
		if (thisDifference == currentDifference) {
			if (pastSteps.size() < steps.size()) {
				steps = pastSteps;
			}
		} else if (thisDifference < currentDifference) {
			steps = pastSteps;
		}
	}
	
	private static Bottle[] copyBottles(Bottle[] source) {
		Bottle[] newBottles = new Bottle[NUM_BOTTLES];
		
		for (int i = 0; i < NUM_BOTTLES; i++) {
			newBottles[i] = new Bottle(source[i].getMaxCapacity(), source[i].getContents());
		}
		
		return newBottles;
	}
	
	private static ArrayList<Step> copySteps(ArrayList<Step> source) {
		ArrayList<Step> newList = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			Step thisStep = new Step(copyBottles(source.get(i).getBottles()));
			thisStep.setEndBottle(source.get(i).getEndBottle());
			thisStep.setStartBottle(source.get(i).getStartBottle());
			thisStep.setWaterMoved(source.get(i).getWaterMoved());
			newList.add(thisStep);
		}
		return newList;
	}
	
	private static boolean canStep(ArrayList<Step> steps, Step newStep) {
		boolean result = true;
		for (int i = 0; i < steps.size(); i++) {
			if (steps.get(i).equals(newStep)) {
				result = false;
			}
		}
		return result;
	}
	
	private static boolean done(Bottle[] bottles) {
		boolean done = false;
		int id = 0;
		while (id < NUM_BOTTLES && !done) {
			done = bottles[id].getContents() == target;
			id++;
		}
		return done;
	}
	
	public static String getTargetString() {
		String targetString = target+"L";
		if (target < 100) {
			targetString = " "+targetString;
		}
		if (target < 10) {
			targetString = " "+targetString;
		}
		return targetString;
	}
	
	public static String getWaterUsedString() {
		int waterUsed = 0;
		for (int i = 0; i < currentStep+1; i++) {
			waterUsed = waterUsed + steps.get(i).getWaterMoved();
		}
		String waterUsedString = waterUsed+"L";
		if (waterUsed < 100) {
			waterUsedString = " "+waterUsedString;
		}
		if (waterUsed < 10) {
			waterUsedString = " "+waterUsedString;
		}
		return waterUsedString;
	}
	
}
