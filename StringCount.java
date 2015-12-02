public class StringCount {
    private String value;
    private int count;

    public StringCount(String value) {
        this.value = value;
        this.count = 1;

        System.out.println(this);
    }

    public String getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        this.count++;
        System.out.println(this);
    }

    public String toString() {
        return String.format("%5d %s", this.count, this.value);
    }
}
