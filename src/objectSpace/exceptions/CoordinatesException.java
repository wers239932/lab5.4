package objectSpace.exceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку с координатами
 * @author vladimir
 */
public class CoordinatesException extends ArgumentCityException{
    public CoordinatesException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }

    public CoordinatesException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
