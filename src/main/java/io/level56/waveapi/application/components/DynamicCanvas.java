package io.level56.waveapi.application.components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DynamicCanvas extends Canvas {

    public final GraphicsContext gc = getGraphicsContext2D();

    public void redraw() {
    	gc.setLineWidth(3);
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setStroke(Color.RED);
        gc.strokeLine(0, 0, getWidth(), getHeight());
        gc.strokeLine(0, getHeight(), getWidth(), 0);
    }

    @Override
    public double minHeight(double width) {
        return 1;
    }

    @Override
    public double minWidth(double height) {
        return 1;
    }

    @Override
    public double prefWidth(double width) {
        return minWidth(width);
    }

    @Override
    public double prefHeight(double width) {
        return minHeight(width);
    }

    @Override
    public double maxWidth(double height) {
        return Double.MAX_VALUE;
    }

    @Override
    public double maxHeight(double width) {
        return Double.MAX_VALUE;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
    }
}
