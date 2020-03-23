package com.nova.engine.sounds;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundClip {

    private Clip clip;
    private FloatControl gainControl;

    public SoundClip(String path) {
        try  {
            InputStream audioSrc = SoundClip.class.getResourceAsStream(path);
            InputStream bufferedStream = new BufferedInputStream(audioSrc);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedStream);

            AudioFormat baseFormat = ais.getFormat();

            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED,
                    baseFormat.getSampleRate(), 16,
                    baseFormat.getChannels(), baseFormat.getChannels()*2,
                    baseFormat.getFrameRate(), false);

            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);

            clip = AudioSystem.getClip();
            clip.open(dais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        if (clip==null)
        {
            return;
        }
        stop();
        clip.setFramePosition(0);
        while (!clip.isRunning())
        {
            clip.start();
        }
    }

    public void stop() {
        if (clip.isRunning())
        {
            clip.stop();
        }
    }
    public void close()
    {
        stop();
        clip.drain();
        clip.close();
    }
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }
    public void setVolume(float volume)
    {
        gainControl.setValue(volume);
    }
    public boolean isRunning()
    {
        return clip.isRunning();
    }
}
