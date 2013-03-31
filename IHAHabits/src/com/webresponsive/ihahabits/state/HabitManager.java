package com.webresponsive.ihahabits.state;

import java.util.ArrayList;

import com.webresponsive.ihahabits.vo.HabitsVO;

/**
 * Habit manager singleton class.
 */
public class HabitManager
{
    // Singleton
    private static HabitManager instance = new HabitManager();

    // Habits holder
    private ArrayList<HabitsVO> habits = null;

    private OnHabitsUpdatedListener onHabitsUpdatedListener;

    // Listener for habits updated
    public interface OnHabitsUpdatedListener
    {
        public void onHabitsUpdated();
    }

    public void setOnHabitsUpdatedListener(OnHabitsUpdatedListener onHabitsUpdatedListener)
    {
        this.onHabitsUpdatedListener = onHabitsUpdatedListener;
    }

    /**
     * Return the only instance of the habit manager class.
     */
    public static HabitManager getInstance()
    {
        return instance;
    }

    public ArrayList<HabitsVO> getHabits()
    {
        return habits;
    }

    /**
     * Called to set habits. This will invoke the listener.
     */
    public void setHabits(ArrayList<HabitsVO> habits)
    {
        this.habits = habits;

        if (onHabitsUpdatedListener != null)
        {
            onHabitsUpdatedListener.onHabitsUpdated();
        }
    }

    public HabitsVO getHabits(int position)
    {
        return habits.get(position);
    }
}
