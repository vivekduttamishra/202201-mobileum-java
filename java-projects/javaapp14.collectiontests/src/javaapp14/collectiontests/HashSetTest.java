package javaapp14.collectiontests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

import java.util.HashSet;

import org.junit.Test;

public class HashSetTest {

	public HashSetTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void setDoestAllowDuplicates() {
		
		//ARRANGE
		HashSet set=new HashSet();
		set.add(2);
		set.add(9);
		set.add(11);
		
		assumeTrue(set.size()==3);
		
		//ACT
		set.add(9); //duplicate is not added
		
		//Assert
		assertEquals(3, set.size());
		
	}
}
