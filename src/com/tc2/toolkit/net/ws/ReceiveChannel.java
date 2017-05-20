package com.tc2.toolkit.net.ws;

import com.tc2.toolkit.bytearray.BoundedByteArray;
import com.tc2.toolkit.print.Console;

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

    private Node _first;
    private Node _last;
    private int _length = 0;

    public ReceiveChannel() {
    }

    public int length() {
        return _length;
    }

    public void receive(byte[] bytes, int len) {
        Node node = new Node();
        node.off = 0;
        node.len = len;
        node.bytes = bytes;
        _length += len;

        if (_first == null) {
            _first = node;
        } else {
            _last.next = node;
        }
        _last = node;
    }

    public BoundedByteArray read(int len) {
        if (_first == null) {
            return null;
        }

        if (_first.realLen() >= len) {
            BoundedByteArray b = new BoundedByteArray(_first.bytes, _first.off, len);
            _first.off += len;
            trim();
            return b;
        }

        Node node = _first;
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

        Node node = _first;
        while (node != null) {
            int realLen = node.realLen();
            length += realLen;
            if (first == null && realLen > 0) {
                first = node;
            }
            node = node.next;
        }

        _first = first;
        _length = length;
        if (length == 0) {
            _last = null;
        }

        node = _first;
        while (node != null) {
//            Console.log("trim", node.off, node.len);
            node = node.next;
        }
    }

    public byte getByte(int off) {
        Node node = _first;
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
