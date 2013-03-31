package com.webresponsive.ihahabits.vo;

import java.util.Date;

public class HabitsVO
{
    private String objectId;

    private boolean journalIsDone;

    private boolean gratitudeIsDone;

    private boolean ideaIsDone;

    private boolean kindnessIsDone;

    private boolean learnIsDone;

    private boolean feelIsDone;

    private Date date;

    public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }

    public boolean isJournalIsDone()
    {
        return journalIsDone;
    }

    public void setJournalIsDone(boolean journalIsDone)
    {
        this.journalIsDone = journalIsDone;
    }

    public boolean isGratitudeIsDone()
    {
        return gratitudeIsDone;
    }

    public void setGratitudeIsDone(boolean gratitudeIsDone)
    {
        this.gratitudeIsDone = gratitudeIsDone;
    }

    public boolean isIdeaIsDone()
    {
        return ideaIsDone;
    }

    public void setIdeaIsDone(boolean ideaIsDone)
    {
        this.ideaIsDone = ideaIsDone;
    }

    public boolean isKindnessIsDone()
    {
        return kindnessIsDone;
    }

    public void setKindnessIsDone(boolean kindnessIsDone)
    {
        this.kindnessIsDone = kindnessIsDone;
    }

    public boolean isLearnIsDone()
    {
        return learnIsDone;
    }

    public void setLearnIsDone(boolean learnIsDone)
    {
        this.learnIsDone = learnIsDone;
    }

    public boolean isFeelIsDone()
    {
        return feelIsDone;
    }

    public void setFeelIsDone(boolean feelIsDone)
    {
        this.feelIsDone = feelIsDone;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }


}
