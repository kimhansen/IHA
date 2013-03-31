package com.webresponsive.ihahabits;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.Parse;
import com.webresponsive.ihahabits.adapters.HabitListAdapter;
import com.webresponsive.ihahabits.cloud.HabitService;
import com.webresponsive.ihahabits.state.HabitManager;
import com.webresponsive.ihahabits.state.HabitManager.OnHabitsUpdatedListener;
import com.webresponsive.ihahabits.util.DateHelper;
import com.webresponsive.ihahabits.vo.HabitVO;
import com.webresponsive.ihahabits.vo.HabitsVO;

public class MainActivity extends Activity implements OnHabitsUpdatedListener
{
    private ArrayList<HabitVO> habits;
    private HabitListAdapter habitListAdapter;
    private HabitsVO todayHabits;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setupParse();

        setupHabitList();
        
        getTodaysHabits();
        
        HabitManager.getInstance().setOnHabitsUpdatedListener(this);
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

    private void getTodaysHabits()
    {
        Date today = new Date();
        HabitService.getHabits(DateHelper.formatDate(today));

        Log.d("MainActivity", "getting habits for today = " + DateHelper.formatDate(today));
        
        TextView date = (TextView) findViewById(R.id.habitDate);
        date.setText(DateHelper.formatDate(today));
    }

    private void setupHabitList()
    {
        ListView listView = (ListView) findViewById(R.id.habitsList);

        habitListAdapter = new HabitListAdapter(this);
           
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
        habitsVO.setDate(Calendar.getInstance().getTime());
        
        habitsVO.setJournalIsDone(habits.get(0).isDone());
        habitsVO.setGratitudeIsDone(habits.get(1).isDone());
        habitsVO.setIdeaIsDone(habits.get(2).isDone());
        habitsVO.setKindnessIsDone(habits.get(3).isDone());
        habitsVO.setLearnIsDone(habits.get(4).isDone());
        habitsVO.setFeelIsDone(habits.get(5).isDone());

        Date today = new Date();
        String habitsObjectId = (todayHabits != null) ? todayHabits.getObjectId() : null;
        HabitService.saveHabits(habitsVO, DateHelper.formatDate(today), habitsObjectId);

        Log.d("MainActivity", "saving habitsVO = " + habitsVO);
        Log.d("MainActivity", "date = " + DateHelper.formatDate(today));
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private void setupParse()
    {
        Parse.initialize(this, "3WcLoKzzwJl8MvMLVNtiw1UtfWxKC5cH91TC27Nh", "mujvlbfcSQAC05CEFJZV4J0DhI3qoDWA1eehe2ey");
    }
}
