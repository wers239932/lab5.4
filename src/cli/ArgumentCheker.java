package cli;
@FunctionalInterface
public interface  ArgumentCheker<T, String> {
T parse(String arg) throws Exception;
}