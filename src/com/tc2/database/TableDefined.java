package com.tc2.database;

public class TableDefined {
    public final TableName tableName;
    public final FieldDefined primaryKey;
    public final FieldDefined[] fields;

    public TableDefined(TableName tableName, FieldDefined primaryKey, FieldDefined... fields) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.fields = new FieldDefined[fields.length + 1];
        this.fields[0] = primaryKey;
        for (int i = 0; i < fields.length; i++) {
            this.fields[i + 1] = fields[i];
        }
    }

    public String createSQL() {
        StringBuilder sb = new StringBuilder();
        for (FieldDefined field : fields) {
            sb.append(field.fieldSQL()).append(",\n");
        }
        return "CREATE TABLE `" + tableName.name + "` (\n" +
                sb +
                "PRIMARY KEY (`" + primaryKey.name + "`)\n" +
                ")\n" +
                "ENGINE=InnoDB DEFAULT\n" +
                "CHARSET=utf8\n" +
                "ROW_FORMAT=COMPACT";
    }

    public String dropSQL() {
        return "DROP TABLE IF EXISTS `" + tableName.name + "`";
    }
}
