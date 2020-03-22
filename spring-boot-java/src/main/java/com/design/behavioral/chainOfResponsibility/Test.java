package com.design.behavioral.chainOfResponsibility;

import java.util.Scanner;

@SuppressWarnings("resource")
public class Test {
	
	public static void main(String[] args) {
		
		ATMDispenseChain atmDispenser = new ATMDispenseChain();
		while(true) {
			int amount = 0;
			System.out.println("Enter amount to dispense");
			Scanner input = new Scanner(System.in);
			amount = input.nextInt();
			if (amount % 10 != 0) {
				System.out.println("Amount should be in multiple of 10s.");
			} else {
				// process the request
				atmDispenser.dc.dispense(new Currency(amount));
			}
			
		}
	}
	
	public static class ATMDispenseChain {
		private DispenseChain dc;
		
		public ATMDispenseChain() {
			this.dc = new Dollar50Dispenser();
			DispenseChain dc2 = new Dollar20Dispenser();
			DispenseChain dc3 = new Dollar10Dispenser();
			
			// set the chain of responsibility
			dc.setNextChain(dc2);
			dc2.setNextChain(dc3);
		}
	}
}
