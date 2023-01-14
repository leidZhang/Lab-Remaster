package com.example.springboot.service.impl;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;
import com.example.springboot.service.IGameSerivce;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GameService implements IGameSerivce {
    @Override
    public Object getQuestion() {
        CountryDB db = new CountryDB();
        String result = "";

        List<String> capitals = db.getCapitals();
        int n = capitals.size();
        int index = (int) (n * Math.random());
        String c = capitals.get(index);

        Map<String, Country> data = db.getData();
        Country ref = data.get(c);
        String country = ref.getName();
        String capital = ref.getCapital();

        if(Math.random()<0.5){
            result = "What is the capital of " + country + "?";
            result += "\n" + capital;
        } else {
            result = capital + "is the capital of ?";
            result += "\n" + country;
        }

        return result;
    }
}
