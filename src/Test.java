import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        HashSet<User> hs = new HashSet<>();
        User u1 = new User("1","1","1");
        User u2 = new User("2","2","2");
        User u3 = new User("3","3","3");
        hs.add(u1);
        hs.add(u2);
        hs.add(u3);
        System.out.println(hs.contains(new User("1","1","1")));
    }
}
