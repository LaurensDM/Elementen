package resources;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class ResourceController {
	private MediaPlayer mediaplayer;
	private List<String> playlist;
	private double currentVolume = 1;
	private int playListIndex = 0;

	public ResourceController() {
		playlist = new LinkedList<>();
		Collections.addAll(playlist, "/resources/music/dance-of-devils-giulio-fazio-main-version-01-15-14356.mp3",
				"/resources/music/Dance With Me.mp3",
				"/resources/music/rise-of-the-hero-vens-adams-main-version-02-27-454.mp3",
				"/resources/music/epic-choir-of-the-damned-giulio-fazio-main-version-01-01-9283.mp3",
				"/resources/music/dangerous-times-richard-bodgers-main-version-03-52-7781.mp3");
	}

	public void playMusic(boolean next) {
		if (next == false) {
			Collections.shuffle(playlist);
			Media buzzer = new Media(getClass().getResource(playlist.get(0)).toExternalForm());
			mediaplayer = new MediaPlayer(buzzer);
		}

		mediaplayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaplayer.seek(Duration.ZERO);
			}
		});
		
		mediaplayer.play();
	}
	
	public void worldMusic() {
		mediaplayer.stop();
		mediaplayer = new MediaPlayer(new Media(getClass().getResource("/resources/music/Adventure.mp3").toExternalForm()));
		mediaplayer.setVolume(currentVolume);
		playMusic(true);
	}
	
	public void gameOver() {
		mediaplayer.stop();
		mediaplayer = new MediaPlayer(new Media(getClass().getResource("/resources/music/despair-and-triumph-kevin-macleod-main-version-04-40-7981.mp3").toExternalForm()));
		mediaplayer.setVolume(currentVolume);
		playMusic(true);
	}

	public void playSoundEffect(String effect) {
		AudioClip buzzer = new AudioClip(getClass().getResource("/resources/music/lazer.mp3").toExternalForm());
		buzzer.play();

	}

	public void changeVolume(double value) {
		currentVolume = value;
		mediaplayer.setVolume(value);
	}
	

	public double getCurrentVolume() {
		return currentVolume;
	}
	
	public boolean isPaused() {
		if (mediaplayer.getStatus()==Status.PAUSED) return true;
		return false;
	}
	
	public void pauseMusic() {
		mediaplayer.pause();
	}
	
	public void unPauseMusic() {
		if (mediaplayer.getStatus()==Status.PAUSED) {
			mediaplayer.play();
		}
	}

	public void nextSong() {
		mediaplayer.stop();
		playListIndex++;
		if (playListIndex==playlist.size()) {
			playListIndex=0;
		}
		mediaplayer = new MediaPlayer(new Media(getClass().getResource(playlist.get(playListIndex)).toExternalForm()));
		mediaplayer.setVolume(currentVolume);
		playMusic(true);
	}

	public MediaPlayer getMediaplayer() {
		return mediaplayer;
	}

}
