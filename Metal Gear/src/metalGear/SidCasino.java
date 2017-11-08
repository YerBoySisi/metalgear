package metalGear;

public class SidCasino extends caveExplorer.CaveRoom {

	public SidCasino(String description) {
		super(description);
		String message = "Hey, welcome to Sid's Casino! At this casino we believe losing sucks, so you win no matter what! \n "
				+ "Your roll multiplied by 10 will be added to your wallet.";
		setDescription(message);
	}

}
