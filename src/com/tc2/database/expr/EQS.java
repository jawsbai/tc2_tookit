package com.tc2.database.expr;

public class EQS extends Expressions {
    public EQS(EQ... eqs) {
        super(eqs, ", ");
    }
}
