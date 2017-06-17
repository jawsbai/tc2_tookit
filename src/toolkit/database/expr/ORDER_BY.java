package toolkit.database.expr;

import toolkit.database.FieldDefined;

public class ORDER_BY implements Expression {
    private FieldDefined defined;
    private String sorting;

    public ORDER_BY() {
    }

    public ORDER_BY(FieldDefined defined) {
        this(defined, "asc");
    }

    public ORDER_BY(FieldDefined defined, String sorting) {
        this.defined = defined;
        this.sorting = sorting;
    }

    @Override
    public String toSQL() {
        if (defined == null) {
            return "";
        }
        return "order by `" + defined.name + "` " + sorting;
    }
}
