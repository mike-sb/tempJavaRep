package ru.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
// ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


        ClassicalMusic cm = context.getBean("classicalMusic", ClassicalMusic.class);
        RockMusic rm =context.getBean("rockMusic", RockMusic.class);



        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);
        player.addMusic(cm);
        player.addMusic(rm);
        player.playMusic();

        context.close();

    }
}
