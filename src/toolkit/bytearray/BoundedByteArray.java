package toolkit.bytearray;

public class BoundedByteArray {
    public final byte[] bytes;
    public final int off;
    public final int len;

    public BoundedByteArray(byte[] bytes, int off, int len) {
        this.bytes = bytes;
        this.off = off;
        this.len = len;
    }

    public byte getByte(int index) {
        return bytes[index + off];
    }
}
