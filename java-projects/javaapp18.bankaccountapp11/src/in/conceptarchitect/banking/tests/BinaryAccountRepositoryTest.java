package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.BinaryAccountRepository;
import in.conceptarchitect.banking.CurrentAccount;
import in.conceptarchitect.banking.HashmapAccountRepository;
import in.conceptarchitect.banking.SavingsAccount;

public class BinaryAccountRepositoryTest {
	
	BankAccount account1;
	BankAccount account2;
	BinaryAccountRepository repository;
	String path="D:\\MyWorks\\Corporate\\202201-mobileum-java\\accounts.db";
	File file;
	String name1="Name1";
	String name2="Name2";
	String password="p@ss";
	double amount=20000;
			
	@Before
	public void setUp() throws Exception {
		
		file=new File(path);
		if(file.exists())
			file.delete();
		assumeFalse(file.exists());
		
		var memoryRepository=new HashmapAccountRepository();		
		repository=new BinaryAccountRepository(memoryRepository, path);
		account1=new SavingsAccount(1, name1, password, amount);
		account2=new CurrentAccount(2,name2,password,amount);
		
		
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
		
		assumeFalse(file.exists());
		repository.addAccount(account1);
		repository.addAccount(account2);
		
		repository.save();
		assumeTrue(file.exists());
		
		var newRepository=new BinaryAccountRepository(new HashmapAccountRepository(), path);
		
		var a1= repository.getAccount(1);
		
		assertEquals(2, newRepository.getAccountCount());
		assertEquals(name1, a1.getName());
		
		
	}
	
	
	
	

}
