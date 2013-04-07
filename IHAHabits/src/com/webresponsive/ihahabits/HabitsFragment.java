package com.webresponsive.ihahabits;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.webresponsive.ihahabits.adapters.HabitListAdapter;
import com.webresponsive.ihahabits.cloud.HabitService;
import com.webresponsive.ihahabits.state.HabitManager;
import com.webresponsive.ihahabits.state.HabitManager.OnHabitsUpdatedListener;
import com.webresponsive.ihahabits.util.DateHelper;
import com.webresponsive.ihahabits.vo.HabitVO;
import com.webresponsive.ihahabits.vo.HabitsVO;

public class HabitsFragment extends Fragment implements OnHabitsUpdatedListener
{
    public static String NAME = "";

    private View view;
    private MainActivity mainActivity;

    private ArrayList<HabitVO> habits;
    private HabitListAdapter habitListAdapter;
    private HabitsVO todayHabits;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.habits_fragment, container, false);
        this.view = view;

        setupHabitList();
        
        getTodaysHabits();

        HabitManager.getInstance().setOnHabitsUpdatedListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mainActivity = (MainActivity) activity;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement " + MainActivity.class.getName());
        }
    }

    private void getTodaysHabits()
    {
        HabitService.getHabits(DateHelper.getTodayString(), null);

        Log.d("MainActivity", "getting habits for today = " + DateHelper.getTodayString());
        
        TextView date = (TextView) view.findViewById(R.id.habitDate);
        date.setText(DateHelper.getTodayString());
    }

    private void setupHabitList()
    {
        ListView listView = (ListView) view.findViewById(R.id.habitsList);

        habitListAdapter = new HabitListAdapter(mainActivity);
           
        habits = getHabits();

        habitListAdapter.setHabits(habits);
        
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                HabitVO habitVO = habits.get(position);
                habitVO.setDone(!habitVO.isDone());
                saveHabits();
            }
        });
        listView.setAdapter(habitListAdapter);
    }

    private void saveHabits()
    {
        HabitsVO habitsVO = new HabitsVO();
        
        habitsVO.setJournalIsDone(habits.get(0).isDone());
        habitsVO.setGratitudeIsDone(habits.get(1).isDone());
        habitsVO.setIdeaIsDone(habits.get(2).isDone());
        habitsVO.setKindnessIsDone(habits.get(3).isDone());
        habitsVO.setLearnIsDone(habits.get(4).isDone());
        habitsVO.setFeelIsDone(habits.get(5).isDone());

        String habitsObjectId = (todayHabits != null) ? todayHabits.getObjectId() : null;
        HabitService.saveHabits(habitsVO, DateHelper.getTodayString(), habitsObjectId);

        Log.d("MainActivity", "saving habitsVO = " + habitsVO);
        Log.d("MainActivity", "date = " + DateHelper.getTodayString());
    }

    private ArrayList<HabitVO> getHabits()
    {
        ArrayList<HabitVO> habits = new ArrayList<HabitVO>();

        HabitVO habitVO1 = new HabitVO();
        habitVO1.setDone(false);
        habitVO1.setName("Journal");
        habits.add(habitVO1);
        HabitVO habitVO2 = new HabitVO();
        habitVO2.setDone(false);
        habitVO2.setName("Gratitude");
        habits.add(habitVO2);
        HabitVO habitVO3 = new HabitVO();
        habitVO3.setDone(false);
        habitVO3.setName("Idea");
        habits.add(habitVO3);
        HabitVO habitVO4 = new HabitVO();
        habitVO4.setDone(false);
        habitVO4.setName("Kindness");
        habits.add(habitVO4);
        HabitVO habitVO5 = new HabitVO();
        habitVO5.setDone(false);
        habitVO5.setName("Learn");
        habits.add(habitVO5);
        HabitVO habitVO6 = new HabitVO();
        habitVO6.setDone(false);
        habitVO6.setName("Feel");
        habits.add(habitVO6);
        
        return habits;
    }

    @Override
    public void onHabitsUpdated()
    {
        ArrayList<HabitsVO> habitsVO = HabitManager.getInstance().getHabits();

        Log.d("MainActivity", "Got habitsVO = " + habitsVO);
        if ((habitsVO != null) && (habitsVO.size() > 0))
        {
            todayHabits = habitsVO.get(0);

            habits.get(0).setDone(todayHabits.isJournalIsDone());
            habits.get(1).setDone(todayHabits.isGratitudeIsDone());
            habits.get(2).setDone(todayHabits.isIdeaIsDone());
            habits.get(3).setDone(todayHabits.isKindnessIsDone());
            habits.get(4).setDone(todayHabits.isLearnIsDone());
            habits.get(5).setDone(todayHabits.isFeelIsDone());
            
            habitListAdapter.setHabits(habits);
            habitListAdapter.notifyDataSetChanged();
        }
    }
}
