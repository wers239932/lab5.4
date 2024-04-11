package objectSpace.exceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку с высотой над уровнем моря
 * @author vladimir
 */
public class HeightException extends ArgumentCityException{
    public HeightException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public HeightException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }

}
