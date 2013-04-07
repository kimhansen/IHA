package com.webresponsive.ihahabits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import android.app.Activity;
import android.graphics.Color;
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

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.webresponsive.ihahabits.adapters.HabitsHistoryListAdapter;
import com.webresponsive.ihahabits.cloud.HabitService;
import com.webresponsive.ihahabits.state.HabitManager;
import com.webresponsive.ihahabits.state.HabitManager.OnHabitsHistoryUpdatedListener;
import com.webresponsive.ihahabits.util.DateHelper;
import com.webresponsive.ihahabits.vo.HabitVO;
import com.webresponsive.ihahabits.vo.HabitsVO;

public class HabitsHistoryFragment extends Fragment implements OnHabitsHistoryUpdatedListener
{
    public static String NAME = "";

    private View view;
    private MainActivity mainActivity;

    private ArrayList<HabitVO> habits;
    private HabitsHistoryListAdapter habitsHistoryListAdapter;
    private ArrayList<HabitsVO> habitsHistory;

    private XYPlot habitsGraph;

//    private Spinner timeSpinner;

    private SimpleXYSeries series;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.habits_history_fragment, container, false);
        this.view = view;

//        setupDaysSpinner(view);

        setupHabitList();

        getHabitsHistory();

        HabitManager.getInstance().setOnHabitsHistoryUpdatedListener(this);

        return view;
    }

//    private void setupDaysSpinner(View v)
//    {
//        timeSpinner = (Spinner) v.findViewById(R.id.timeSpinner);
//        timeSpinner.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.time_spinner, R.layout.spinner_fat_item));
//        timeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
//            {
//                int selectedDays = timeSpinner.getSelectedItemPosition();
//                Date dateFiler = DateHelper.getWeekAgo();
//                if (selectedDays == 0)
//                {
//                    dateFiler = DateHelper.getWeekAgo();
//                }
//                else if (selectedDays == 1)
//                {
//                    dateFiler = DateHelper.getMonthAgo();
//                }
//                else if (selectedDays == 2)
//                {
//                    dateFiler = DateHelper.get3MonthsAgo();
//                }
//                HabitService.getHabits(null, DateHelper.formatDate(dateFiler));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> paramAdapterView)
//            {
//            }
//        });
//    }

    @SuppressWarnings("deprecation")
    private void setupGraph(Number[] numberSeries)
    {
        habitsGraph = (XYPlot) view.findViewById(R.id.habitsGraph);

        if (series != null)
        {
            habitsGraph.removeSeries(series);
        }

        // Turn the above arrays into XYSeries':
        series = new SimpleXYSeries(Arrays.asList(numberSeries), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "");

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.rgb(0, 200, 0), Color.rgb(0, 100, 0), null);

        habitsGraph.addSeries(series, series1Format);

        // reduce the number of range labels
        habitsGraph.setTicksPerRangeLabel(3);

        habitsGraph.getLegendWidget().setVisible(false);
        habitsGraph.setBackground(null);
        habitsGraph.setBackgroundPaint(null);
        habitsGraph.setBackgroundColor(Color.TRANSPARENT);
        habitsGraph.setBorderPaint(null);
        habitsGraph.setDomainLabel("");
        habitsGraph.setRangeLabel("");
        habitsGraph.getTitleWidget().setVisible(false);
        habitsGraph.getGraphWidget().setBackgroundPaint(null);
        habitsGraph.getGraphWidget().setBorderPaint(null);
        habitsGraph.getGraphWidget().getGridBackgroundPaint().setColor(Color.TRANSPARENT);
//        habitsGraph.getGraphWidget().setGridPaddingTop(10);

        habitsGraph.setRangeTopMax(1);
        habitsGraph.setRangeBottomMax(0);

        // by default, AndroidPlot displays developer guides to aid in laying
        // out your plot.
        // To get rid of them call disableAllMarkup():
        habitsGraph.disableAllMarkup();
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

    private void getHabitsHistory()
    {
        Date dateFiler = DateHelper.get3MonthsAgo();
        HabitService.getHabits(null, DateHelper.formatDate(dateFiler));

        Log.d("MainActivity", "getting all habits");
    }

    private void setupHabitList()
    {
        ListView listView = (ListView) view.findViewById(R.id.habitsList);

        habitsHistoryListAdapter = new HabitsHistoryListAdapter(mainActivity);

        habits = getHabits();

        habitsHistoryListAdapter.setHabits(habits);

        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // TODO - show individual habit data
            }
        });
        listView.setAdapter(habitsHistoryListAdapter);
    }

    private ArrayList<HabitVO> getHabits()
    {
        ArrayList<HabitVO> habits = new ArrayList<HabitVO>();

        HabitVO habitVO1 = new HabitVO();
        habitVO1.setScore(0d);
        habitVO1.setChain(0);
        habitVO1.setMaxChain(0);
        habitVO1.setName("Journal");
        habits.add(habitVO1);
        HabitVO habitVO2 = new HabitVO();
        habitVO2.setScore(0d);
        habitVO2.setChain(0);
        habitVO2.setMaxChain(0);
        habitVO2.setName("Gratitude");
        habits.add(habitVO2);
        HabitVO habitVO3 = new HabitVO();
        habitVO3.setScore(0d);
        habitVO3.setChain(0);
        habitVO3.setMaxChain(0);
        habitVO3.setName("Idea");
        habits.add(habitVO3);
        HabitVO habitVO4 = new HabitVO();
        habitVO4.setScore(0d);
        habitVO4.setChain(0);
        habitVO4.setMaxChain(0);
        habitVO4.setName("Kindness");
        habits.add(habitVO4);
        HabitVO habitVO5 = new HabitVO();
        habitVO5.setScore(0d);
        habitVO5.setChain(0);
        habitVO5.setMaxChain(0);
        habitVO5.setName("Learn");
        habits.add(habitVO5);
        HabitVO habitVO6 = new HabitVO();
        habitVO6.setScore(0d);
        habitVO6.setChain(0);
        habitVO6.setMaxChain(0);
        habitVO6.setName("Feel");
        habits.add(habitVO6);

        return habits;
    }

    @Override
    public void onHabitsHistoryUpdated()
    {
        habitsHistory = HabitManager.getInstance().getHabits();

        if ((habitsHistory != null) && (habitsHistory.size() > 0))
        {
            Date today = DateHelper.getToday();

            HabitsVO firstHabitsVO = habitsHistory.get(0);
            TextView date = (TextView) view.findViewById(R.id.habitDate);
            date.setText(firstHabitsVO.getDate() + " - " + DateHelper.formatDate(today));

            Log.d("MainActivity", "Got habitsVO = " + habitsHistory.size());

            double journalScore = 0;
            double gratitudeScore = 0;
            double ideaScore = 0;
            double kindnessScore = 0;
            double learnScore = 0;
            double feelScore = 0;

            int journalChain = 0;
            int gratitudeChain = 0;
            int ideaChain = 0;
            int kindnessChain = 0;
            int learnChain = 0;
            int feelChain = 0;

            int journalMaxChain = 0;
            int gratitudeMaxChain = 0;
            int ideaMaxChain = 0;
            int kindnessMaxChain = 0;
            int learnMaxChain = 0;
            int feelMaxChain = 0;

            Number[] scores = new Number[habitsHistory.size()];
            int count = 0;
            String habitStartDate = habitsHistory.get(0).getDate();
            
            String startDate = DateHelper.dayBefore(habitStartDate);
            String habitDate;
            
            for (HabitsVO habitsVO : habitsHistory)
            {
                habitDate = habitsVO.getDate();
                startDate = DateHelper.dayAfter(startDate);
                Log.d("Habits", "start date = " + startDate);
                Log.d("Habits", "habit date = " + habitDate);

                // NOTE - don't count chain today, if zero, because day not over yet..
                boolean habitToday = DateHelper.isToday(habitDate);
                Log.d("Habits", "habitToday = " + habitToday);
                if (!habitDate.equals(startDate))
                {
                    Log.d("Habits", "Resetting from date = " + habitDate);
                    startDate = habitDate;

                    journalMaxChain = (journalMaxChain > journalChain) ? journalMaxChain : journalChain;
                    gratitudeChain = (gratitudeMaxChain > gratitudeChain) ? gratitudeMaxChain : gratitudeChain;
                    ideaMaxChain = (ideaMaxChain > ideaChain) ? ideaMaxChain : ideaChain;
                    kindnessMaxChain = (kindnessMaxChain > kindnessChain) ? kindnessMaxChain : kindnessChain;
                    learnMaxChain = (learnMaxChain > learnChain) ? learnMaxChain : learnChain;
                    feelMaxChain = (feelMaxChain > feelChain) ? feelMaxChain : feelChain;

                    journalChain = 0;
                    gratitudeChain = 0;
                    ideaChain = 0;
                    kindnessChain = 0;
                    learnChain = 0;
                    feelChain = 0;
                }
                
                double newNumber = 0;
                if (habitsVO.isJournalIsDone())
                {
                    newNumber++;
                    journalScore++;
                    journalChain++;
                }
                else if (!habitToday)
                {
                    journalMaxChain = (journalMaxChain > journalChain) ? journalMaxChain : journalChain;
                    journalChain = 0;
                }
                if (habitsVO.isGratitudeIsDone())
                {
                    newNumber++;
                    gratitudeScore++;
                    gratitudeChain++;
                }
                else if (!habitToday)
                {
                    gratitudeChain = (gratitudeMaxChain > gratitudeChain) ? gratitudeMaxChain : gratitudeChain;
                    gratitudeChain = 0;
                }
                if (habitsVO.isIdeaIsDone())
                {
                    newNumber++;
                    ideaScore++;
                    ideaChain++;
                }
                else if (!habitToday)
                {
                    ideaMaxChain = (ideaMaxChain > ideaChain) ? ideaMaxChain : ideaChain;
                    ideaChain = 0;
                }
                if (habitsVO.isKindnessIsDone())
                {
                    newNumber++;
                    kindnessScore++;
                    kindnessChain++;
                }
                else if (!habitToday)
                {
                    kindnessMaxChain = (kindnessMaxChain > kindnessChain) ? kindnessMaxChain : kindnessChain;
                    kindnessChain = 0;
                }
                if (habitsVO.isLearnIsDone())
                {
                    newNumber++;
                    learnScore++;
                    learnChain++;
                }
                else if (!habitToday)
                {
                    learnMaxChain = (learnMaxChain > learnChain) ? learnMaxChain : learnChain;
                    learnChain = 0;
                }
                if (habitsVO.isFeelIsDone())
                {
                    newNumber++;
                    feelScore++;
                    feelChain++;
                }
                else if (!habitToday)
                {
                    feelMaxChain = (feelMaxChain > feelChain) ? feelMaxChain : feelChain;
                    feelChain = 0;
                }
                
                scores[count++] = newNumber / 6;
            }

            setupGraph(scores);

            int days = habitsHistory.size();
            journalScore = journalScore / days * 100;
            gratitudeScore = gratitudeScore / days * 100;
            ideaScore = ideaScore / days * 100;
            kindnessScore = kindnessScore / days * 100;
            learnScore = learnScore / days * 100;
            feelScore = feelScore / days * 100;

            habits.get(0).setScore(journalScore);
            habits.get(1).setScore(gratitudeScore);
            habits.get(2).setScore(ideaScore);
            habits.get(3).setScore(kindnessScore);
            habits.get(4).setScore(learnScore);
            habits.get(5).setScore(feelScore);

            journalMaxChain = (journalMaxChain > journalChain) ? journalMaxChain : journalChain;
            gratitudeChain = (gratitudeMaxChain > gratitudeChain) ? gratitudeMaxChain : gratitudeChain;
            ideaMaxChain = (ideaMaxChain > ideaChain) ? ideaMaxChain : ideaChain;
            kindnessMaxChain = (kindnessMaxChain > kindnessChain) ? kindnessMaxChain : kindnessChain;
            learnMaxChain = (learnMaxChain > learnChain) ? learnMaxChain : learnChain;
            feelMaxChain = (feelMaxChain > feelChain) ? feelMaxChain : feelChain;

            habits.get(0).setChain(journalChain);
            habits.get(1).setChain(gratitudeChain);
            habits.get(2).setChain(ideaChain);
            habits.get(3).setChain(kindnessChain);
            habits.get(4).setChain(learnChain);
            habits.get(5).setChain(feelChain);

            habits.get(0).setMaxChain(journalMaxChain);
            habits.get(1).setMaxChain(gratitudeMaxChain);
            habits.get(2).setMaxChain(ideaMaxChain);
            habits.get(3).setMaxChain(kindnessMaxChain);
            habits.get(4).setMaxChain(learnMaxChain);
            habits.get(5).setMaxChain(feelMaxChain);
        }
        else
        {
            habits = getHabits();
        }

        habitsHistoryListAdapter.setHabits(habits);
        habitsHistoryListAdapter.notifyDataSetChanged();
    }
}
