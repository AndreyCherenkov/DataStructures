public class Queue {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void push(Object obj) {
        // Сразу создаем вспомогательный объект и помещаем новый элемент в него
        Node ob = new Node();
        ob.setData(obj);
        // Если очередь пустая - в ней еще нет элементов
        if (head == null) {
            // Теперь наша голова указывает на наш первый элемент
            head = ob;
        } else {
            // Если это не первый элемент, то надо, чтобы последний элемент в очереди
            // указывал на вновь прибывший элемент
            tail.setPointer(ob);
        }
        // И в любом случае нам надо наш "хвост" переместить на новый элемент
        // Если это первый элемент, то "голова" и "хвост" будут указывать на один и тот же элемент
        tail = ob;
        // Увеличиваем размер нашей очереди
        size++;
    }

    public Object pop() {
        // Если у нас нет элементов, то возвращаем null
        if (size == 0) {
            return null;
        }
        // Получаем наш объект из вспомогательного класса из "головы"
        Object obj = head.getData();
        head = head.getPointer();

        if (head == null) {
            tail = null;
        }

        size--;

        return obj;
    }

    public int size() {
        return size;
    }

    public Object peek(){
        if(head != null){
            return head.getData();
        }
        return null;
    }

    public static void main(String[] args) {
        Queue queue = new Queue();

        for(int i = 0; i<10; i++) {
            queue.push(i);
        }
        while(queue.size() > 0) {
            System.out.println(queue.pop());
        }
    }
}