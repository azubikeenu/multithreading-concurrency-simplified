package com.azubike.ellipsis.single_threaded_app;

class Task {
    void doTask() {
        for (int i = 0; i < 1500; i++) {
            System.out.print("T");
        }
        System.out.println("\n");
    }


}

public class Main {
    public static void main(String[] args) {

        new Thread(() -> {
            new Task().doTask();
        }).start();


        for (int i = 0; i < 1500; i++) {
            System.out.print("M");
        }





    }
}
