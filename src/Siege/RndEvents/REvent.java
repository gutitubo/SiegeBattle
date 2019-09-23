package Siege.RndEvents;

public class REvent {

	private REvents event;

	public REvents getEvent() {
		return event;
	}

	public void setEvent(REvents event) {
		this.event = event;
	}

	public REvent() {
		this.event = REvents.getRandom();
	}

}