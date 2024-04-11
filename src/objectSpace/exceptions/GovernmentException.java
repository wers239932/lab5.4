package objectSpace.exceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку с правительством
 * @author vladimir
 */
public class GovernmentException extends ArgumentCityException{
    public GovernmentException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public GovernmentException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
