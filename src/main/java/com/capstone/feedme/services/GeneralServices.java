package com.capstone.feedme.services;

import com.capstone.feedme.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralServices {

    public List<Category> detailsPageCatControl(List<Category> categories){
            List<Category> outputCategories = (List<Category>) categories.remove(0);
            return outputCategories;
    }

}
