package ch10.jsr349;

public enum CustomerType {
    INDIVIDUAL("I"), CORPORATE("C");

    private String code;

    private CustomerType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
