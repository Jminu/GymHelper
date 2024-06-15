package com.example.gymhelper;
/*
* 이곳에는, 운동 정보class
* 운동 이름, 무게, 반복수, count*/
public class WorkOut {
    private String name;
    private int weight;
    private int reps;
    private int sets;
    private int count;

    public WorkOut(String name, int weight, int reps, int sets) {
        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.count = 0; //초기 count값은 0
    }

    public String getName() {
        return this.name;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getReps() {
        return this.reps;
    }

    public int getCount() {
        return this.count;
    }
    public int getSets() {
        return this.sets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWorkInfo() {
        return this.name + " - " + this.weight + "kg - " + this.reps + "reps " + this.sets + "sets";
    }
}
