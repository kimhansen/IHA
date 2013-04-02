package com.webresponsive.ihahabits.vo;

public class HabitVO
{
    private String name;

    private boolean isDone;

    private double score;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isDone()
    {
        return isDone;
    }

    public void setDone(boolean isDone)
    {
        this.isDone = isDone;
    }

    public double getScore()
    {
        return score;
    }

    public void setScore(double score)
    {
        this.score = score;
    }
}
