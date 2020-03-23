package ru.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicPlayer {
    private List<Music> musicList = new ArrayList<>();
    private Music music;

    public MusicPlayer() {
    }

    public void playMusic() {
        for (Music m: musicList) {
            System.out.println("Playing: " + m.getSong());
        }

    }

    public void addMusic(Music music)
    {
        musicList.add(music);
    }
    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }
}
