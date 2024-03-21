

public class DoubleLinkedList {

    //внутренний класс узла
    private class Node {
        Object data;
        Node next;
        Node prev;

        public Node(Object data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node [data=" + data + ", next=" + next + ", prev=" + prev + "]";
        }
    }

    private final Node head;
    private final Node tail;
    private int length = 0;

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.addFirst(2);
        list.addFirst(3);
        System.out.println(list);
        list.insertByIndex(1, 6);
        System.out.println(list);
    }

    public DoubleLinkedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Object value) {
        Node addNode = new Node(value, head.next, head);
        head.next.prev = addNode;
        head.next = addNode;
        length++;
    }

    public void addLast(Object value) {
        Node addNode = new Node(value, tail, tail.prev);
        tail.prev.next = addNode;
        tail.prev = addNode;
        length++;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    public void removeFirst() {
        if (isEmpty()) {
            return;
        }
        Node removeNode = head.next;
        head.next = removeNode.next;
        removeNode.next.prev = head;
        removeNode.next = null;
        removeNode.prev = null;
        length++;
    }

    public void removeLast() {
        if (isEmpty()) {
            return;
        }
        Node removeNode = tail.prev;
        tail.prev = removeNode.prev;
        removeNode.prev.next = tail;
        removeNode.next = null;
        removeNode.prev = null;
        length--;
    }

    //Вспомогательная функция для получения узла по индексу
    private Node getNodeByIndex(int index) {
        Node resultNode;
        if (index < length / 2) {
            int nodeIndex = 0;
            resultNode = head.next;
            while (nodeIndex != index) {
                resultNode = resultNode.next;
                nodeIndex++;
            }
        } else {
            int nodeIndex = length - 1;
            resultNode = tail.prev;
            while (nodeIndex != index) {
                resultNode = resultNode.prev;
                nodeIndex--;
            }
        }
        return resultNode;
    }

    public Object getByIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node resultNode = getNodeByIndex(index);
        return resultNode.data;
    }

    public void removeByIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node resultNode = getNodeByIndex(index);
        resultNode.prev.next = resultNode.next;
        resultNode.next.prev = resultNode.prev;
        resultNode.next = null;
        resultNode.prev = null;
        length--;
    }

    public void insertByIndex(int index, Object value) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node resultNode = getNodeByIndex(index);
        Node addNode = new Node(value, resultNode, resultNode.prev);
        resultNode.prev.next = addNode;
        resultNode.prev = addNode;
        length++;
    }

    public void setByIndex(int index, Object value) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node resultNode = getNodeByIndex(index);
        resultNode.data = value;
    }

    public long getLength() {
        long length = 0;
        Node currentNode = head.next;
        while (currentNode != tail) {
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }

    public void clear() {
        while (length > 0) {
            removeFirst();
        }
    }

    @Override
    public String toString() {
        String result = "[";
        Node currentNode = head.next;
        while (currentNode != tail) {
            result += currentNode.data + " ";
            currentNode = currentNode.next;
        }
        return result += "]";
    }
}