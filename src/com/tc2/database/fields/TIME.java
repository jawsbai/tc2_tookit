package com.tc2.database.fields;

import com.tc2.database.FieldDefined;
import com.tc2.database.expr.EQ;
import com.tc2.toolkit.helper.TimeHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TIME extends VARCHAR {
    public TIME(String name) {
        super(name, true, 50);
    }

    public EQ eq(int value) {
        return new EQ(this, String.valueOf(value));
    }

    public EQ eqNow() {
        return new EQ(this, String.valueOf(TimeHelper.now()));
    }
}
