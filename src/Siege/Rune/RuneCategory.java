package Siege.Rune;

import java.util.HashSet;

public enum RuneCategory {
	ATTACK("戦闘"),
	MAGIC("錬金"),
	SWIFT("俊敏"),
	COLLECT("収集");

	String name;

	private RuneCategory(String name) {
		this.name = name;
	}

	public static HashSet<Runes> getRunesAsHashSet(RuneCategory category) {
		HashSet<Runes> runes = Runes.asHashSet(category);
		return runes;
	}

	public String getName() {
		return this.name;
	}
}
