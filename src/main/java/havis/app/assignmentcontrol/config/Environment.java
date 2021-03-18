package havis.app.assignmentcontrol.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AssignmentControl environment properties.
 * 
 */
public class Environment {

	private final static Logger log = Logger.getLogger(Environment.class.getName());

	private final static Properties properties = new Properties();

	static {
		try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Environment.properties")) {
			properties.load(stream);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Failed to load environment properties", e);
		}
	}

	/**
	 * Switch JSON Format
	 */
	@Deprecated
	final static Boolean GATE_CONTROL = getBoolean(properties.getProperty("havis.app.assignmentcontrol.gate.control","false"));
	
	/**
	 * Time after a given Tag should be delete from tag list.
	 * 
	 * value < 1 --> do not start timer
	 * value > 0 --> delete tag from the list after elapsed time.
	 */
	final static Integer TAG_TIMEOUT = getInteger(properties.getProperty("havis.app.assignmentcontrol.tag.timeout", "-1"));
	
	/**
	 * REST Address of remote assignment database.
	 */
	final static URL ASSIGNMENT_URI = getURL(properties.getProperty("havis.app.assignmentcontrol.assignment.uri", "http://user:password@localhost:0/rest-assignment-path"));

	/**
	 * HTTP Connection timeout in milliseconds.
	 */
	final static Integer HTTP_CONNECTION_TIMEOUT = getInteger(properties.getProperty("havis.app.assignmentcontrol.http.connection.timeout", "10000"));

	/**
	 * Location name.
	 */
	final static String LOCATION_NAME = properties.getProperty("havis.app.assignmentcontrol.location.name", "DUMMY");

	/**
	 * Location id. The id must exists in the remote assignment database.
	 */
	final static Integer LOCATION_ID = getInteger(properties.getProperty("havis.app.assignmentcontrol.location.id", "0"));

	/**
	 * Delay time, after the Cycle should be stopped, after the first or second
	 * Inputs switches to off position.
	 */
	final static Long TRIGGER_HOLD_TIME = getLong(properties.getProperty("havis.app.assignmentcontrol.trigger.hold.time", "0"));

	/**
	 * Indicates whether the location has scan triggers or not.
	 */
	final static boolean SCAN_TRIGGER = getBoolean(properties.getProperty("havis.app.assignmentcontrol.scan.trigger", "true"));

	/**
	 * Indicates whether the location has an 'Actite Switch' or not.
	 */
	final static boolean ACTIVE_SWITCH_AVAILABLE = getBoolean(properties.getProperty("havis.app.assignmentcontrol.active.switch", "false"));

	/**
	 * HTTP-Trigger sets the OutputPort 'HS1' 'high'. ALE Middleware started up.
	 */
	final static URL PC_OUT_READY_URL = getURL(properties.getProperty("havis.app.assignmentcontrol.pc.out.ready.url", "http://localhost:8888/services/ALE/trigger/Ready"));

	/**
	 * HTTP-Trigger sets "ACTIVE" Output to 'high'
	 */
	final static URL PC_OUT_ACTIVE_URL = getURL(properties.getProperty("havis.app.assignmentcontrol.pc.out.active.url", "http://localhost:8888/services/ALE/trigger/Active"));

	/**
	 * HTTP-Trigger sets "ACTIVE" Output to 'low'
	 */
	final static URL PC_OUT_INACTIVE_URL = getURL(properties.getProperty("havis.app.assignmentcontrol.pc.out.inactive.url", "http://localhost:8084/services/ALE/trigger/Inactive"));

	/**
	 * HTTP-Trigger sets "ACTIVE" Output to 'low'
	 */
	final static URL PC_OUT_SEND_ACCEPT_URL = getURL(properties.getProperty("havis.app.assignmentcontrol.pc.out.sendaccept.url", "http://localhost:8084/services/ALE/trigger/SendAccept"));

	/**
	 * HTTP-Trigger stops the EventCycle.
	 */
	final static URL PC_STOP_SCAN_URL = getURL(properties.getProperty("havis.app.assignmentcontrol.pc.stopscan.url", "http://localhost:8888/service/ALE/trigger/StopScan"));

	/**
	 * HTTP-Trigger starts the EventCycle.
	 */
	final static URL PC_START_SCAN_URL = getURL(properties.getProperty("havis.app.assignmentcontrol.pc.startscan.url", "http://localhost:8888/service/ALE/trigger/StartScan"));

	/**
	 * HTTP-Trigger starts observing.
	 */
	final static URL PC_OBSERVE_URL = getURL(properties.getProperty("havis.app.assignmentcontrol.pc.observe.url", "http://localhost:8888/service/ALE/trigger/GetInputs"));
	
	public final static String TRIGGER_1 = "Trigger1";

	public final static String TRIGGER_2 = "Trigger2";

	public final static String ACTIVE_SWITCH = "ActiveSwitch";

	public static final String CONFIG_FILE = properties.getProperty("havis.app.assignmentcontrol.configurationFile",
			"conf/havis/app/assignmentcontrol/configCopy.json");

	public static final String LOCK = properties.getProperty("havis.app.assignmentcontrol.lock", "conf/havis/app/assignmentcontrol/lock");
	public static final String SPEC = properties.getProperty("havis.app.assignmentcontrol.spec", "conf/havis/app/assignmentcontrol/spec");
	
	private final static URL getURL(String url) {
		if ((url != null) && (url.trim().length() > 0)) {
			try {
				return new URL(url);
			} catch (MalformedURLException mue) {
				log.log(Level.WARNING, "Load Environment URL value", mue);
				return null;
			}
		} else {
			return null;
		}
	}

	private final static Long getLong(String longval) {
		if ((longval != null) && (longval.trim().length() > 0)) {
			try {
				return Long.parseLong(longval);
			} catch (NumberFormatException nfe) {
				log.log(Level.WARNING, "Load Environment Long value", nfe);
				return null;
			}
		} else {
			return null;
		}
	}

	private final static Integer getInteger(String integer) {
		if ((integer != null) && (integer.trim().length() > 0)) {
			try {
				return Integer.parseInt(integer);
			} catch (NumberFormatException nfe) {
				log.log(Level.WARNING, "Load Environment Integer value", nfe);
				return null;
			}
		} else {
			return null;
		}
	}

	private final static boolean getBoolean(String bool) {
		if ((bool != null) && (bool.trim().length() > 0)) {
			try {
				return Boolean.parseBoolean(bool);
			} catch (NumberFormatException nfe) {
				log.log(Level.WARNING, "Load Environment Boolean value", nfe);
				return false;
			}
		} else {
			return false;
		}
	}
}
