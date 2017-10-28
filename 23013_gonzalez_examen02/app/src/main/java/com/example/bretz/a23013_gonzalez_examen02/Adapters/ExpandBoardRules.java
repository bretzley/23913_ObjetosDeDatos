package com.example.bretz.a23013_gonzalez_examen02.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.bretz.a23013_gonzalez_examen02.R;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by bretz on 10/27/2017.
 */

public class ExpandBoardRules extends BaseExpandableListAdapter{

    private Context context;
    private List<String> expandableListTitle;
    private TreeMap<String, List<String>> expandableListDetails;

    public ExpandBoardRules(Context context, List<String> expandleListTitle,
                            TreeMap<String, List<String>> expandableListDabetail){
        this.context = context;
        this.expandableListTitle = expandleListTitle;
        this.expandableListDetails = expandableListDabetail;

    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition){
        return this.expandableListDetails.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_boards, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandBoardRules);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }


    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetails.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String boardInfo = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.txtBoardInfo);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(boardInfo);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


}
