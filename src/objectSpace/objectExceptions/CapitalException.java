package objectSpace.objectExceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку со столицей
 * @author vladimir
 */
public class CapitalException extends ArgumentCityException{
    public CapitalException(String msg){
        super(msg);
    }
    public CapitalException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
