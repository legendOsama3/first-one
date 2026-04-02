package Lab03;


public class Lab03_DLL_Driver {
    public static void main(String[] args) {
        DLL<Integer> list1 = new DLL<>();
        for ( int i= 10; i <= 40 ; i= i+10)
            list1.addToTail(i);

        System.out.println("Test Palindrome");

        System.out.println(list1);
        System.out.println("Is Palindrome? " + list1.isPalindrome());  // false

        DLL<Integer> list2 = new DLL<>();
        for ( int i= 10; i <= 40 ; i= i+10) {
            list2.addToTail(i);
            list2.addToHead(i);
        }
        System.out.println(list2);
        System.out.println("Is Palindrome? " + list2.isPalindrome());  // true

        System.out.println("Test insertAlternate");
        DLL<Integer> a = new DLL<>();
        a.addToTail(1);
        a.addToTail(2);
        a.addToTail(3);

        DLL<Integer> b = new DLL<>();
        b.addToTail(4);
        b.addToTail(5);
        b.addToTail(6);

        System.out.println(a);
        System.out.println(b);
        a.insertAlternate(b);
        System.out.println(a);



        System.out.println("Test deleteAllOccurances");
        DLL<Integer> c = new DLL<>();
        c.addToTail(10);
        c.addToTail(20);
        c.addToTail(30);
        c.addToTail(20);

        System.out.println(c);
        c.deleteAllOccurances(20);
        System.out.println(c);





    }
}
