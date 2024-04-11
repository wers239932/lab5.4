package filework;

import objectSpace.City;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

public class Storage<T extends City> extends ArrayList<T> {
    private final Date creationDate;
    public Storage(Collection<T> collection)
    {
        super(collection);
        this.creationDate=new Date();
    }

    public Date getCreationDate() {
        return creationDate;
    }
    public Storage()
    {
        super();
        this.creationDate=new Date();
    }
    static class Comparater implements Comparator<City>
    {
        public int compare(City city1, City city2)
        {
            return city1.compareTo(city2);
        }
    }
    public void sort()
    {
        Comparator<City> comparator= new Comparater();
        this.sort(comparator);
    }
}
