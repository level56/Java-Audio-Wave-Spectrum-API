package io.level56.waveapi.application;

import io.level56.waveapi.application.WaveFormService.WaveFormJob;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import io.level56.waveapi.application.tools.Colors;

public class Main extends Application {

	WaveVisualization waveVisualization = new WaveVisualization(520, 32,Colors.purpleDark, Colors.purple);

	@Override
	public void start(Stage stage) {
		try {
			BorderPane root = new BorderPane();
			root.setCenter(waveVisualization);
			root.boundsInLocalProperty().addListener(l -> {
				waveVisualization.setWidth(root.getWidth());
				waveVisualization.setHeight(root.getHeight());
			});

			stage.setTitle("Wave");
			stage.setOnCloseRequest(c -> System.exit(0));

			Scene scene = new Scene(root, 600, 40);

			stage.setScene(scene);
			stage.show();

			waveVisualization.getWaveService().startService(
					"/Users/level56/Documents/GitHub/Untitled/Java-Audio-Wave-Spectrum-API/src/main/resources/io/level56/waveapi/application/audio/audio.mp3",
					WaveFormJob.AMPLITUDES_AND_WAVEFORM);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
