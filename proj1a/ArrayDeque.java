public class ArrayDeque<T> {

    private T[] Item;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        Item = (T[]) new Object[8];
        nextFirst = 7;
        nextLast = 0;
    }


    private double useeRate(){
        return (double) size / Item.length;
    }

    private int minusOne(int index,int length) {
        index--;
        if(index < 0 )
            index = length - 1;
        return index;
    }

    private int addOne(int index,int length) {
        index++;
        if(index > length - 1)
            index = 0 ;
        return index;
    }

    private void grow(int capital){
        T a [] = (T[]) new Object[capital];
        int begin = addOne(nextFirst,Item.length);
        int length = Item.length - begin;
        System.arraycopy(Item,begin ,a,0,length);
        System.arraycopy(Item,0 ,a, length,nextLast);
        nextFirst = a.length -1;
        nextLast = nextLast + length;
        Item = a;

    }

    private void lessen(int capital){
        T a [] = (T[]) new Object[capital];
        int begin = addOne(nextFirst,Item.length);
        if(begin < nextLast){
            System.arraycopy(Item,begin,a,0,nextLast - begin);
        } else {
            System.arraycopy(Item,begin,a,0,Item.length - begin);
            System.arraycopy(Item,0, a,Item.length - begin,nextLast);
        }
        nextFirst = a.length -1;
        nextLast = size;
        Item = a;

    }

    public void addFirst(T item){
        if(nextFirst == -2 && nextLast == -2){
            nextFirst = 0;
            nextLast = 1;
        }

        if(size == Item.length){
            grow(Item.length * 2);
        }

        Item[nextFirst] = item;
        nextFirst = minusOne(nextFirst,Item.length);
        size++;
    }

    public void addLast(T item) {
        if(nextFirst == -2 && nextLast == -2){
            nextFirst = Item.length - 1;
            nextLast = 0;
        }
        if(size == Item.length){
            grow(Item.length * 2);
        }

        Item[nextLast] = item;
        nextLast = addOne(nextLast,Item.length);
        size++;
    }

    public T removeFirst(){

        if(size == 0){
            return null;
        }

        nextFirst = addOne(nextFirst,Item.length);
        T first = Item[nextFirst];
        Item[nextFirst] = null;
        size--;

        if(useeRate() <= 0.25 && Item.length > 8){
            lessen(Item.length / 2);
        }

        return first;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        nextLast = minusOne(nextLast,Item.length);
        T last = Item[nextLast];
        Item[nextLast] = null;
        size--;

        if(useeRate() <= 0.25 && Item.length > 8){
            lessen(Item.length / 2);
        }

        return last;
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public T get(int index){
        if(Item[index] == null){
            return null;
        }
        return Item[index];
    }


    public void printDeque(){
        int begin = addOne(nextFirst,Item.length);;
        for (int i = 0; i < size ; i++) {
            System.out.print(Item[begin] + " ");
            begin = addOne(begin,Item.length);
        }
    }

}
