package StorageInterface;

import objectSpace.City;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public interface StorageInterface {
    public ArrayList getStorage();
    public Date getCreationDate();
    public void setStorsge(ArrayList storage);
    public void add(City city);
    public void remove(City city);
    public void clear();
    public void save() throws IOException;
    public void load() throws IOException;
}
