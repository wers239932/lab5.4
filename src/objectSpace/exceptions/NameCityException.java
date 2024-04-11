package objectSpace.exceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку с названием города
 * @author vladimir
 */

public class NameCityException extends ArgumentCityException{

    public NameCityException(String msg, int argumentNumber) {
        super(msg, argumentNumber);
    }
    public NameCityException(String msg, Throwable cause, int argumentNumber){super(msg, cause, argumentNumber);}
}
