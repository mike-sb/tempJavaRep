package ru.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RockMusic implements Music {

    @Value("${music.songName}")
    private String songName;

    @Override
    public void setSong(String songName) {
        this.songName = songName;
    }

    @Override
    public String getSong() {
        return songName;
    }
    public void init()
    {
        System.out.println("RM Inited.");
    }
    public void destroy()
    {
        System.out.println("RM Destroyed");
    }
}
