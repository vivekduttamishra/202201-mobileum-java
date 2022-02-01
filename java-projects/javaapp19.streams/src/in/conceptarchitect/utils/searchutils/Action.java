package in.conceptarchitect.utils.searchutils;

@FunctionalInterface
public interface Action<T> {

	void execute(T item);
	
}
