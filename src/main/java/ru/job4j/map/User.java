package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && name.equals(user.name) && birthday.equals(user.birthday);
    }

   @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birtday = Calendar.getInstance();
        User u1 = new User("Ivan", 7, birtday);
        int hashCode1 = u1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        User u2 = new User("Ivan", 7, birtday);
        int hashCode2 = u2.hashCode();
        int hash2 = hashCode2 ^ (hashCode1 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(u1, new Object());
        map.put(u2, new Object());
        map.forEach((k, v) -> System.out.println(k + " = " + v));
        System.out.printf("u1 - хэш-код: %s, хэш: %s, бакет: %s", hashCode1, hash1, bucket1);
        System.out.println();
        System.out.printf("u2 - хэш-код: %s, хэш: %s, бакет: %s", hashCode2, hash2, bucket2);
    }
}