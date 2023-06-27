package io.level56.waveapi.application;

import javafx.scene.control.Button;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.level56.waveapi.application.WaveFormService.WaveFormJob;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import io.level56.waveapi.application.tools.Colors;

public class Main extends Application {

	WaveVisualization waveVisualization = new WaveVisualization(520 * 6, 32 * 6, Colors.purpleDark, Colors.purple);
	Button save;
	Pane pane;

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
			Scene scene = new Scene(root, 600 * 6, 40 * 6);
			stage.setScene(scene);
			stage.show();
			waveVisualization.getWaveService().startService(
					"/Users/level56/Documents/GitHub/Untitled/Java-Audio-Wave-Spectrum-API/src/main/resources/io/level56/waveapi/application/audio/audio.wav",
					WaveFormJob.AMPLITUDES_AND_WAVEFORM);
			snapShot(stage, waveVisualization);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void snapShot(Stage stage, WaveVisualization wave) {

		FileChooser savefile = new FileChooser();
		savefile.setTitle("Save File");

		File file = savefile.showSaveDialog(stage);
		System.out.println("is file null ? " + file);

		if (file != null) {
			try {
				double pixelScale = 2;
				WritableImage writableImage = new WritableImage(
						(int) Math.rint(pixelScale * wave.getWidth()),
						(int) Math.rint(pixelScale * wave.getHeight()));

				SnapshotParameters params = new SnapshotParameters();
				params.setTransform(Transform.scale(pixelScale, pixelScale));

				wave.snapshot(params, writableImage);
				
				RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
				ImageIO.write(renderedImage, "png", file);

			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("Error!");
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
