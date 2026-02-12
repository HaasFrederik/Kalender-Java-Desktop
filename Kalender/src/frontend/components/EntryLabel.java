package frontend.components;

import backend.entries.Entry;

public class EntryLabel extends MyLabel {

	public Entry entry;
	
	public EntryLabel(Entry e) {
		super(e.toString());
		entry = e;
	}
	
}
