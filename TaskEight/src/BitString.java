class BitString {
    int length;
    long bits;

    BitString() {
    }

    BitString(int len, long bits) {
        length = len;
        this.bits = bits;
    }

    BitString(BitString s) {
        length = s.length;
        bits = s.bits;
    }
    BitString(int len, byte b){
        this.length = len;
        this.bits = convertByte(b, len);
    }
    public long convertByte(byte b, int length){
        long temp = 0;
        for(long i = 1<<length-1; i != 0; i >>= 1){
            if((b & i) == 0){
                temp = (temp << 1);
            }
            else temp = ((temp << 1) | 1);
        }
        return temp;
    }


    static BitString concat(BitString s1, BitString s2) {
        BitString ny = new BitString();
        ny.length = s1.length + s2.length;
        if (ny.length > 64) {
            System.out.println("For lang bitstreng, gÃ¥r ikke!");
            return null;
        }
        ny.bits = s2.bits | (s1.bits << s2.length);
        return ny;
    }
    BitString plukkut(int antall, int posisjon) {
        if (posisjon < 0 || antall < 0 || posisjon + antall > length) {
            System.out.println("Umulig kombinasjon av posisjon og antall");
            return null;
        }
        BitString ny = new BitString();
        ny.length = antall;
        long maske = (1L << (length - posisjon)) - 1;
        int forskyving = length - antall - posisjon;
        ny.bits = (bits & maske) >> forskyving;
        return ny;
    }

    public void addZero() {
        this.bits = (this.bits << 1);
        this.length++;
    }

    public void addOne() {
        this.bits = ((this.bits << 1) | 1);
        this.length++;
    }

    public void remove() {
        this.bits = (bits >> 1);
        this.length--;
    }

    String skrivut() {
        String s = "";
        for (long testbit = 1L << (length - 1); testbit != 0; testbit >>= 1) {
            s += ((bits & testbit) == 0) ? "0" : "1";
        }
        return s;
    }

}
