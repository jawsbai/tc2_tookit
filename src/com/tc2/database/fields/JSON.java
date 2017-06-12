package com.tc2.database.fields;

import com.tc2.database.expr.EQ;

public class JSON extends TEXT {
    public JSON(String name) {
        super(name, true);
    }

    public EQ eqJSON(Object value) {
        return new EQ(this, com.alibaba.fastjson.JSON.toJSONString(value));
    }
}
