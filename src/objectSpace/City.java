package objectSpace;

import objectSpace.exceptions.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Random;

/**
 * Класс город, объектами которого мы манипулируем
 */

public class City implements Comparable<City>{

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**
     * название
     */
    private String name; //Поле не может быть null, Строка не может быть пустой
    /**
     * @see Coordinates
     */
    private Coordinates coordinates; //Поле не может быть null
    /**
     * дата создания объекта
     */
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /**
     * площадь города
     */
    private Long area; //Значение поля должно быть больше 0, Поле не может быть null
    /**
     * население
     */
    private int population; //Значение поля должно быть больше 0
    /**
     * высота над уровнем моря, должно быть больше 0
     */
    private double metersAboveSeaLevel;
    /**
     * наличие столицы
     */
    private Boolean capital; //Поле может быть null
    /**
     * carCode, некоторое поле, больше 0, меньше 1001
     */
    private Long carCode; //Значение поля должно быть больше 0, Максимальное значение поля: 1000, Поле может быть null
    /**
     * @see Government
     */
    private Government government; //Поле может быть null
    /**
     * @see Human
     */
    private Human governor; //Поле может быть null
    private static final Random randonGenerator = new Random();
    public City(String name, Coordinates coordinates, Long area, int population, double metersAboveSeaLevel, Boolean capital, Long carCode, Government government, Human governor)
    {
        this.id = randonGenerator.nextInt(Integer.MAX_VALUE) + 1;
        this.name=name;
        Clock clock=Clock.system(ZoneId.of("Europe/Moscow"));
        this.creationDate= ZonedDateTime.now(clock);
        this.area=area;
        this.government=government;
        this.governor=governor;
        this.capital=capital;
        this.carCode=carCode;
        this.metersAboveSeaLevel= metersAboveSeaLevel;
        this.population=population;
        this.coordinates=coordinates;
    }
    public City(int id) {this.id=id;}

    public void setArea(Long area) {
        this.area = area;
    }

    public Boolean getCapital() {
        return capital;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Long getArea() {
        return area;
    }

    public Government getGovernment() {
        return government;
    }

    public Human getGovernor() {
        return governor;
    }

    public int getId() {
        return id;
    }

    public Long getCarCode() {
        return carCode;
    }

    public Double getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public long getPopulation() {
        return population;
    }

    public String getName() {
        return name;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public void setCarCode(Long carCode) {
        this.carCode = carCode;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMetersAboveSeaLevel(Long metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        if(Objects.isNull(this.government))
            return this.id +","+this.name+","+this.coordinates.toString()+","+
                this.creationDate +","+ this.area
                +","+ this.population +","+ this.metersAboveSeaLevel
                +","+ this.capital +","
                + this.carCode +","+"null"+","
                +this.governor.toString();
        else
            return this.id +","+this.name+","+this.coordinates.toString()+","+
                    this.creationDate +","+ this.area
                    +","+ this.population +","+ this.metersAboveSeaLevel
                    +","+ this.capital +","
                    + this.carCode +","+this.government.toString()+","
                    +this.governor.toString();
    }

    @Override
    public int compareTo(City o) {
        int diff=this.area.compareTo(o.getArea());
        if(diff==0)
        {
            diff=id-o.getId();
        }
        return diff;
    }

    /**
     * парсит набор строк в город
     * @param args
     * @throws CoordinatesException
     * @throws NameCityException
     * @throws AreaException
     * @throws PopulationException
     * @throws HeightException
     * @throws CapitalException
     * @throws CarCodeException
     * @throws GovernmentException
     * @throws GovernorException
     */
    public static City parseCity(String[] args) throws CoordinatesException, NameCityException, AreaException, PopulationException, HeightException, CapitalException, CarCodeException, GovernmentException, GovernorException {
        if(args.length!=12)
        {
            throw new IncorrectDataExceptoin("некорректное количество данных");
        }
        else
        {
            try
            {
                Integer id;
                try {
                    id=Integer.parseInt(args[0].trim());
                }
                catch (Exception e)
                {
                    throw new IncorrectDataExceptoin("id cannot be understood");
                }


                String name = args[1].trim();
                Coordinates coordinates;
                ZonedDateTime creationDate;
                Long area;
                Integer population;
                Double metersAboveSeaLevel;
                Boolean capital;
                Long carCode;
                Government government;
                Human governor;
                if(name=="") throw new NameCityException("имя должно быть строкой, не должно быть null",1);
                try {
                    float x;
                    long y;
                    x= Float.parseFloat(args[2].trim());
                    y=Long.parseLong(args[3].trim());
                    coordinates = new Coordinates(x, y);
                    }
                catch (Exception e) {
                    throw new CoordinatesException("coords быть в формате (long x, Integer y), y не должен быть null", 2);
                }
                try {
                    creationDate=ZonedDateTime.parse(args[4].trim());
                }
                catch (Exception e)
                {
                    throw new AreaException("площадь должна быть не null, должна быть >0", 4);
                }
                try {
                    area = Long.parseLong(args[5].trim());
                    if(area<=0) throw new Exception();
                }
                catch (Exception e)
                {
                    throw new AreaException("площадь должна быть не null, должна быть >0", 4);
                }

                try {
                    population = Integer.parseInt(args[6].trim());
                }
                catch (Exception e)
                {
                    throw new PopulationException("население должно быть числом long или null", 5);
                }
                try {
                    metersAboveSeaLevel = Double.parseDouble(args[7].trim());
                }
                catch (Exception e)
                {
                    throw new HeightException("высота над уровнем моря должна быть числом", 6);
                }
                try {

                    if(!args[8].trim().equals("null"))
                        capital = Boolean.parseBoolean(args[8].trim());
                    else capital = null;
                }
                catch (Exception e)
                {
                    throw new CapitalException("столица должна быть типа boolean", 7);
                }
                try {

                    if(!args[9].trim().equals("null")) {
                        carCode = Long.parseLong(args[9].trim());
                        if (carCode <= 0 || carCode > 1000) throw new Exception();
                    }
                    else carCode=null;
                }
                catch (Exception e)
                {
                    throw new CarCodeException("carCode должен быть числом, больше 0 и не больше 1000", 8);
                }
                try {

                    if(!args[10].trim().equals("null"))
                        government = Government.valueOf(args[10].trim());
                    else government=null;
                }
                catch (Exception e)
                {
                    throw new GovernmentException("правительство должно быть null, KLEPTOCRACY, CORPORATOCRACY или PATRIARCHY", 9);
                }
                try {
                    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    governor = new Human(LocalDateTime.parse(args[11].trim()));
                }
                catch (Exception e)
                {
                    throw new GovernorException("правитель должен родиться в легитимный день в формате yyyy-MM-dd HH:mm", 10);
                }

                City city = new City(name,coordinates,area,population,metersAboveSeaLevel,capital,carCode,government,governor);
                city.setCreationDate(creationDate);
                city.setId(id);
                return city;
            }
            catch (Exception e)
            {
                throw new IncorrectDataExceptoin("неверный формат ввода данных", e);
            }
        }
    }
}
