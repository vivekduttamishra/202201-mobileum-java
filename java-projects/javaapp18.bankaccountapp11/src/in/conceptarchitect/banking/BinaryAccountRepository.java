package in.conceptarchitect.banking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BinaryAccountRepository implements AccountRepository {
	
	AccountRepository inMemoryRepository;
	AccountRepository defaultRepository;
	String path;

	public BinaryAccountRepository(AccountRepository repository,String path) {
		// TODO Auto-generated constructor stub
		defaultRepository=repository;
		this.path=path;
		load();
	}

	

	@Override
	public int addAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return inMemoryRepository.addAccount(account);		
		
	}

	@Override
	public BankAccount getAccount(int accountNumber) {
		// TODO Auto-generated method stub
		
		return inMemoryRepository.getAccount(accountNumber);
	}

	@Override
	public void removeAccount(int accountNumber) {
		// TODO Auto-generated method stub
		 inMemoryRepository.removeAccount(accountNumber);
	}

	@Override
	public BankAccount[] getAllActiveAdccounts() {
		// TODO Auto-generated method stub
		return inMemoryRepository.getAllActiveAdccounts();
	}

	@Override
	public int getAccountCount() {
		// TODO Auto-generated method stub
		return inMemoryRepository.getAccountCount();
	}

	@Override
	public void save() {
	
		ObjectOutputStream oos=null;
		try {

			oos	= new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(inMemoryRepository);
		}catch(Exception ex) {
			throw new RuntimeException(ex.getMessage(),ex);
		}finally {
			try {
				oos.close();
			}catch(Exception e) {
				System.err.println("FATAL ERROR");
				e.printStackTrace();
			}
		}

	}	
	
	private void load() {
		// TODO Auto-generated method stub
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(path));
			inMemoryRepository=(AccountRepository) ois.readObject();
		} catch(Exception ex) {
			System.err.println("IGNORED Failed to read repository:"+ex.getMessage());
			//ex.printStackTrace();
			inMemoryRepository=defaultRepository;
		}finally {
			try {
				if(ois!=null)
					ois.close();
			}catch(Exception ex) {
				System.err.println("FATAL ERROR");
				ex.printStackTrace();
			}
		}
		
	}

}
