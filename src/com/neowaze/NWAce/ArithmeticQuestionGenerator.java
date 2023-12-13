package com.neowaze.NWAce;

import java.util.Random;

public class ArithmeticQuestionGenerator {
	
	private int answer;
	private String question;
	
	public void generateNewQuestion() {
		Random rand = new Random();
		int num1, num2, num3, gaya_soal = rand.nextInt(20);
		if(gaya_soal==0) {
			num1 = rand.nextInt(10); 
			num2 = rand.nextInt(100);
			answer = num1 + num2;
			question = num1 + " + " + num2 + " =";
		} else if(gaya_soal==1) {
			num1 = rand.nextInt(100); 
			num2 = rand.nextInt(1000);
			answer = num1 + num2;
			question = num1 + " + " + num2 + " =";
		} else if(gaya_soal==2) {
			num1 = rand.nextInt(1000); 
			num2 = rand.nextInt(10000);
			answer = num1 + num2;
			question = num1 + " + " + num2 + " =";
		} else if(gaya_soal==3) {
			num1 = rand.nextInt(100); 
			num2 = rand.nextInt(100);
			answer = num1 - num2;
			question = num1 + " - " + num2 + " =";
		} else if(gaya_soal==4) {
			num1 = rand.nextInt(1000); 
			num2 = rand.nextInt(1000);
			answer = num1 - num2;
			question = num1 + " - " + num2 + " =";
		} else if(gaya_soal==5) {
			num1 = rand.nextInt(100); 
			num2 = rand.nextInt(10);
			answer = num1 * num2;
			question = num1 + " * " + num2 + " =";
		} else if(gaya_soal==6) {
			num1 = rand.nextInt(100); 
			num2 = rand.nextInt(100);
			answer = num1 * num2;
			question = num1 + " * " + num2 + " =";
		} else if(gaya_soal==7) {
			num1 = rand.nextInt(1000); 
			num2 = rand.nextInt(10);
			answer = num1 * num2;
			question = num1 + " * " + num2 + " =";
		} else if(gaya_soal==8) {
			answer = rand.nextInt(100);
			num1 = rand.nextInt(10); 
			num2 = answer * num1;
			question = num2 + " / " + num1 + " =";
		} else if(gaya_soal==9) {
			answer = rand.nextInt(1000);
			num1 = rand.nextInt(100); 
			num2 = answer * num1;
			question = num2 + " / " + num1 + " =";
		} else if(gaya_soal==10) {
			num1 = rand.nextInt(100);
			answer = num1 * num1;
			question = num1 + " ^ 2 =";
		} else if(gaya_soal==11) {
			answer = rand.nextInt(100);
			num1 = answer * answer;
			question = "sqrt("+ num1 + ") =";
		} else if(gaya_soal==12) {
			num1 = rand.nextInt(100); 
			num2 = rand.nextInt(1000); 
			num3 = rand.nextInt(1000);
			answer = num1 + num2 + num3;
			question = num1 + " + " + num2 + " + " + num3 + " =";
		} else if(gaya_soal==13) {
			num1 = rand.nextInt(1000); 
			num2 = rand.nextInt(1000); 
			num3 = rand.nextInt(100);
			answer = num1 + num2 - num3;
			question = num1 + " + " + num2 + " - " + num3 + " =";
		} else if(gaya_soal==14) {
			num1 = rand.nextInt(100); 
			num2 = rand.nextInt(100); 
			num3 = rand.nextInt(100);
			answer = num1 * num2 + num3;
			question = num1 + " * " + num2 + " + " + num3 + " = ";
		} else if(gaya_soal==15) {
			num1 = rand.nextInt(100); 
			num2 = rand.nextInt(100); 
			num3 = rand.nextInt(100);
			answer = num1 * num2 - num3;
			question = num1 + " * " + num2 + " - " + num3 + " = ";
		} else if(gaya_soal==16) {
			num1 = rand.nextInt(100); 
			num2 = rand.nextInt(100);
			answer = num1 * num1 + num2;
			question = num1 + " ^ 2 + " + num2 + " = ";
		} else if(gaya_soal==17) {
			num1 = rand.nextInt(100); 
			num2 = rand.nextInt(100);
			answer = num1 * num1 - num2;
			question = num1 + " ^ 2 - " + num2 + " = ";
		} else if(gaya_soal==18) {
			num1 = rand.nextInt(100); 
			num2 = rand.nextInt(10);
			answer = num1 % num2;
			question = num1 + " mod " + num2 + " = ";
		} else if(gaya_soal==19) {
			num1 = rand.nextInt(1000); 
			num2 = rand.nextInt(100);
			answer = num1 % num2;
			question = num1 + " mod " + num2 + " = ";
		}
	}
	
	public boolean checkAnswer(int userAnswer) {
		return userAnswer == answer;
	}
	
	public String getQuestion() {
		return question;
	}
	
}