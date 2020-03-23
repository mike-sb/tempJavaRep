package ru.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.spring")
@PropertySource("music.properties")
public class Config {

    @Bean
    public ClassicalMusic classicalMusic()
    {
        return new ClassicalMusic();
    }
    @Bean
    public RockMusic rockMusic()
    {
        return new RockMusic();
    }
    @Bean
    public MusicPlayer musicPlayer()
    {
        return new MusicPlayer();
    }

}
