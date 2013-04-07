package com.webresponsive.ihahabits.adapters;

import java.text.NumberFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.webresponsive.ihahabits.R;
import com.webresponsive.ihahabits.vo.HabitVO;

public class HabitsHistoryListAdapter extends BaseAdapter
{    
    private Activity context;
    private ArrayList<HabitVO> habits;
    
    static class ViewHolder
    {
        public TextView habitNameText;
        public TextView habitChainText;
        public TextView habitMaxChainText;
        public TextView habitScoreText;
    }

    public HabitsHistoryListAdapter(Activity context)
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
            rowView = inflater.inflate(R.layout.habits_history_list_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.habitNameText = (TextView) rowView.findViewById(R.id.habitName);
            viewHolder.habitChainText = (TextView) rowView.findViewById(R.id.habitChain);
            viewHolder.habitMaxChainText = (TextView) rowView.findViewById(R.id.habitMaxChain);
            viewHolder.habitScoreText = (TextView) rowView.findViewById(R.id.habitScore);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        HabitVO habitVO = habits.get(position);
        holder.habitNameText.setText(habitVO.getName());

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(0);

        holder.habitChainText.setText(habitVO.getChain() + "");
        holder.habitMaxChainText.setText("Record: " + habitVO.getMaxChain());
        holder.habitScoreText.setText(numberFormat.format(habitVO.getScore()) + " %");

//        holder.habitScoreText.setText("Chain: " + habitVO.getChain() + "  Max: " + habitVO.getMaxChain() + " - " + numberFormat.format(habitVO.getScore()) + " %");

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
