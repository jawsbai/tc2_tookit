package toolkit.database;

public class TableName {
    public final String name;

    public TableName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
