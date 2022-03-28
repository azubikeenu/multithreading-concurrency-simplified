package com.azubike.ellipsis.concurrency_packages;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class Student implements Comparable<Student> {
    private String name;
    private int rank;

    Student(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    @Override
    public String toString() {
        return String.format("name :%s and rank : %d ", name, rank);
    }

    // this defines the priority order --- added in asc order by ranks
    @Override
    public int compareTo(Student o) {
        return Integer.compare(rank, o.rank);
    }

}

class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getRank(), o2.getRank());
    }
}

public class PriorityBlockingQueueExample {
    public static void main(String[] args) {
        //BlockingQueue<Student> students = new PriorityBlockingQueue<>();
        BlockingQueue<Student> students = new PriorityBlockingQueue<>(6, new StudentComparator());
        students.add(new Student("d", 57));
        students.add(new Student("a", 1));
        students.add(new Student("b", 2));
        students.add(new Student("c", 3));

        System.out.println(students.poll());
        System.out.println(students.poll());
        System.out.println(students.poll());


    }
}
