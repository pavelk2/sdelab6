package introsde.rest.ehealth.resources;

import introsde.rest.ehealth.dao.PersonDao;
import introsde.rest.ehealth.model.Person;

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

public class PersonResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    Long id;

    public PersonResource(UriInfo uriInfo, Request request,Long id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }
    // Application integration
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Person getPerson() {
        Person person = PersonDao.instance.getDataProvider().get(id);
        if (person == null)
            throw new RuntimeException("Get: Person with " + id + " not found");
        return person;
    }
    @PUT
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response putPerson(Person person) {
        if (PersonDao.instance.getDataProvider().containsKey(this.id)) {
            person.setPersonId(this.id);
            PersonDao.instance.getDataProvider().put(person.getPersonId(), person);
            return Response.ok(uriInfo.getAbsolutePath()).build();
        } else {
            return Response.noContent().build();
        }
    }
    @DELETE
    public void deletePerson() {
        Person c = PersonDao.instance.getDataProvider().remove(id);
        if (c == null)
            throw new RuntimeException("Delete: Person with " + id
                    + " not found");
    }
}