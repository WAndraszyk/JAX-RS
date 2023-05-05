/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author wandr
 */
public class ComplaintsClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        List<ComplaintDTO> complaints
                = client.target("http://localhost:8080/Complaints/"
                        + "resources/complaints")
                        .request(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<ComplaintDTO>>() {
            });

        System.out.println("ALL COMPLAINTS:");
        for (ComplaintDTO complaint : complaints) {
            System.out.println(complaint.toString());
        }

        ComplaintDTO openComplaint
                = client.target("http://localhost:8080/Complaints/"
                        + "resources/complaints/502")
                        .request(MediaType.APPLICATION_JSON)
                        .get(ComplaintDTO.class);

        System.out.println("\nCHOSEN OPEN COMPLAINT:");
        System.out.println(openComplaint.toString());
        
        openComplaint.setStatus("closed");
        
        Response response = client.target("http://localhost:8080/Complaints/"
                        + "resources/complaints/502")
                        .request(MediaType.APPLICATION_JSON)
                        .put(Entity.json(openComplaint.toString()));

        List<ComplaintDTO> openComplaints
                = client.target("http://localhost:8080/Complaints/"
                        + "resources/complaints?status=open")
                        .request(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<ComplaintDTO>>() {
            });

        System.out.println("\nOPEN COMPLAINTS:");
        for (ComplaintDTO complaint : openComplaints) {
            System.out.println(complaint.toString());
        }
        
        client.close();
    }
}
