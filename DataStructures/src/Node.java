public class Node{
    private Object data;
    private Node pointer;

    public Object getData() {
        return data;
    }

    public void setData(Object object) {
        this.data = object;
    }

    public Node getPointer() {
        return pointer;
    }

    public void setPointer(Node next) {
        this.pointer = next;
    }
}
