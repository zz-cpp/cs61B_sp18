public class quick {


    public static void main(String[] args) {
        StudentArrayDeque<Integer> sd = new StudentArrayDeque<>();

        int res ;
        int count;

        sd.addFirst(0);
        sd.addLast(2);
        sd.addLast(3);
        sd.addFirst(4);
        sd.addFirst(5);
        sd.addFirst(6);
        sd.addLast(8);
        sd.addFirst(9);
        sd.addLast(12);
        sd.addFirst(13);
        sd.addLast(14);
        sd.addLast(17);
        sd.addFirst(18);
        sd.addLast(19);
        count = sd.size();
        res = sd.removeLast();
        count = sd.size();
        res = sd.removeFirst();
        res =sd.removeFirst();
        count = sd.size();
        res =sd.removeLast();
        res =sd.removeFirst();
        res =sd.removeFirst();
        res =sd.removeLast();
        res =sd.removeFirst();
        res =sd.removeFirst();
        res =sd.removeLast();
    }
}
