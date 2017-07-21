/**
 * Created by kparwal on 7/20/17.
 */
public class ReturnValue<T> {
    private String key;
    private T value;

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public ReturnValue(String key, T value) {
        this.key = key;
        this.value = value;
    }
}
