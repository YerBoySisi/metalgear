package metalGear;

public class BensCafe extends caveExplorer.CaveRoom{

	private String msg = "This is Bens Cafe";
	
	public BensCafe(String description) {
		super(description);
		// this is dumb
		setDescription(description + msg);
	}

}
