package com.capstone.feedme.services;

import com.capstone.feedme.models.Rating;
import com.capstone.feedme.models.Recipe;
import com.capstone.feedme.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IconService {

    public String ratingIcon(List<Rating> ratings, User user, Recipe recipe) {

        boolean flag = false;

        for (int i = 0; i < ratings.size(); i++) {
            if(ratings.get(i).getUser().getId() == user.getId() && ratings.get(i).getRecipe().getId() == recipe.getId()){
                flag = true;
            }
        }

        if(flag){
            return "<i class=\"fa-solid fa-heart fa-2x p-2\"></i>";
        } else {
            return "<i class=\"fa-regular fa-heart fa-2x p-2\"></i>";
        }








    }

}
