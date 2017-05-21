package com.tc2.database;

public class TableDefined {
    public final TableName tableName;
    public final FieldDefined[] fields;
    public final String primaryKey;

    public TableDefined(TableName tableName, String primaryKey, FieldDefined... fields) {
        this.tableName = tableName;
        this.fields = fields;
        this.primaryKey = primaryKey;
    }

    public String createSQL() {
        StringBuilder sb = new StringBuilder();
        for (FieldDefined field : fields) {
            sb.append(field.fieldSQL()).append(",\n");
        }
        return "CREATE TABLE `" + tableName.name + "` (\n" +
                sb +
                "PRIMARY KEY (`" + primaryKey + "`)\n" +
                ")\n" +
                "ENGINE=InnoDB DEFAULT\n" +
                "CHARSET=utf8\n" +
                "ROW_FORMAT=COMPACT";
    }

    public String dropSQL() {
        return "DROP TABLE IF EXISTS `" + tableName.name + "`";
    }
}
