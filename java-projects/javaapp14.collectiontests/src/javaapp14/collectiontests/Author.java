package javaapp14.collectiontests;

public class Author {

	int id;
	String name;

	public Author(int id,String name) {
		super();
		this.id=id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(! (obj instanceof Author))
			return false;
		
		Author author=(Author) obj;
		
		//if two author object has same name, we will consider them same.
		return author.id== this.id;
	}
	

}
