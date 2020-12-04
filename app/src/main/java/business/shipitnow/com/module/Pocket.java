package business.shipitnow.com.module;

public class Pocket {

    private String Name;
    private String Description;
    private String id;
    private String Date;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Pocket{" +
                "Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", id='" + id + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
