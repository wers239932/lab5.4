package objectSpace.exceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку с carCode
 * @author vladimir
 */
public class CarCodeException extends ArgumentCityException{
    public CarCodeException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public CarCodeException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
