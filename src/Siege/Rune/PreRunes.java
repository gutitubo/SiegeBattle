package Siege.Rune;

public class PreRunes {

	private Runes[] runes;
	private RuneCategory main;
	private RuneCategory sub;

	public PreRunes() {
		runes = new Runes[5];
	}

	public Runes[] getRunes() {
		return runes;
	}

	public void setRunes(Runes[] runes) {
		this.runes = runes;
	}

	public RuneCategory getMain() {
		return main;
	}

	public void setMain(RuneCategory main) {
		this.main = main;
	}

	public RuneCategory getSub() {
		return sub;
	}

	public void setSub(RuneCategory sub) {
		this.sub = sub;
	}

	public void clear() {
		runes = new Runes[5];
		main = null;
		sub = null;
	}

}
