package command;

import adapter.HexagonAdapter;

public class UpdateHexagonCmd implements Command{

	private HexagonAdapter temp = new HexagonAdapter();
	private HexagonAdapter originalHexagon;
	private HexagonAdapter newState;
	
	public UpdateHexagonCmd(HexagonAdapter originalHexagon, HexagonAdapter newState)
	{
		this.originalHexagon=originalHexagon;
		this.newState = newState;
	}
	@Override
	public void execute() {
		/*temp.setX(originalHexagon.getX());
		temp.setY(originalHexagon.getY());
		temp.setRadius(originalHexagon.getRadius());
		temp.setInnerColor(originalHexagon.getInnerColor());
		temp.setColor(originalHexagon.getColor());
		
		originalHexagon.setX(newState.getX());
		originalHexagon.setY(newState.getY());
		originalHexagon.setRadius(newState.getRadius());
		originalHexagon.setInnerColor(newState.getInnerColor());
		originalHexagon.setColor(newState.getColor());
		*/
		temp=originalHexagon.clone(temp);
		originalHexagon=newState.clone(originalHexagon);
	}

	@Override
	public void unexecute() {
		/*originalHexagon.setX(temp.getX());
		originalHexagon.setY(temp.getY());
		originalHexagon.setRadius(temp.getRadius());
		originalHexagon.setInnerColor(temp.getInnerColor());
		originalHexagon.setColor(temp.getColor());*/
		originalHexagon=temp.clone(originalHexagon);
		
	}

}
