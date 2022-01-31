package in.conceptarchitect.banking;

import java.util.ArrayList;
import java.util.List;

import in.conceptarchitect.utils.searchutils.Action;
import in.conceptarchitect.utils.searchutils.Converter;
import in.conceptarchitect.utils.searchutils.Matcher;


public interface AccountRepository {

	int addAccount(BankAccount account);

	BankAccount getAccount(int accountNumber);

	void removeAccount(int accountNumber);

	BankAccount[] getAllActiveAdccounts();

	int getAccountCount();
	
	void save();
	
	public default List<BankAccount> search(Matcher<BankAccount> matcher){
		
		var result=new ArrayList<BankAccount>();
		
		for(var account : getAllActiveAdccounts())
			if(matcher.isMatch(account))
				result.add(account);
		
		
		return result;
		
	}
	
	public default void forEach(Action<BankAccount> action) {
		for(var account:getAllActiveAdccounts()) {
			action.execute(account);
		}
	}
	
	public default <X> List<X> map( Converter<BankAccount> converter){
	
		ArrayList<X> result = new ArrayList<X>();
		for(var account : getAllActiveAdccounts()) {
			X value=converter.convert(account);
			result.add( value);
		}
		
		return result;
	}

}





