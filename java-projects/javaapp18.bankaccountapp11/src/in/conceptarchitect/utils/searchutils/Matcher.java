package in.conceptarchitect.utils.searchutils;

@FunctionalInterface
public interface Matcher<T> {

	boolean isMatch(T value); //returns true/false
	
	default boolean isNotMatch(T value) {
		return !isMatch(value);
	}
}
