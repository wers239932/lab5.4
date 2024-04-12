package objectSpace.objectExceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку с координатами
 * @author vladimir
 */
public class CoordinatesException extends ArgumentCityException{
    public CoordinatesException(String msg){
        super(msg);
    }

    public CoordinatesException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
