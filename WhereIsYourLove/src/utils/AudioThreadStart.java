package utils;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioThreadStart implements Runnable {
    @Override
    public void run() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("zapusk.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

