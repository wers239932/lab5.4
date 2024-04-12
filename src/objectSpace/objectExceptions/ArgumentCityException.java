package objectSpace.objectExceptions;

/**
 * Абстрактный класс обибок при вводе неверных типов данных при создании объекта типа City
 * @author vladimir
 */

public abstract class ArgumentCityException extends Exception{
    public int argumentNumber;
    public ArgumentCityException(String msg){
        super(msg);
    }

    public ArgumentCityException(String msg, int argumentNumber){
        super(msg);
        this.argumentNumber = argumentNumber;
    }
    public ArgumentCityException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause);
        this.argumentNumber = argumentNumber;
    }
}
