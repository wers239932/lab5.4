package objectSpace.objectExceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку с carCode
 * @author vladimir
 */
public class CarCodeException extends ArgumentCityException{
    public CarCodeException(String msg){
        super(msg);
    }
    public CarCodeException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
