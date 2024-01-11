package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/hello")
public class GreetingResource {

    @RestClient
    ExampleRestClient restClient;

    @RestClient
    GoogleRestClient googleRestClient;

    @GET()
    @Path("self-call")
    @Produces(MediaType.TEXT_PLAIN)
    public Response callOwnApi() {
        try (var restClientResponse = restClient.selfCall()) {
            //do nothing with it
        }

        return Response
                .status(200).build();
    }

    @GET
    @Path("google-call")
    @Produces(MediaType.TEXT_PLAIN)
    public Response callGoogle() {
        try (var googleClientResponse = googleRestClient.call()) {
            //do nothing with it
        }

        return Response
                .status(402).build();
    }


    @GET
    @Path("402")
    public Response return402() {
        return Response.status(402).entity("{}").build();
    }

    @RegisterRestClient(baseUri = "http://localhost:8081")
    public interface ExampleRestClient {
        @GET
        @Path("/hello/402")
        Response selfCall();
    }


    @RegisterRestClient(baseUri = "https://www.google.com")
    public interface GoogleRestClient {
        @GET
        Response call();
    }
}
