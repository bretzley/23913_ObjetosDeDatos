package com.example.bretz.oct6android.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by bretz on 10/20/2017.
 */

public class ExpandData {

    public static TreeMap<String, List<String>> getData(ArrayList<Posts> posts, ArrayList<Comments> comments) {
        TreeMap<String, List<String>> expandableListDetail = new TreeMap<>();

        for (Posts p : posts) {
            String pString = "Title: " + p.getTitle() + " - Id:" + p.getId() +"\n"+ p.getBody();
            //String pString = "ID: " + p.getId() + " - " + p.getTitle() +"\n"+ p.getBody();

            List<String> commentStrings = new ArrayList<>();

            for(Comments c : comments) {
                if (c.getPostId().equals(p.getId())) {
                    String cString = (c.getName() + " commented: " + c.getBody());
                    commentStrings.add(cString);
                }
            }
            expandableListDetail.put(pString, commentStrings);
        }
        return expandableListDetail;
    }
}
