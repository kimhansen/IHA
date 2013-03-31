package com.webresponsive.ihahabits.cloud;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.webresponsive.ihahabits.state.HabitManager;
import com.webresponsive.ihahabits.util.DateHelper;
import com.webresponsive.ihahabits.vo.HabitsVO;

public class HabitService
{
    private static final String PARSE_TABLE_HABITS = "Habits";

    public static boolean saveHabits(final HabitsVO habitsVO, String date, String objectId)
    {
        final ParseObject habitObject = new ParseObject(PARSE_TABLE_HABITS);

        if (objectId != null)
        {
            habitObject.setObjectId(objectId);
        }
        habitObject.put("JournalIsDone", habitsVO.isJournalIsDone());
        habitObject.put("GratitudeIsDone", habitsVO.isGratitudeIsDone());
        habitObject.put("IdeaIsDone", habitsVO.isIdeaIsDone());
        habitObject.put("KindnessIsDone", habitsVO.isKindnessIsDone());
        habitObject.put("LearnIsDone", habitsVO.isLearnIsDone());
        habitObject.put("FeelIsDone", habitsVO.isFeelIsDone());
        
        habitObject.put("Date", date);

        habitObject.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                if (e == null)
                {
                    Log.d("HabitService", "Successfully saved habit");

                    Date today = new Date();
                    getHabits(DateHelper.formatDate(today));
                }
                else
                {
                    showErrorMessage(e);
                }
            }
        });

        return true;
    }

    public static void getHabits(String date)
    {
        ParseQuery query = new ParseQuery(PARSE_TABLE_HABITS);
        query.whereEqualTo("Date", date);
        query.findInBackground(new FindCallback()
        {
            @Override
            public void done(List<ParseObject> habitList, ParseException e)
            {
                if (e == null)
                {
                    Log.d("HabitService", "Retrieved " + habitList.size() + " habits");

                    setHabits(habitList);
                }
                else
                {
                    showErrorMessage(e);
                }
            }
        });
    }

    /**
     * Convert the project parse objects into habitsVO's and update the
     * ProjectManager.
     */
    private static void setHabits(List<ParseObject> habitList)
    {
        ArrayList<HabitsVO> habits = new ArrayList<HabitsVO>();

        for (ParseObject parseObject : habitList)
        {
            HabitsVO habitsVO = new HabitsVO();

            habitsVO.setObjectId(parseObject.getObjectId());

            habitsVO.setJournalIsDone(parseObject.getBoolean("JournalIsDone"));
            habitsVO.setGratitudeIsDone(parseObject.getBoolean("GratitudeIsDone"));
            habitsVO.setIdeaIsDone(parseObject.getBoolean("IdeaIsDone"));
            habitsVO.setKindnessIsDone(parseObject.getBoolean("KindnessIsDone"));
            habitsVO.setLearnIsDone(parseObject.getBoolean("LearnIsDone"));
            habitsVO.setFeelIsDone(parseObject.getBoolean("FeelIsDone"));

            habits.add(habitsVO);
        }

        HabitManager.getInstance().setHabits(habits);
    }

    private static void showErrorMessage(ParseException e)
    {
        Log.e("HabitService", "Error when saving habit - e = " + e);
    }
}
