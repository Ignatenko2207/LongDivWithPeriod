package com.smarterama;

public class DivisionWithPeriod {

	public static void main(String[] args) {
		
		int dividend = 12345;
		int divider = 33;
		String outputString = longDivisionAsString(dividend, divider);
		System.out.println(outputString);
	}

	public static String longDivisionAsString(int dividend, int divider) {
		String outputString = "";
		if (divider == 0) {
			outputString = "Divider cannot be \"0\"!";
			return outputString;
		}
		if (divider < 0 || dividend < 0) {
			outputString = "Dividend or divider less \"0\"!  Input other numbers!";
			return outputString;
		}
		if (divider > Integer.MAX_VALUE || dividend > Integer.MAX_VALUE) {
			outputString = "Dividend or divider cannot be larger than 2 147 483 647.  Input other numbers!";
			return outputString;
		}
		if (divider > dividend) {
			outputString = "Divider is larger than dividend. Input other numbers!";
			return outputString;
		}
		int finalResult = getDivisionResult(dividend, divider);
		double rsultAsDouble = getDivisionResultAsDouble(dividend, divider);
		String periodAsString = getPeriodAsString(divider, rsultAsDouble);
		//System.out.println(periodAsString);
		outputString += dividend + "|" + divider + "\n";
		char [] numbersOfDividend = Integer.toString(dividend).toCharArray();
		String partOfDividendAsString = "";
		int tempDividend;
		int tempResult;
		int spacesBefore;
		int spacesAfter;
		int quantityOfCycles = 0;
		boolean firstRow = true;
		boolean secondRow = false;
		for (int i = 0; i < numbersOfDividend.length; i++) {
			quantityOfCycles++;
			partOfDividendAsString = partOfDividendAsString + Integer.toString(Character.getNumericValue(numbersOfDividend[i]));
			tempDividend = Integer.parseInt(partOfDividendAsString);
			if(tempDividend<divider){
				quantityOfCycles--;
				continue;				
			}
			tempResult = divider*getDivisionResult(tempDividend, divider);
			char [] symbolsInRes = Integer.toString(tempResult).toCharArray();
			spacesAfter = numbersOfDividend.length - i - 1;
			spacesBefore = numbersOfDividend.length - spacesAfter - symbolsInRes.length;
			if(firstRow){
				outputString += setSpaceInString(spacesBefore) + tempResult + setSpaceInString(spacesAfter);
				String minusesAsString = setMinuses(divider, finalResult, periodAsString);
				outputString += "+" + minusesAsString + "\n";
				// period included
				outputString += setSpaceInString(spacesBefore) + setUnderlineInString(symbolsInRes.length) + setSpaceInString(spacesAfter) + "|" + finalResult + periodAsString + "\n";
				secondRow = true;
				firstRow = false;
			}
			if(secondRow){
				secondRow = false;
				partOfDividendAsString = String.valueOf(getRemainOfDivision(tempDividend, divider));
				if (getRemainOfDivision(tempDividend, divider) == 0){
					partOfDividendAsString = "";
				}
				continue;
			}
				
			outputString += setSpaceInString(spacesBefore) + partOfDividendAsString + "\n";
			outputString += setSpaceInString(spacesBefore) + tempResult + "\n";
			outputString += setSpaceInString(spacesBefore) + setUnderlineInString(symbolsInRes.length) + "\n";
			
			partOfDividendAsString = String.valueOf(getRemainOfDivision(tempDividend, divider));
			if (getRemainOfDivision(tempDividend, divider) == 0){
				partOfDividendAsString = "";
			}
			
			if(i == (numbersOfDividend.length-1)){
				char [] numbersOfRemainOfDivision = Integer.toString(getRemainOfDivision(dividend, divider)).toCharArray();	
				spacesBefore = numbersOfDividend.length - numbersOfRemainOfDivision.length;
				outputString += setSpaceInString(spacesBefore) + getRemainOfDivision(dividend, divider) + "\n";
			}
		}
		if(quantityOfCycles == 1){
			char [] numbersOfRemainOfDivision = Integer.toString(getRemainOfDivision(dividend, divider)).toCharArray();
			spacesBefore = numbersOfDividend.length - numbersOfRemainOfDivision.length;
			outputString += setSpaceInString(spacesBefore) + getRemainOfDivision(dividend, divider);
		}
		return outputString;
	}

	public static int getDivisionResult(int dividend, int divider) {
		int result = (Integer)dividend/divider;
		return result;
	}
	
	public static double getDivisionResultAsDouble(int dividend, int divider) {
		double DivisionResultAsDouble = Double.valueOf(dividend) / Double.valueOf(divider);
		return DivisionResultAsDouble;
	}
	
	public static int getRemainOfDivision(int dividend, int divider) {
		int result = (Integer)dividend%divider;
		return result;
	}

	public static String getPeriodAsString(int divider, double rsultAsDouble) {
		//System.out.println(rsultAsDouble);
		char[] numbersOfDivider = Integer.toString(divider).toCharArray();
		int lengthOfdivider = numbersOfDivider.length;
		char[] numbersOfresultAsDouble = Double.toString(rsultAsDouble).toCharArray();
		boolean countdown = false;
		boolean startofPeriod = false;
		String period = "";
		int skipedNumber = -1;
		int firstNumberInPeriod = 0;
		for(int i=0; i<numbersOfresultAsDouble.length; i++){
			if(countdown){
				skipedNumber++;
			}
			if(numbersOfresultAsDouble[i] == '.'){
				countdown = true;
			}
			if (skipedNumber == lengthOfdivider) {
				firstNumberInPeriod = Character.getNumericValue(numbersOfresultAsDouble[i]);
				startofPeriod = true;
			} 
			if(firstNumberInPeriod == Character.getNumericValue(numbersOfresultAsDouble[i]) && skipedNumber > lengthOfdivider){
				period = "(" + period + ")";
				return period;
			}
			if(startofPeriod){
				period += String.valueOf(numbersOfresultAsDouble[i]);
			}
			if(skipedNumber >9){
				return "(" + ")";
			}
		}
		return "(" + ")";
	}

	private static String setMinuses(int divider, int finalResult, String periodAsString) {
		String minusesAsString = "";
		int minusesLength;
		char [] symbolsInDvivder = Integer.toString(divider).toCharArray();
		char [] symbolsInfinalResult = Integer.toString(finalResult).toCharArray();
		char []	symbolsperiodAsString = periodAsString.toCharArray();
		if (symbolsInDvivder.length > (symbolsInfinalResult.length + symbolsperiodAsString.length)) {
			minusesLength = symbolsInDvivder.length;
		} 
		else{
			minusesLength = (symbolsInfinalResult.length + symbolsperiodAsString.length);
		}
		for (int j = 0; j < minusesLength; j++){
			minusesAsString += "-";
		}
		return minusesAsString;
	}

	public static String setSpaceInString(int quantityOfSpaces) {
		String spaces = "";
		for (int i = 0; i < quantityOfSpaces; i++){
			spaces += " ";
		}
		return spaces;
	}

	public static String setUnderlineInString(int quantityOfUnderlines) {
		String underline = "";
		for (int k = 0; k < quantityOfUnderlines; k++){
			underline += "_";
		}
		return underline;
	}
}