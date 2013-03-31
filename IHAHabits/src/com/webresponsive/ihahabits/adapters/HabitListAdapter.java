package com.webresponsive.ihahabits.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.webresponsive.ihahabits.R;
import com.webresponsive.ihahabits.vo.HabitVO;

public class HabitListAdapter extends BaseAdapter
{    
    private Activity context;
    private ArrayList<HabitVO> habits;
    
    static class ViewHolder
    {
        public TextView habitNameText;
        public ImageView habitOkImage;
//        public boolean isDone;
    }

    public HabitListAdapter(Activity context)
    {
        this.context = context;
    }

    public void setHabits(ArrayList<HabitVO> habits)
    {
        this.habits = habits;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView = convertView;
        if (rowView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.habit_list_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.habitNameText = (TextView) rowView.findViewById(R.id.habitName);
            viewHolder.habitOkImage = (ImageView) rowView.findViewById(R.id.habitOk);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        HabitVO habitVO = habits.get(position);
        holder.habitNameText.setText(habitVO.getName());
//        holder.isDone = habitVO.isDone();
        if (habitVO.isDone())
        {
            holder.habitOkImage.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.habitOkImage.setVisibility(View.GONE);
        }

        return rowView;
    }

    @Override
    public int getCount()
    {
        return habits.size();
    }

    @Override
    public Object getItem(int position)
    {
        return habits.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }
}
