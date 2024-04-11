package objectSpace;

import java.time.LocalDateTime;

/**
 * Класс всего полезного о человеке - дата рождения
 */

public class Human {
    private final LocalDateTime birthday;

    public Human(LocalDateTime date)
    {
        this.birthday=date;
    }
    @Override
    public String toString()
    {
        return this.birthday.toString();
    }
}
