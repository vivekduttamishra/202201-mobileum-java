package in.conceptarchitect.banking;

public class Bank {

	
	private String name;
	private double rate;
	

	public Bank(String name, double rate) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.rate=rate;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public double getRate() {
		// TODO Auto-generated method stub
		return rate;
	}
	
	int accountCount=0;	
	int lastAccountNumber=0;
	BankAccount [] accounts=new BankAccount[100];


	public int openAccount(String accountType,String name, String password,  double amount) {
		// TODO Auto-generated method stub
		
		BankAccount account=null;
		if(accountType.equals("savings"))
				account= new SavingsAccount(0, name,password,amount);
		else if(accountType.equals("current"))
				account=new CurrentAccount(0,name,password,amount);
		else if(accountType.equals("overdraft"))
				account=new OverdraftAccount(0, name, password, amount);
		
		if(account==null)
				return -1;
		
		return addAccount(account);
	}

	private int addAccount(BankAccount account) {
		int accountNumber= ++lastAccountNumber;
		accountCount++;
		account.setAccountNumber(accountNumber);
		accounts[accountNumber]=account;
		return accountNumber;
	}
	
	private BankAccount getAccount(int accountNumber) {
		if(accountNumber<1 || accountNumber>lastAccountNumber || accounts[accountNumber]==null)
			return null;


		return accounts[accountNumber];
	}
	
	
	public double closeAccount(int accountNumber, String password) {
		// TODO Auto-generated method stub
		var account=getAccount(accountNumber,password);
		if(account==null)
			return -1;
		if(account.getBalance()<0)
			return -1;
		accounts[accountNumber]=null;
		accountCount--;
		return account.getBalance();
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}

	public BankAccount getAccount(int accountNumber, String password) {
		var account=getAccount(accountNumber);
		
		
		
		if(account==null || !account.authenticate(password))
			return null;
		
		return account;
	}

	public boolean deposit(int accountNumber, double amount) {
		// TODO Auto-generated method stub
		var account=getAccount(accountNumber);
		if(account!=null){
			return account.deposit(amount);
		} else
			return false;
	}

	public Response withdraw(int accountNumber, double amount, String password) {
		// TODO Auto-generated method stub
		var account=getAccount(accountNumber);
		if(account!=null)
			return account.withdraw(amount, password);
		else
			return new Response(ResponseStatus.INVALID_ACCOUNT,"No such account");
	}

	public Response transfer(int accountNumber, double amount, String password, int targetAccount) {
		// TODO Auto-generated method stub
		var source= getAccount(accountNumber);
		if(source==null)
			return new Response(ResponseStatus.INVALID_ACCOUNT,"Invalid Source Account");

		var target=getAccount(targetAccount);
		if(target==null)
			return new Response(ResponseStatus.INVALID_ACCOUNT,"Invalid Target Account");
	
		var withdrawResult= source.withdraw(amount, password);
		if(withdrawResult.getCode()==ResponseStatus.SUCCESS) {
			target.deposit(amount);
			return new Response(ResponseStatus.SUCCESS,null);
			
		}		
		
		return withdrawResult; 
	}

	public void creditInterest() {
		// TODO Auto-generated method stub
		for(var i=1;i<=lastAccountNumber;i++) {
			var account=accounts[i]; //may be null for closed account
			if(account!=null)
				account.creditInterest(rate);
		}
	}

	public double getBalance(int accountNumber, String password) {
		// TODO Auto-generated method stub
		var account=getAccount(accountNumber);
		if(account!=null && account.authenticate(password))
				return account.getBalance();
		else
			return -1;
	}

	
	public Response changePassword(int accountNumber, String currentPassword, String newPassword) {
		var account=getAccount(accountNumber);
		if(account==null)
			return new Response(ResponseStatus.INVALID_ACCOUNT,"No such account");
		if(account.changePassword(currentPassword, newPassword))
			return new Response(ResponseStatus.SUCCESS,null);
		else
			return new Response(ResponseStatus.INVALID_CREDENTIALS,"Invalid Current Password");
	}

	public String[] getAllAccountsInfo() {
		// TODO Auto-generated method stub
		String [] info= new String [ accountCount];
		var x=0;
		
		for(var account : accounts) {
			if(account!=null) {
				
				info[x]= account.toString();
				x++;
			}
		}
		
		return info;
		
	}
	 

}















