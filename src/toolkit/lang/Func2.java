package toolkit.lang;

public interface Func2<ARG1, ARG2, RESULT> {
    RESULT invoke(ARG1 arg1, ARG2 arg2);
}