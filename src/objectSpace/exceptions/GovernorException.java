package objectSpace.exceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку с мэром
 * @author vladimir
 */
public class GovernorException extends ArgumentCityException{
    public GovernorException(String msg){
        super(msg);
    }
    public GovernorException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
