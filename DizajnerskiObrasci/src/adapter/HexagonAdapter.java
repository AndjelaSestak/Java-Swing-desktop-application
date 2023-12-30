package adapter;

import java.awt.Color;
import java.awt.Graphics;
import geometry.ShapesWithSurface;
import hexagon.Hexagon;

public class HexagonAdapter extends ShapesWithSurface {

	private Hexagon hexagon = new Hexagon(1, 1, 1);

	public HexagonAdapter() {

	}

	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public HexagonAdapter(Hexagon hexagon, boolean selected) {
		this.hexagon = hexagon;
		this.hexagon.setSelected(selected);
	}

	public HexagonAdapter(Hexagon hexagon, Color color) {
		this.hexagon = hexagon;
		setColor(color);
	}

	public HexagonAdapter(Hexagon hexagon, Color color, Color inner) {
		this.hexagon = hexagon;
		setInnerColor(inner);

	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		hexagon.paint(g);
	}

	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
	}

	public Color getColor() {
		return hexagon.getBorderColor();
	}

	public void setColor(Color borderColor) {
		hexagon.setBorderColor(borderColor);
	}

	public boolean isSelected() {
		return hexagon.isSelected();
	}

	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public void setRadius(int radius) {
		hexagon.setR(radius);
	}

	public int getX() {
		return hexagon.getX();
	}

	public void setX(int x) {
		hexagon.setX(x);
	}

	public int getY() {
		return hexagon.getY();
	}

	public void setY(int y) {
		hexagon.setY(y);
	}

	public HexagonAdapter clone(HexagonAdapter hexagon) {
		//HexagonAdapter hexagon = new HexagonAdapter();
		hexagon.setX(this.getX());
		hexagon.setY(this.getY());
		hexagon.setRadius(this.getRadius());
		hexagon.setColor(this.getColor());
		hexagon.setInnerColor(this.getInnerColor());
		return hexagon;
	}

}
