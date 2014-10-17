package introsde.rest.ehealth.resources;

import introsde.rest.ehealth.dao.PersonDao;
import introsde.rest.ehealth.model.Person;
import introsde.rest.ehealth.model.HealthProfile;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

public class HealthProfileResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    Long id;

    public HealthProfileResource(UriInfo uriInfo, Request request,Long id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }
    // Application integration
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public HealthProfile getHealthProfile() {
        Person person = PersonDao.instance.getDataProvider().get(id);
        if (person == null)
            throw new RuntimeException("Get: Person with " + id + " not found");
        return person.getHProfile();
    }
}