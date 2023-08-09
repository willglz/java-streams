package fprogramming.finterfaces;

import java.util.function.Function;

public class EmailValidator implements Function<String, Boolean> {
    @Override
    public Boolean apply(String s) {
        return s.contains("@");
    }
}
