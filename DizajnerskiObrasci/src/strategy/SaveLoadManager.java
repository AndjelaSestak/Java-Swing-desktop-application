package strategy;

public class SaveLoadManager implements SaveLoad {

	private SaveLoad saveLoad;

	public SaveLoadManager(SaveLoad saveLoad) {
		this.saveLoad = saveLoad;
	}

	@Override
	public void saveData(String filePath) {
		// TODO Auto-generated method stub
		this.saveLoad.saveData(filePath);
	}

	@Override
	public void loadData(String filePath) {
		// TODO Auto-generated method stub
		this.saveLoad.loadData(filePath);
	}

}
