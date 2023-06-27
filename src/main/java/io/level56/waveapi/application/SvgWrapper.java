package io.level56.waveapi.application;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

class SvgWrapper{

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