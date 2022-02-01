package in.conceptarchitect.utils.searchutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class ListSearch {
	
	public  static  <T>  List<T> search(Collection<T> list, Matcher<T> matcher){
		ArrayList<T> result=new ArrayList<T>();
		
		for(var item :list) {
			if(matcher.isMatch(item))
				result.add(item);
		}
		
		return result;
	}

}
