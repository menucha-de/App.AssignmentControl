package havis.app.assignmentcontrol;

import havis.app.assignmentcontrol.model.Tag;

import java.util.List;

/**
 * EC report listener
 * 
 */
public interface EventCycleListener {
	/**
	 * Will be called each time by {@link ECThread} on EC report.
	 *
	 * @param newTagsAvailable
	 *            new tags in field
	 * @param {@link Tag}
	 */
	void onEcReport(boolean newTagsAvailable, List<Tag> tags);
}
