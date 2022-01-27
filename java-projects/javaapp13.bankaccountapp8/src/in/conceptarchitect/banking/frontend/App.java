package in.conceptarchitect.banking.frontend;

import in.conceptarchitect.banking.Bank;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank icici=new Bank("ICICI",12);
		//create dummy accounts
		icici.openAccount("savings", "Account S", "p@ss", 50000);
		icici.openAccount("current", "Account C", "p@ss", 50000);
		icici.openAccount("overdraft","Account O", "p@ss", 50000);
		
		ATM iciciAtm=new ATM(icici);
		
		iciciAtm.start();	

	}

}
