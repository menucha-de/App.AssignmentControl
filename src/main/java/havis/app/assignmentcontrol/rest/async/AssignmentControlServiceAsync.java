package havis.app.assignmentcontrol.rest.async;

import havis.app.assignmentcontrol.model.Configuration;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

@Path("../rest/app/assignmentcontrol")
public interface AssignmentControlServiceAsync extends RestService {

	@GET
	@Path("config")
	void getConfig(MethodCallback<Configuration> callback);

	@PUT
	@Path("config")
	void setConfig(Configuration config, MethodCallback<Void> callback);
	
	@PUT
	@Path("accept")
	void sendAccept(MethodCallback<Void> callback);
}