package javaapp14.collectiontests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTests {

	ArrayList list;
	
	class Book{
		
		String title;
		Author author;
		public Book(String title, Author author) {
			super();
			this.title = title;
			this.author = author;
		}
		@Override
		public String toString() {
			return "Book [title=" + title + ", author=" + author + "]";
		}
		
	}
	
	String authorName="Vivek";
	String bookTitle="The Accursed God";
	int authorId=1;
	Author author=new Author(authorId,authorName);
	Book  book=new Book(bookTitle, author);
	
	
	@Before
	public void setUp() {
		list=new ArrayList();
		list.add("Hello World");		
		list.add(20); //auto boxed to Integer
		
		list.add(author);
		
		list.add(book);
	}

	@Test
	public void sizeReturnsTotalItemsWeHave() {
		
		assertEquals(4, list.size());
	}
	
	@Test
	public void clearClearsAllItems() {
		list.clear();
		assertEquals(0, list.size());
	}
	
	@Test
	public void containsReturnsTrueForItemPresentInTheList() {
		
		assertTrue(list.contains("Hello World"));
		assertTrue(list.contains(book));
	}
	
	@Test
	public void containsReturnsFalseForItemNotPresentInTheList() {
		assertFalse(list.contains("Hi"));
		
		//a new book with same title and same author is not equal by default
		assertFalse(list.contains(new Book(bookTitle,author)));
		
	}
	
	
	@Test
	public void containsShouldReturnTrueForSameAuthorIdEventWithDifferentName() {
		
		var newAuthor=new Author(authorId, "Not Same Name"); 
		assertTrue(list.contains(newAuthor));
	}
	
	@Test
	public void containsShouldReturnFalseForSameAuthorNameIfIdDiffers() {
		
		var newAuthor=new Author(1000, authorName); 
		assertFalse(list.contains(newAuthor));
	}
	
	@Test
	public void iteratorCanIterateThroughAllItems() {
		var iterator= list.iterator();
		
		int count=0;
		
		while(iterator.hasNext()) {
			iterator.next();
			count++;
		}
		
		assertEquals(list.size(),count);
	}
	
	@Test
	public void iteratorhasNextInitiallyReturnsTrueForNonEmptyCollection() {
		var iterator= list.iterator();
		assertTrue(iterator.hasNext());
	}
	
	@Test
	public void iteratorHasNextInitiallyReturnsFalseForEmptyCollection() {
		var list=new ArrayList();
		var iterator= list.iterator();
		assertFalse(iterator.hasNext());
	}
	
	
	@Test
	public void iteratorReturnsFirstItemOnFirstNextCall() {
		var iterator=list.iterator();
		
		assertEquals(list.get(0), iterator.next());			
				
	}
	
	@Test
	public void eachCallOfIteratorNextReturnsNextItem() {
		var iterator=list.iterator();
		for(var i=0;i<list.size();i++) {
			assertEquals(list.get(i), iterator.next());
		}
	}
	
	
	@Test(expected = NoSuchElementException.class)
	public void iteratorNextAfterLastItemThrowsNoSuchElementException() {
		//Arrange
		var iterator=list.iterator();
		
		while(iterator.hasNext())
			iterator.next();
		
		assumeFalse(iterator.hasNext());
		
		
		//ACT that throws NoSuchElementException
		iterator.next();
	}
	
	@Test
	public void anyClassWithgetIteratorWorksWithForOfLoop() {
		
		int i=0;
		for(var value : list) {
			assertEquals(list.get(i), value);
			i++;
		}
	}
	
	
	
	

}
