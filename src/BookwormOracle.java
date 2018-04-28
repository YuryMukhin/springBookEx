public class BookwormOracle implements Oracle {

    private Encyclopedia enc;

    public void setEnc(Encyclopedia enc) {
        this.enc = enc;
    }

    @Override
    public String defineMeaningOfLife() {
        return "you waste your time";
    }
}
