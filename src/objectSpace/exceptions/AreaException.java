package objectSpace.exceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку с площадью
 * @author vladimir
 */
public class AreaException extends ArgumentCityException{
    public AreaException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public AreaException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
