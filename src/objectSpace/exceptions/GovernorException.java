package objectSpace.exceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку с мэром
 * @author vladimir
 */
public class GovernorException extends ArgumentCityException{
    public GovernorException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public GovernorException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
