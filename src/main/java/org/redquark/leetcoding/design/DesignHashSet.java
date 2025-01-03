package org.redquark.leetcoding.design;

public class DesignHashSet {

    static class MyHashSet {

        private final boolean[] elements;

        public MyHashSet() {
            this.elements = new boolean[1000001];
        }

        public void add(int key) {
            this.elements[key] = true;
        }

        public void remove(int key) {
            this.elements[key] = false;
        }

        public boolean contains(int key) {
            return this.elements[key];
        }
    }

    public static void main(String[] args) {
        final MyHashSet myHashSet = new MyHashSet();

        myHashSet.add(1);
        myHashSet.add(2);
        System.out.println(myHashSet.contains(1));
        System.out.println(myHashSet.contains(3));
        myHashSet.add(2);
        System.out.println(myHashSet.contains(2));
        myHashSet.remove(2);
        System.out.println(myHashSet.contains(2));
    }
}
