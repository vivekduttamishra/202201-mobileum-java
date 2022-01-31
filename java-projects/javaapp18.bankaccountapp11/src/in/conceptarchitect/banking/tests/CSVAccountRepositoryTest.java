package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.CSVAccountRepository;
import in.conceptarchitect.banking.CurrentAccount;
import in.conceptarchitect.banking.HashmapAccountRepository;
import in.conceptarchitect.banking.SavingsAccount;

public class CSVAccountRepositoryTest {
	
	BankAccount account1;
	BankAccount account2;
	CSVAccountRepository repository;
	String path="D:\\MyWorks\\Corporate\\202201-mobileum-java\\accounts.csv";
	File file;
	String name1="Name1";
	String name2="Name2";
	String password="p@ss";
	double amount=20000;
			
	@Before
	public void setUp() throws Exception {
		
		var memoryRepository=new HashmapAccountRepository();		
		repository=new CSVAccountRepository(memoryRepository, path);
		account1=new SavingsAccount(1, name1, password, amount);
		account2=new CurrentAccount(2,name2,password,amount);
		file=new File(path);
		if(file.exists())
			file.delete();
	}

	@Test
	public void canWorkWithNonExistentPath() {
		assertNotNull(repository);
	}
	
	@Test
	public void canSaveEmptyRepository() {
		repository.save();
		assertTrue(file.exists());
	}
	
	
	@Test
	public void canSaveRepositoryWithAccounts() {
		repository.addAccount(account1);
		repository.addAccount(account2);
		
		repository.save();
		assertTrue(file.exists());
		
		
	}
	
	@Test
	public void canLoadRepositoryWithAccounts() {
		repository.addAccount(account1);
		repository.addAccount(account2);
		
		repository.save();
		assumeTrue(file.exists());
		
		var newRepository=new CSVAccountRepository(new HashmapAccountRepository(), path);
		
		var a1= repository.getAccount(1);
		
		assertEquals(2, repository.getAccountCount());
		assertEquals(name1, a1.getName());
		
		
	}
	
	
	
	

}
