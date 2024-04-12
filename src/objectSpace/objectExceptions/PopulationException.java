package objectSpace.objectExceptions;
/**
 * Класс расширяющий ArgumentCityException означающий ошибку с населением
 * @author vladimir
 */
public class PopulationException extends ArgumentCityException{

    public PopulationException(String msg){
        super(msg);
    }

    public PopulationException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }

}
