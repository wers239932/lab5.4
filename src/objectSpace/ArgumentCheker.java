package objectSpace;
@FunctionalInterface
public interface  ArgumentCheker<T> {
T parse(String arg) throws Exception;
}