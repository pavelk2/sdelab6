package introsde.rest.ehealth.resources;

import introsde.rest.ehealth.dao.PersonDao; // use it to access the data providers
import introsde.rest.ehealth.model.Person; // use it to manage person data

import java.io.IOException;
import java.util.ArrayList; // use it to store the list of Person to return 
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import introsde.rest.ehealth.resources.PersonResource;
import introsde.rest.ehealth.resources.HealthProfileResource;

//Will map the resource to the URL /person
@Path("/person")
public class PersonCollectionResource {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of people to the user in the browser
	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_JSON })
	public List<Person> getPersonsList() {
		List<Person> people = new ArrayList<Person>();
		people.addAll(PersonDao.instance.getDataProvider().values());
		return people;
	}
	
    @POST  
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Person newPerson(Person person) throws IOException {
        int count = PersonDao.instance.getDataProvider().size();
        Long newId = new Long(count+1);
        person.setPersonId(newId);
        PersonDao.instance.getDataProvider().put(newId, person);
        return person;
    }

	// Defines that the next path parameter after the base url is
	// treated as a parameter and passed to the PersonResources
	// Allows to type http://localhost:599/person/1
	// 1 will be treaded as parameter todo and passed to PersonResource
	@Path("{personId}")
	public PersonResource getPerson(@PathParam("personId") Long id) {
		return new PersonResource(uriInfo, request, id);
		
	}
	@Path("{personId}/healthprofile")
	public HealthProfileResource getHealthProfile(@PathParam("personId") Long id) {
		return new HealthProfileResource(uriInfo, request, id);
	}
	
}
