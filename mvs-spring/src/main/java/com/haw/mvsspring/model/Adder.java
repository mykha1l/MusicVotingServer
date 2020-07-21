package com.haw.mvsspring.model;

//Adder class to use as a java object for the testing request mapping with two integers
public class Adder {
    private int firstInt;
    private int secondInt;

    public void setFirstInt(Integer firstInt)
    {
        this.firstInt = firstInt;
    }
    public void setSecondInt(Integer secondInt)
    {
        this.secondInt = secondInt;
    }

    public Integer getFirstInt()
    {
        return firstInt;
    }

    public Integer getSecondInt()
    {
        return secondInt;
    }
}