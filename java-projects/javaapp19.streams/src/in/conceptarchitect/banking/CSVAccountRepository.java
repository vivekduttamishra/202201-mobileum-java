package in.conceptarchitect.banking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import in.conceptarchitect.banking.exceptions.InvalidAccountTypeException;

public class CSVAccountRepository implements AccountRepository {
	
	AccountRepository memoryRepository;
	int lastId=0;
	String path;
	

	public CSVAccountRepository(AccountRepository memoryRepository,String path) {
		super();
		this.memoryRepository = memoryRepository;
		this.path=path;
		load();
	}
	

	private void load() {
		// TODO Auto-generated method stub
		BufferedReader reader=null;
		
		try{
			reader=new BufferedReader(new FileReader(path));
			lastId= Integer.parseInt(reader.readLine()); //first line only contains last It
			while(true) {
				var line= reader.readLine();
				if(line==null)
					break; //we read all accounts;
				
				var fields= line.split(","); //now we have array like ["SavingsAccount","1","Vivek","xxxx","20000"];
				
				int accountNumber= Integer.parseInt(fields[1]);
				var name=fields[2];
				var password=fields[3];
				var amount=Double.parseDouble(fields[4]);
				BankAccount account=null;
				switch(fields[0])
				{
				case "in.conceptarchitect.banking.SavingsAccount":
					account=new SavingsAccount(accountNumber, name, password, amount);
					break;
				case "in.conceptarchitect.banking.CurrentAccount":
					account=new SavingsAccount(accountNumber, name, password, amount);
					break;
				case "in.conceptarchitect.banking.OverdraftAccount":
					account=new SavingsAccount(accountNumber, name, password, amount);
					break;
				default:
					throw new InvalidAccountTypeException(accountNumber, "Invalid Account Type"+fields[0]);
				}
				
				account.password=password; //I don't want to encrypt again
				
				memoryRepository.addAccount(account);
				account.setAccountNumber(accountNumber);
				
				
			}
			
		}catch(Exception ex) {
			//First time file may not exist. so it ok to have exception.
		}
		finally{
			try { reader.close(); } catch(Exception ex) {}
		}
	}

	

	

	public void save() {
		// TODO Auto-generated method stub
		PrintWriter writer=null;
		try {
			writer=new PrintWriter(new FileWriter(path));
			writer.println(lastId);
			
			for(var account : getAllActiveAdccounts()) {
				
				writer.printf("%s,%d,%s,%s,%f\r\n", account.getClass().getName(),
													account.getAccountNumber(), 
													account.getName(), 
													account.getPassword(), 
													account.getBalance());
			}
			
		}catch(Exception ex) {
			throw new RuntimeException(ex.getMessage(),ex);
		} finally {
			try{writer.close();}catch(Exception ex) {}
		}
	}

	
	
	@Override
	public int addAccount(BankAccount account) {
		// TODO Auto-generated method stub
		var result=memoryRepository.addAccount(account);
		lastId++;
		save();
		return result;
	}

	@Override
	public BankAccount getAccount(int accountNumber) {
		// TODO Auto-generated method stub
		return memoryRepository.getAccount(accountNumber);
	}
	
	

	@Override
	public void removeAccount(int accountNumber) {
		// TODO Auto-generated method stub
		memoryRepository.removeAccount(accountNumber);
		save();
	}

	@Override
	public BankAccount[] getAllActiveAdccounts() {
		// TODO Auto-generated method stub
		return memoryRepository.getAllActiveAdccounts();
	}

	@Override
	public int getAccountCount() {
		// TODO Auto-generated method stub
		return memoryRepository.getAccountCount();
	}

}
