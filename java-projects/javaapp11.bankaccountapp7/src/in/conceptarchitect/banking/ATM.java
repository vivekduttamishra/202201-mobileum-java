package in.conceptarchitect.banking;

import in.conceptarchitect.utils.Input;

public class ATM {

	Bank bank; //associated parent bank.
	int accountNumber;
	Input keyboard=new Input();
	private String password;
	

	public ATM(Bank bank) {
		super();
		this.bank = bank;
	}
	
	public void start() {
		accountNumber=keyboard.readInt("account number?");
		password=keyboard.readString("password?");
		//A secret menu
		if(accountNumber==-1 && password=="NIMDA")
			adminMenu();
		else
			mainMenu();
	}
	
	
	
	private void adminMenu() {
		// TODO Auto-generated method stub
		while(true) {
			var choice=keyboard.readInt("1. open account 2. credit interest 3. view all accounts 0. exit ?");
			switch(choice) {
				case 0:
					return ;
					
				default:
					showError("invalid choice. retry");
			}
		}
	}

	private void mainMenu() {
		
		while(true) {
			var choice=keyboard.readInt("1. deposit 2. withdraw 3. check balance 4. transfer 5. close account 0. exit ?");
			switch(choice) {
			case 1:
				doDeposit();
				break;
			case 2:
				doWithdraw();
				break;
				
			case 3:
				doCheckBlance();
				break;
				
			case 4:
				doTransfer();
				break;
				
			case 5:
				doCloseAccount();
				break;
				
			case 0:
				return ;
				
			default:
				showError("invalid choice. retry");
			
			}
		}
	}

	private void showError(String string) {
		// TODO Auto-generated method stub
		System.err.println(string);
	}

	private void doCloseAccount() {
		// TODO Auto-generated method stub
		
	}

	private void doTransfer() {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("amount?");
		int targetAccount=keyboard.readInt("target account?");
		Response response= bank.transfer(accountNumber, amount, password, targetAccount);
		if(response.getCode()==ResponseStatus.SUCCESS) {
			showInfo("Amount Transferred Successfully");
		} else {
			showError(response.getMessage());
		}
		
	}

	private void doCheckBlance() {
		// TODO Auto-generated method stub
		var account=bank.getAccount(accountNumber,password);
		if(account==null){
			showError("Invalid Credentials");
			return;
		}
		double balance=account.getBalance();
		showInfo("Your Balance:"+balance);
		
	}

	private void doWithdraw() {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("Amount to withdraw?");
		Response result= bank.withdraw(accountNumber, amount, password);
		if(result.getCode()==ResponseStatus.SUCCESS)
			dispenseCash(amount);
		else
			showError(result.getMessage());
		
	}

	private void dispenseCash(int amount) {
		// TODO Auto-generated method stub
		System.out.println("Please collect your cash : "+amount);
	}

	private void doDeposit() {
		// TODO Auto-generated method stub
		int amount=keyboard.readInt("Deposit Amount?"); //ATM allows only whole sum (actully multiple of 100)
		if(amount%100!=0) {
			showError("Invalid Denomination");
			return ;
		}
		
		if(bank.deposit(accountNumber, amount))
			showInfo("Amount deposited");
		else
			showInfo("Deposit failed");
		
		
	}

	private void showInfo(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}
	
	
}
