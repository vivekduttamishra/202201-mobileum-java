package in.conceptarchitect.banking;

public class Bank {

	
	private String name;
	private double rate;
	int accountCount=0;

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

	public int openAccount(String string, String string2, double d) {
		// TODO Auto-generated method stub
		return ++accountCount;
	}

	public int getAccountCount() {
		// TODO Auto-generated method stub
		return accountCount;
	}

	public boolean closeAccount(int i, String string) {
		// TODO Auto-generated method stub
		return true;
	}

}















