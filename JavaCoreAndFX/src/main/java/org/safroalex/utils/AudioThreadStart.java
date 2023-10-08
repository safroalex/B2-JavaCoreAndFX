package org.safroalex.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;

public class AudioThreadStart implements Runnable {
    @Override
    public void run() {
        try {
            String s = Paths.get(getClass().getClassLoader().getResource("zapusk.wav").toURI()).toAbsolutePath().toString();
            Media hit = new Media(Paths.get(s).toUri().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
