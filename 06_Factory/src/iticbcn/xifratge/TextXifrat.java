package iticbcn.xifratge;

public class TextXifrat {

    byte[] arrayBytes;
    
    public TextXifrat(byte[] arrayBytes) {
        this.arrayBytes = arrayBytes;
    }

    public String toString() {
        return new String(arrayBytes);
    }

    public byte[] getBytes() {
        return arrayBytes;
    }
}
