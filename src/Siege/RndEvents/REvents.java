package Siege.RndEvents;

import java.util.ArrayList;
import java.util.Collections;

public enum REvents {
	CORE2X(0, 180),
	BONUSCHEST3X(0, 120),
	;

	private int announceTime;
	private int time;
	private boolean active;

	private REvents(int announceTime, int time) {
		active = false;
		this.announceTime = announceTime;
		this.time = time;
	}

	public static REvents getRandom() {
		ArrayList<REvents> events = new ArrayList<>();
		for (REvents re : REvents.values()) {
			events.add(re);
		}
		Collections.shuffle(events);
		return events.get(0);
	}

	public int getAnnounceTime() {
		return announceTime;
	}

	public void setAnnounceTime(int announceTime) {
		this.announceTime = announceTime;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void active() {
		this.active = true;
	}
}
