package javaapp14.collectiontests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class HashMapTests {

	HashMap map;
	String key1="IN";
	String value1="INDIA";
	String key2="JP";
	String value2="Japan";
	String key3="FR";
	String value3="France";
	String newKey="DE";
	String newValue="Germany";
	int size;

	@Before
	public void arrange() {
		map=new HashMap();
		map.put(key1, value1);
		map.put(key2, value2);
		map.put(key3, value3);
		
		size=map.size();
		
	}
	
	@Test
	public void returnsValidValueForValidKey() {
		
		assertEquals(value1, map.get(key1));
		
	}
	
	@Test
	public void returnsNullValueForInvalidKey() {
		var value= map.get("invalid-key");
		
		assertNull(value);
	}
	
	
	@Test
	public void canAddNewKeyValuePair() {
		map.put(newKey, newValue);
		assertEquals(size+1, map.size());
	}
	
	@Test
	public void replacesCurrentValueIfWePutAgainstExisingKey() {
		map.put(key1, newValue);
		
		assertEquals(size,map.size()); //new item is not added.
		
		assertEquals(newValue, map.get(key1)); //the value in the list is replaced.
	}
	
	

}
