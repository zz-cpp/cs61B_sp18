
public class LinkedListDeque<T> {

    private  class Node{
        Node prev;
        T item;
        Node next;

        private Node(Node prev, T item, Node next ) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        private Node() {
            this.item = null;
            this.prev = null;
            this.next = null;
        }

        private Node( T item) {
            this.prev = null;
            this.item = item;
            this.next = null;
        }
    }

    private class DoublyLinkedList{

        private Node sentinel;
        int size;

        private DoublyLinkedList(){
            sentinel = new Node();
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
            size = 0;
        }

        private DoublyLinkedList(T item){
            sentinel = new Node();
            Node firstItem = new Node(item);
            sentinel.next = firstItem;
            firstItem.prev = sentinel;
            firstItem.next = sentinel;
            sentinel.prev = firstItem;
            size = 1;
        }

        private void addLast(T x){
            Node newObj = new Node(x);
            sentinel.prev.next = newObj;
            newObj.prev = sentinel.prev;
            newObj.next = sentinel;
            sentinel.prev = newObj;
            size++;
        }

        private void addFirst(T x){
            Node newObj = new Node(x);
            newObj.next = sentinel.next;
            newObj.prev = sentinel;
            sentinel.next.prev = newObj;
            sentinel.next = newObj;
            size++;
        }

        private boolean isEmpty() {
            if(size == 0){
                return true;
            }
            return  false;
        }

        public int size(){
            return size;
        }

        public void printDList(){
            Node item = sentinel.next;
            while(item != null && item != sentinel){
                System.out.print(item.item + " ");
                item = item.next;
            }
        }

        public T removeFirst(){
            if (sentinel.next == sentinel){
                return null;
            }

            Node first = sentinel.next;
            sentinel.next = first.next;
            first.next.prev = sentinel;
            first.next = null;
            first.prev = null;
            size--;

            return first.item;
        }

        public T removeLast(){

            if(sentinel.prev == sentinel ){
                return null;
            }

            Node last = sentinel.prev;
            last.prev.next = sentinel;
            sentinel.prev = last.prev;
            last.next = null;
            last.prev = null;

            return last.item;
        }

        public T get(int index){

            if(index == size - 1){
                return sentinel.prev.item;
            }

            if(index == 0){
                return sentinel.next.item;
            }

            Node head = sentinel.next;

            while(index > 0){

                if(head == sentinel){
                    return null;
                }

                head = head.next;
                index--;
            }

            return head.item;
        }
    }

    private DoublyLinkedList sentinel;


    public LinkedListDeque() {
        sentinel = new DoublyLinkedList();
    }

    public void addFirst(T item){
        sentinel.addFirst(item);
    }

    public void addLast(T item){
        sentinel.addLast(item);
    }

    public boolean isEmpty(){
        return sentinel.isEmpty();
    }

    public int size(){
        return sentinel.size();
    }

    public void printDeque(){
         sentinel.printDList();
    }

    public T removeFirst(){
      return   sentinel.removeFirst();
    }

    public T removeLast(){
        return sentinel.removeLast();
    }

    public T get(int index){
        return sentinel.get(index);
    }
}
