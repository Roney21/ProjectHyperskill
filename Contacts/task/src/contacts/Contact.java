package contacts;

import java.time.LocalDateTime;

public interface Contact {
    String toString();
    String getTimeCreated();
    void setTimeEdit(LocalDateTime editedTime);
    String getTimeEdit();

    String getFullname();
}
