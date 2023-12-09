package command;

import geometry.Line;

public class UpdateLineCmd implements Command{
	private Line line;
	private Line newState;
	private Line temp = new Line();

	public UpdateLineCmd(Line line, Line newState) {
		this.line = line;
		this.newState = newState;
	}

	@Override
	public void execute() {
		temp.getStartPoint().setX(line.getStartPoint().getX());
		temp.getStartPoint().setY(line.getStartPoint().getY());
		temp.getEndPoint().setX(line.getEndPoint().getX());
		temp.getEndPoint().setY(line.getEndPoint().getY());
		temp.setColor(line.getColor());

		line.getStartPoint().setX(newState.getStartPoint().getX());
		line.getStartPoint().setY(newState.getStartPoint().getY());
		line.getEndPoint().setX(newState.getEndPoint().getX());
		line.getEndPoint().setY(newState.getEndPoint().getY());
		line.setColor(newState.getColor());
	}

	@Override
	public void unexecute() {
		line.getStartPoint().setX(temp.getStartPoint().getX());
		line.getStartPoint().setY(temp.getStartPoint().getY());
		line.getEndPoint().setX(temp.getEndPoint().getX());
		line.getEndPoint().setY(temp.getEndPoint().getY());
		line.setColor(temp.getColor());

	}

}