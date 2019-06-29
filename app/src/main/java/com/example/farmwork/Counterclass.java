package com.example.farmwork;

public class Counterclass {



        public Integer myCounter;


        public Counterclass() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

    public Counterclass(Integer myCounter) {
        this.myCounter = myCounter;
    }
}
