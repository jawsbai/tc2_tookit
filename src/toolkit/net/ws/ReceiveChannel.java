package toolkit.net.ws;

import toolkit.bytearray.BoundedByteArray;

public class ReceiveChannel {
    private class Node {
        int off;
        int len;
        byte[] bytes;
        Node next;

        int realLen() {
            return len - off;
        }
    }

    private Node first;
    private Node last;
    private int length = 0;

    public ReceiveChannel() {
    }

    public int length() {
        return length;
    }

    public void receive(byte[] bytes, int len) {
        Node node = new Node();
        node.off = 0;
        node.len = len;
        node.bytes = bytes;
        length += len;

        if (first == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
    }

    public BoundedByteArray read(int len) {
        if (first == null) {
            return null;
        }

        if (first.realLen() >= len) {
            BoundedByteArray b = new BoundedByteArray(first.bytes, first.off, len);
            first.off += len;
            trim();
            return b;
        }

        Node node = first;
        int total = 0;
        int index = 0;
        byte[] bytes = new byte[len];
        while (node != null) {
            int realLen = node.realLen();
            if (len >= total) {
                int l = len - total;
                if (l > realLen) {
                    System.arraycopy(node.bytes, node.off, bytes, index, realLen);
                    index += realLen;
                    node.off = node.len;
                } else {
                    System.arraycopy(node.bytes, node.off, bytes, index, l);
                    index += l;
                    node.off += l;
                }
            }
            if (index == len) {
                break;
            }
            total += realLen;
            node = node.next;
        }

        trim();

        return new BoundedByteArray(bytes, 0, bytes.length);
    }

    private void trim() {
        int length = 0;
        Node first = null;

        Node node = this.first;
        while (node != null) {
            int realLen = node.realLen();
            length += realLen;
            if (first == null && realLen > 0) {
                first = node;
            }
            node = node.next;
        }

        this.first = first;
        this.length = length;
        if (length == 0) {
            last = null;
        }

        node = this.first;
        while (node != null) {
//            Console.log("trim", node.off, node.len);
            node = node.next;
        }
    }

    public byte getByte(int off) {
        Node node = first;
        int total = 0;
        while (node != null) {
            int readLen = node.realLen();
            if (readLen + total > off) {
                return node.bytes[off - total];
            }
            total += readLen;
            node = node.next;
        }
        return 0;
    }
}
