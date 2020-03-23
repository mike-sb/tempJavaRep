package ru.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music {

    @Value("${music.songName}")
    private String songName;

    public ClassicalMusic() {
    }


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
        System.out.println(" CM Inited.");
    }
    public void destroy()
    {
        System.out.println(" CM Destroyed");
    }
}
