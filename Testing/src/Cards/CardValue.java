package Cards;

public enum CardValue {
    Narr(0),
    Eins(1),
    Zwei(2),
    Drei(3),
    Vier(4),
    Fünf(5),
    Sechs(6),
    Sieben(7),
    Acht(8),
    Neun(9),
    Zehn(10),
    Elf(11),
    Zwölf(12),
    Dreizehn(13),
    Zauberer(14);

    int index;

    private CardValue(int i){
        this.index = i;
    }

    public int getIndex() {
        return index;
    }
}
