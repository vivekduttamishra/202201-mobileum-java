package in.conceptarchitect.utils.searchutils;

public interface Converter<T,X> {

	 X convert(T object);
}
