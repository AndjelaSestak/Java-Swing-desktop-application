package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import drawing.DlgDonut;

public class Donut extends Circle {
	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		// this.center - ne moze jer je private u Circle klasi
		/*
		 * this.setCenter(center); this.setRadius(radius);
		 */
		super(center, radius);// prva naredba
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		/*
		 * this.center=center;//moze jer je protected //this.radius= radius; ne moze jer
		 * je private this.setRadius(radius); this.setSelected(selected);
		 * this.innerRadius= innerRadius;
		 */

		// drugi nacin
		super(center, radius, selected); // poziv konstruktora iz Circle nadredjene klase
		this.innerRadius = innerRadius;

	}

	public Donut(Point center, int radius, int innerRadius, Color color) {
		this(center, radius, innerRadius);
		setColor(color);

	}

	public Donut(Point center, int radius, int innerRadius, Color color, Color colorInner) {
		this(center, radius, innerRadius, color);
		setInnerColor(colorInner);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			/*
			 * if (this.center.equals(pomocni.center) && this.getRadius() ==
			 * pomocni.getRadius()) { return true; } else { return false; } } else { return
			 * false; } }
			 */
			if (super.equals(pomocni) && this.innerRadius == pomocni.innerRadius) {// equals iz kruga proverava centar i
																					// radius
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		return super.contains(x, y) && center.distance(x, y) >= innerRadius;
	}// mora se definisati jer bi prvo trazio u Circle

	public boolean contains(Point clickPoint) {
		return super.contains(clickPoint) && center.distance(clickPoint.getX(), clickPoint.getY()) >= innerRadius;
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public void fill(Graphics g) {
		// Providnost donut-a
		Ellipse2D eIner = new Ellipse2D.Float(center.getX() - innerRadius, center.getY() - innerRadius, 2 * innerRadius,
				2 * innerRadius);
		Ellipse2D eOuter = new Ellipse2D.Float(center.getX() - getRadius(), center.getY() - getRadius(),
				2 * getRadius(), 2 * getRadius());
		Area outer = new Area(eOuter);
		Area iner = new Area(eIner);
		outer.subtract(iner);

		g.setColor(getInnerColor());
		((Graphics2D) g).fill(outer);

		/*
		 * g.setColor(getInnerColor()); super.fill(g); g.setColor(Color.white);
		 * g.fillOval(center.getX()-innerRadius+1, center.getY()-innerRadius+1,
		 * innerRadius*2-1, innerRadius*2-1);
		 */
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);// crtanje spoljasnjeg kruga
		g.setColor(getColor());
		g.drawOval(center.getX() - innerRadius, center.getY() - innerRadius, innerRadius * 2, innerRadius * 2);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - innerRadius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() + innerRadius - 2, center.getY() - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() - innerRadius - 2, 4, 4);
			g.drawRect(center.getX() - 2, center.getY() + innerRadius - 2, 4, 4);
			g.setColor(Color.black);
		}
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Donut) {
			Donut donutToCompare = (Donut) obj;
			return (int) (this.area() - donutToCompare.area());
		}
		return 0;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public String toString() {
		// Center=(x,y), radius= radius
		return super.toString() + ", innerRadius=" + innerRadius;
	}

	public Donut clone(Donut donut) {
		//Donut donut = new Donut();
		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());
		try {
			donut.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setInnerRadius(this.getInnerRadius());
		donut.setInnerColor(this.getInnerColor());
		donut.setColor(this.getColor());
		return donut;
	}

}
