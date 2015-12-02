import java.util.ArrayList;

public class StringSet extends ArrayList<StringCount> {

    public boolean add(String s) {
        if (s.isEmpty()) {
            return false;
        }

        for (StringCount stringCount : this) {
            if (stringCount.getValue().equals(s)) {
                stringCount.increaseCount();
                return true;
            }
        }
        return super.add(new StringCount(s));
    }
}
