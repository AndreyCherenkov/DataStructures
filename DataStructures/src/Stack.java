import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class Stack{

    private Node head = null;
    private int size = 0;

    public static void main(String[] args) throws InterruptedException{

        final RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtime.getPid());
        System.out.println();
        Thread thread = new Thread(new HardMemorySize());
        thread.setDaemon(true);
        thread.start();
        thread.join();
        while (true){
            thread.sleep(10000);
        }
    }

    public void push(Object element){
        Node node = new Node();
        node.setData(element);
        node.setPointer(head);
        head = node;
        size++;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public Object peek(){
        if(head != null){
            return head.getData();
        }
        return null;
    }

    public Object pop(){
        if(!isEmpty()){
            Object element = head.getData();
            head = head.getPointer();
            size--;
            return element;
        }
        return null;
    }

    public int size(){
        return size;
    }
}

class HardMemorySize implements Runnable{

    Stack stack = new Stack();

    @Override
    public void run() {
        System.out.println("До заполнения: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / Math.pow(2, 20));
        for(int i = 0; i < 100000; i++){
            stack.push(new int[]{1,2,3,4,5,6,7,8,9,0});
        }
        System.out.println("После заполнения: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / Math.pow(2, 20));
        for(int i = 100000; i > 0; i--){
            stack.pop();
        }
        System.gc();
        System.out.println("После удаления: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / Math.pow(2, 20));
    }
}

class EasyMemorySize implements Runnable{

    Stack stack = new Stack();

    @Override
    public void run() {
        System.out.println("До заполнения: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / Math.pow(2, 20));
        for(int i = 0; i < 100000; i++){
            stack.push(1);
        }
        System.out.println("После заполнения: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / Math.pow(2, 20));
        for(int i = 100000; i > 0; i--){
            stack.pop();
        }
        //System.gc();
        System.out.println("После удаления: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / Math.pow(2, 20));
    }
}
