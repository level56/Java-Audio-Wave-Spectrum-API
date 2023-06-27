package io.level56.waveapi.application;

import io.level56.waveapi.application.WaveFormService.WaveFormJob;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;

public class WaveVisualization extends WaveFormPane {
	
	private final PaintService animationService; // ** repaint
	private final WaveFormService waveService; // ** wave data
	private boolean recalculateWaveData;
	
// ** CONSTRUCTOR
// region constructor
	public WaveVisualization(int width, int height, Color bg, Color fg) {
		super(width, height, bg, fg);
		super.setWaveVisualization(this);
		waveService = new WaveFormService(this);
		animationService = new PaintService();
		
		// ** WIDTH
		// region width
		widthProperty().addListener((observable , oldValue , newValue) -> {

			// ** canvas width
			this.width = Math.round(newValue.floatValue());
			
			// ** draw line
			recalculateWaveData = true;
			clear();
			
		});
		// endregion

		// ** HEIGHT
		// region height
		heightProperty().addListener((observable , oldValue , newValue) -> {
			
			// ** canvas height
			this.height = Math.round(newValue.floatValue());
			
			// ** draw line
			recalculateWaveData = true;
			clear();
		});
		// endregion
		
		// ** MOUSE EVENTS
		setOnMouseMoved(m -> this.setMouseXPosition((int) m.getX()));
		setOnMouseDragged(m -> this.setMouseXPosition((int) m.getX()));
		setOnMouseExited(m -> this.setMouseXPosition(-1));
		
	}
	// endregion

	public PaintService getAnimationService() {
		return animationService;
	}
	
	public WaveFormService getWaveService() {
		return waveService;
	}
	
// ** METHODS
// region methods
	public void startPainterService() {
		animationService.start();
	}
	
	public void stopPainterService() {
		animationService.stop();
		clear();
	}

	public boolean isPainterServiceRunning() {
		return animationService.isRunning();
	}
	// endregion
	
// ** PAINT SERVICE
// region paint service
	public class PaintService extends AnimationTimer {

		private volatile SimpleBooleanProperty running = new SimpleBooleanProperty(false);
		private long previousNanos = 0;
		
		@Override
		public void start() {
			if (width <= 0 || height <= 0)
				width = height = 1;
			
			super.start();
			running.set(true);
		}
		
		public WaveVisualization getWaveVisualization() {
			return WaveVisualization.this;
		}
		
		@Override
		public void handle(long nanos) {
			if (nanos >= previousNanos + 100000 * 1000) { //
				previousNanos = nanos;
				setTimerXPosition(getTimerXPosition() + 1);
			}
			
			if (getWaveService().getResultingWaveform() == null || recalculateWaveData) {
				getWaveService().startService(getWaveService().getFileAbsolutePath(), WaveFormJob.WAVEFORM);
				recalculateWaveData = false;
				return;
			}	
			paintWaveForm();
		}
		
		@Override
		public void stop() {
			super.stop();
			running.set(false);
		}
		
		public boolean isRunning() {
			return running.get();
		}
		
		public SimpleBooleanProperty runningProperty() {
			return running;
		}
		
	}
	// endregion
	
}
