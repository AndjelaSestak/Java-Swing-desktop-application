package command;

import geometry.Point;

public class UpdatePointCmd implements Command{
	private Point point;
	private Point newState;
	private Point temp = new Point();

	public UpdatePointCmd(Point point, Point newState) {
		this.point = point;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// point = newState;
		/*temp.setX(point.getX());
		temp.setY(point.getY());
		temp.setColor(point.getColor());

		point.setX(newState.getX());
		point.setY(newState.getY());
		point.setColor(newState.getColor());*/
		temp=point.clone(temp);
		point=newState.clone(point);
	}

	@Override
	public void unexecute() {
		/*point.setX(temp.getX());
		point.setY(temp.getY());
		point.setColor(temp.getColor());*/
		point=temp.clone(point);
	}
}
