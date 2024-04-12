package objectSpace.exceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку с высотой над уровнем моря
 * @author vladimir
 */
public class HeightException extends ArgumentCityException{
    public HeightException(String msg){
        super(msg);
    }
    public HeightException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }

}
