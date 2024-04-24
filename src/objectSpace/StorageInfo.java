package objectSpace;

import java.util.Date;

public class StorageInfo {
    private Date creationDate;
    private int size;
    public StorageInfo(int size, Date creationDate) {
        this.size = size;
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getSize() {
        return size;
    }
}
