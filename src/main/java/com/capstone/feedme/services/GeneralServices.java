package com.capstone.feedme.services;

import com.capstone.feedme.models.Category;
import com.capstone.feedme.models.Comment;
import com.capstone.feedme.models.Rating;
import com.capstone.feedme.models.Recipe;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

@Service
public class GeneralServices {

    public int getNumberOfRatingsByRecipe(List<Rating> ratings, Recipe recipe) {
        int output = 0;
        if (ratings == null) {
            return 0;
        } else {
            for (int i = 0; i < ratings.size(); i++) {
                if (ratings.get(i).getRecipe() == null) {
                    return 0;
                } else if (ratings.get(i).getRecipe().getId() == recipe.getId()) {
                    output += ratings.get(i).getRating();
                    ;
                }
            }
        }
        return output;
    }

    public int getNumberOfCommentsByRecipe(List<Comment> comments, Recipe recipe) {

        List<Comment> outputNumberOfCommentsByRecipe = new ArrayList<>();

        if (comments == null) {
            return 0;
        } else {
            for (int i = 0; i < comments.size(); i++) {
                if (comments.get(i).getRecipe() == null) {
                    return 0;
                } else if (comments.get(i).getRecipe().getId() == recipe.getId()) {
                    outputNumberOfCommentsByRecipe.add(comments.get(i));
                }
            }
        }
        return outputNumberOfCommentsByRecipe.size();
    }

    public String dateFormat(LocalDateTime localDateTime){

        System.out.println("Before : " + localDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm E, MMM dd yyyy ");

        String outputDateTimeString = localDateTime.format(formatter);

        System.out.println("After : " + outputDateTimeString);

        return outputDateTimeString;

    }

    public String removeATags(String string){
        System.out.println("========================================");
        System.out.println("don't ignorer me");
        return string.replaceAll("<a href=", "<br>Link: ");

    }


}

