package command;

import geometry.Circle;

public class UpdateCircleCmd implements Command{
	private Circle circle;
	private Circle newState;
	private Circle temp = new Circle();

	public UpdateCircleCmd(Circle circle, Circle newState) {
		this.circle = circle;
		this.newState = newState;
	}

	@Override
	public void execute() {
		/*temp.getCenter().setX(circle.getCenter().getX());
		temp.getCenter().setY(circle.getCenter().getY());
		try {
			temp.setRadius(circle.getRadius());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		temp.setColor(circle.getColor());
		temp.setInnerColor(circle.getInnerColor());
		
		circle.getCenter().setX(newState.getCenter().getX());
		circle.getCenter().setY(newState.getCenter().getY());
		try {
			circle.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.setColor(newState.getColor());
		circle.setInnerColor(newState.getInnerColor());*/
		temp=circle.clone(temp);
		circle=newState.clone(circle);
	}

	@Override
	public void unexecute() {
		/*circle.getCenter().setX(temp.getCenter().getX());
		circle.getCenter().setY(temp.getCenter().getY());
		try {
			circle.setRadius(temp.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.setColor(temp.getColor());
		circle.setInnerColor(temp.getInnerColor());*/
		circle=temp.clone(circle);
	}
	
	public String toString()
	{
		return "Modify circle " + circle.toString();
	}

}
