package in.conceptarchitect.banking.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import in.conceptarchitect.banking.BankAccount;
import in.conceptarchitect.banking.CurrentAccount;
import in.conceptarchitect.banking.OverdraftAccount;
import in.conceptarchitect.banking.SavingsAccount;

public class StreamTests {
	
	ArrayList<BankAccount> accounts;
	BankAccount a1,a2,a3,a4,a5;
	String password="p@ss";
	
	
	@Before
	public void arrange() {
		a1=new SavingsAccount(1,"Vivek Mishra", "p@ss",50000);
		a2=new CurrentAccount(2, "Sanjay Mall","otherpassword",20000);
		a3=new OverdraftAccount(3,"Shivanshi Mishra","p@ss",50000);
		a4=new OverdraftAccount(4,"Amit Singh","p@ss",25000);
		a5=new CurrentAccount(5,"Aman Singh","p@ss",30000);
		accounts=new ArrayList<>();
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		accounts.add(a4);
		accounts.add(a5);
		
		a3.withdraw(51000,password); //now balance is in negative		
		
	}
	
	
	@Test
	public void weCanTakeAStreamFromACollection() {
		var stream= accounts.stream();
		
		assertTrue(stream instanceof Stream);
	}
	
	@Test
	public void weCanWrapArbitraryDataAsStream() {
		var stream=Stream.of(1,2,3,4,5);
		
		assertTrue(stream instanceof Stream);
	}
	
	@Test
	public void weCanProcessStreamUsingForEachTerminalOperation() {
		
		accounts
			.stream()
			.forEach(a->System.out.println(a));
		
	}
	
	@Test
	public void weCanUseMethodReferenceForLambdaFunction() {
		System.out.println("Output printed using Method Reference");
		accounts
			.stream()
			.forEach(System.out::println);
	}
	
	@Test
	public void weCanStoreAllValueOfAStreamToACollectionUsingForEach() {
		
		var result= new ArrayList<BankAccount>();
		
		accounts
			.stream()
			.forEach(result::add);   // a-> result.add(a)
		
		
		assertEquals(accounts.size(), result.size());
		
		
		
	}
	
	int count=0;
	
	@Test
	public void weCanCountAllTheItemsOfStreamUsingForEachUsingClassField() {
		
		
		accounts
			.stream()
			.forEach(a-> count++);
		
		assertEquals(accounts.size(), count);
	}
	
	@Test
	public void weCanCountAllTheItemsOfStreamUsingForEachUsingCounterObject() {
		
		final Counter counter=new Counter();
		
		accounts
			.stream()
			.forEach(a->counter.increment());
		
		assertEquals(accounts.size(), counter.value());
	}
	
	
	@Test
	public void weCanCountAllTheItemsOfStreamUsingForEachUsingArrayOfInt() {
		
		int []count= {0}; //array is a reference type
		
		accounts
			.stream()
			.forEach(a->count[0]++);
		
		assertEquals(accounts.size(), count[0]);
	}
	
	@Test
	public void weCanCountAllItemsUsingCountTerminalOperation() {
		
		var result= accounts.stream().count();
		
		
		assertEquals(accounts.size(), result);
		
		
	}
	
	@Test
	public void weCanCollectTheResultInAListUsingCollectTerminalOperation() {
		var result= accounts.stream().collect(Collectors.toList());  //collect the result as list
		
		assertEquals(accounts.size(), result.size());
		assertEquals(accounts.get(0), result.get(0));
		
	}
	
	
	@Test
	public void weCanFilterCurrentAccountsWithFilterIntermediateOperation() {
		
		var currentAccountCount= accounts
									.stream()
									.filter(a-> a instanceof CurrentAccount)
									.count();
		
		assertEquals(2, currentAccountCount);
	}
	
	@Test
	public void weCanApplyMultipleTerminalOperations() {
		
		var negativeBalances= accounts
										.stream()									//get stream of all accounts
										.filter(a-> a instanceof OverdraftAccount)  //filter and get stream of overdraft accounts
										.filter(a-> a.getBalance()<0)				//filter and get stream of accounts with negative balance
										.map(a-> a.getBalance()) 					//cover stream of accounts to stream of doubles
										.collect(Collectors.toList());				//collect a list of doubles
		
		
		
		assertEquals(1, negativeBalances.size());
		assertEquals(-1010, negativeBalances.get(0),0);
		
		
	}
	
	@Test
	public void aStreamDoesntWorkWithoutATerminalFunctionAttached() {
		
		var filter1Counter=new Counter();
		var filter2Counter=new Counter();
		var map1Counter=new Counter();
		var map2Counter=new Counter();
		
		var stream= accounts
							.stream()
							.filter( account->{
								filter1Counter.increment();
								return account instanceof OverdraftAccount;								
							})
							.map(account->{
								map1Counter.increment();
								return account.getBalance();
							})
							.filter(balance->{
								filter2Counter.increment();
								return balance<0;
							})
							.map(balance->{
								map2Counter.increment();
								return balance; //no change.
							});
		
		//what is the value of filter1Counter, filter2Counter and map1Counter map2Counter
		//stream never worked because there is no terminal
		assertEquals(0,filter1Counter.value());
		assertEquals(0, map1Counter.value());
		assertEquals(0, filter2Counter.value());
		assertEquals(0, map2Counter.value());
		
	}
	
	@Test
	public void aStreamWorksIfThereIsATerminalAttached() {
		
		var filter1Counter=new Counter();
		var filter2Counter=new Counter();
		var map1Counter=new Counter();
		var map2Counter=new Counter();
		
		var stream= accounts
							.stream()
							.filter( account->{
								filter1Counter.increment();
								return account instanceof OverdraftAccount;								
							})
							.map(account->{
								map1Counter.increment();
								return account.getBalance();
							})
							.filter(balance->{
								filter2Counter.increment();
								return balance<0;
							})
							.map(balance->{
								map2Counter.increment();
								return balance; //no change.
							});
		
		//what is the value of filter1Counter, filter2Counter and map1Counter map2Counter
		var finalCount= stream.count(); //terminal is now attached
		
		assertEquals(accounts.size(),filter1Counter.value());
		assertEquals(2, map1Counter.value());
		assertEquals(2, filter2Counter.value());
		assertEquals(finalCount, map2Counter.value());
		
	}
	
	@Test(expected=IllegalStateException.class)
	public void youCantAttachMoreThanOneTerminalToAStream() {
		
		var stream= accounts.stream();
		
		var count=stream.count(); //stream is now consumed
		
		//we can't consume it again
		
		var result=stream.collect(Collectors.toList());
		
	}
	
	@Test()
	public void youCantRegenerateStreamFromOriginalSource() {
		
		var stream= accounts.stream();
		
		var count=stream.count(); //stream is now consumed
		
		//We can regenerate stream		
		var result=accounts.stream().collect(Collectors.toList());
		
		
		assertEquals(count, result.size());
	}
	
	
	
		
	
}







