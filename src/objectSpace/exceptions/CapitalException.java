package objectSpace.exceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку со столицей
 * @author vladimir
 */
public class CapitalException extends ArgumentCityException{
    public CapitalException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public CapitalException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
