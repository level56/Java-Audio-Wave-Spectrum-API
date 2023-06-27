package io.level56.waveapi.application;

import javafx.scene.paint.Color;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.level56.waveapi.application.components.DynamicCanvas;
import io.level56.waveapi.application.tools.Colors;

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
		transparentForeground = Color.rgb((int) (foregroundColor.getRed() * 255),
				(int) (foregroundColor.getGreen() * 255), (int) (foregroundColor.getBlue() * 255), 0.3);
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
				int value = (int) (waveData[i] * height);
				int y1 = (height - 2 * value) / 2;
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

//enum wav{FORMAT,W,H,X,Y}

class wav{
	
	private wav format;
	private wav w;
	private wav h;
	private wav x;
	private wav y;
	
	public wav getFormat() {
		return format;
	}
	public void setFormat(wav format) {
		this.format = format;
	}
	public wav getW() {
		return w;
	}
	public void setW(wav w) {
		this.w = w;
	}
	public wav getH() {
		return h;
	}
	public void setH(wav h) {
		this.h = h;
	}
	public wav getX() {
		return x;
	}
	public void setX(wav x) {
		this.x = x;
	}
	public wav getY() {
		return y;
	}
	public void setY(wav y) {
		this.y = y;
	}


}


	class Svg{
		private double w;
		private double h;
		private Path path;
		
		Svg(double w, double h, Path path){
			this.w = w;
			this.h = h;
			this.path = path;
		}
		
		public double getW() {
			return w;
		}
		public void setW(double w) {
			this.w = w;
		}
		public double getH() {
			return h;
		}
		public void setH(double h) {
			this.h = h;
		}

		public Path getPath() {
			return path;
		}

		public void setPath(Path path) {
			this.path = path;
		}

		public void getPathW() {
			this.path.getW();
		}
		public void setPATHW(double w) {
			this.path.setW(w);
		}

		public void getPathH() {
			this.path.getH();
		}

		public void setPathH(double h) {
			this.path.setH(h);
		}

		public void getPathX() {
			this.path.getX();
		}

		public void setPathX(double x) {
			this.path.setX(x);
		}

		public void getPathY() {
			this.path.getY();
		}

		public void setPathY(double y) {
			this.path.setY(y);
		}



	}
	
	class Path{
		private double w;
		private double h;
		private double x;
		private double y;

		Path(double w, double h, double x, double y){
			this.w = w;
			this.h = h;
			this.x = x;
			this.y = y;
		}

		public double getW() {
			return w;
		}

		public void setW(double w) {
			this.w = w;
		}

		public double getH() {
			return h;
		}

		public void setH(double h) {
			this.h = h;
		}

		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}


	}

	@XmlRootElement(name = "wav")
	public class WavListWrapper {

		private List<Svg> svg;

		@XmlElement(name = "svg")
		public List<Svg> getSVG() {
			return svg;
		}

		public void setSVG(List<Svg> svg) {
			this.svg = svg;
		}
	}
}
