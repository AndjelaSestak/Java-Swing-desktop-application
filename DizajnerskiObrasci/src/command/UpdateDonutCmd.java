package command;

import geometry.Donut;

public class UpdateDonutCmd implements Command {
	private Donut donut;
	private Donut newState;
	private Donut temp = new Donut();

	public UpdateDonutCmd(Donut donut, Donut newState) {
		this.donut = donut;
		this.newState = newState;
	}

	@Override
	public void execute() {
		/*temp.getCenter().setX(donut.getCenter().getX());
		temp.getCenter().setY(donut.getCenter().getY());
		try {
			temp.setRadius(donut.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp.setInnerRadius(donut.getInnerRadius());
		temp.setColor(donut.getColor());
		temp.setInnerColor(donut.getInnerColor());

		donut.getCenter().setX(newState.getCenter().getX());
		donut.getCenter().setY(newState.getCenter().getY());
		try {
			donut.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setInnerRadius(newState.getInnerRadius());
		donut.setColor(newState.getColor());
		donut.setInnerColor(newState.getInnerColor());*/
		temp=donut.clone(temp);
		donut=newState.clone(donut);
	}

	@Override
	public void unexecute() {
		/*donut.getCenter().setX(temp.getCenter().getX());
		donut.getCenter().setY(temp.getCenter().getY());
		try {
			donut.setRadius(temp.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setInnerRadius(temp.getInnerRadius());
		donut.setColor(temp.getColor());
		donut.setInnerColor(temp.getInnerColor());*/
		donut=temp.clone(donut);
	}

}
