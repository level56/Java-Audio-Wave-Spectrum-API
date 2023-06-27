package io.level56.waveapi.application;

import javafx.scene.paint.Color;
import io.level56.waveapi.application.components.DynamicCanvas;

public class WaveFormPane extends DynamicCanvas {
	
	private final float[] defaultWave;
	private float[] waveData;
	private Color backgroundColor;
	private Color foregroundColor;
	private Color transparentForeground;
	private Color mouseXColor = Color.rgb(255, 255, 255, 0.7);
	public int width;
	int height;
	private int timerXPosition = 0;
	private int mouseXPosition = -1;
	private WaveVisualization waveVisualization;
	Color timerColor = Colors.green;
	Color pastColor = Colors.greenDarkTransparent;
	
// ** CONSTRUCTOR
	public WaveFormPane(int width, int height, Color bg, Color fg) {

		defaultWave = new float[width];

		this.width = width;
		this.height = height;
		this.setWidth(width);
		this.setHeight(height);
		
		// ** default wave
		for (int i = 0; i < width; i++)
			defaultWave[i] = 0.28802148f;
		waveData = defaultWave;
		
		setBackground(bg);
		setForeground(fg);
		
	}
	
	public void setWaveData(float[] waveData) {
		this.waveData = waveData;
	}
	
	public void setForeground(Color color) {
		this.foregroundColor = color;
		transparentForeground = Color.rgb((int) ( foregroundColor.getRed() * 255 ), (int) ( foregroundColor.getGreen() * 255 ), (int) ( foregroundColor.getBlue() * 255 ), 0.3);
	}
	
	public void setBackground(Color color) {
		this.backgroundColor = color;
	}
	
	public int getTimerXPosition() {
		return timerXPosition;
	}
	
	public void setTimerXPosition(int timerXPosition) {
		this.timerXPosition = timerXPosition;
	}
	
	public void setMouseXPosition(int mouseXPosition) {
		this.mouseXPosition = mouseXPosition;
	}
	

	public void clear() {
		waveData = defaultWave;
		
		// ** background
		gc.setFill(backgroundColor);
		gc.fillRect(0, 0, width, height);
		
		// ** line
		gc.setStroke(foregroundColor);
		gc.strokeLine(0, height / 2, width, height / 2);
	}
	
	public void paintWaveForm() {
		
		// ** background
		gc.setFill(backgroundColor);
		gc.fillRect(0, 0, width, height);
		
		/// ** waveform
		gc.setStroke(foregroundColor);
		if (waveData != null)
			for (int i = 0; i < waveData.length; i++) {
				if (!waveVisualization.getAnimationService().isRunning()) {
					clear();
					break;
				}
				int value = (int) ( waveData[i] * height );
				int y1 = ( height - 2 * value ) / 2;
				int y2 = y1 + 2 * value;
				gc.strokeLine(i, y1, i, y2);
			}
		
		// ** draw past region
		gc.setFill(pastColor);
		gc.fillRect(0, 0, timerXPosition, height);
		
		// ** draw timer line
		gc.setFill(timerColor);
		gc.fillRect(timerXPosition, 0, 1, height);
		
		// ** draw mouse line
		if (mouseXPosition != -1) {
			gc.setFill(mouseXColor);
			gc.fillRect(mouseXPosition, 0, 3, height);
		}
	}
	
	public WaveVisualization getWaveVisualization() {
		return waveVisualization;
	}
	
	public void setWaveVisualization(WaveVisualization waveVisualization) {
		this.waveVisualization = waveVisualization;
	}
	
}
