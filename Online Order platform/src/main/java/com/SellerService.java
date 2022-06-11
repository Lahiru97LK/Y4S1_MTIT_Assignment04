package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;

import model.Seller; 

@Path("/Seller") 
public class SellerService
{ 
	Seller sellerObj = new Seller(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readResearcher() 
	{ 
		return sellerObj.readSeller(); 
	}
	
//create post ---
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearcher(@FormParam("researcherId") String researcherId,
			@FormParam("name") String name,
			@FormParam("emailaddress") String emailaddress,
			@FormParam("workOnProduct") String workOnProduct,
			@FormParam("productCategory") String productCategory,
			@FormParam("purposeOfResearch") String purposeOfResearch,
			@FormParam("previousProducts") String previousProducts)
			{
			String output = sellerObj.insertSeller(researcherId,name, emailaddress, workOnProduct, productCategory, purposeOfResearch, previousProducts);
			return output;
			}
	
	//create put ----
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearcher(String researcherData)
		{
			//Convert the input string to a JSON object
			JsonObject researcherObject = new JsonParser().parse(researcherData).getAsJsonObject();
	
			//Read the values from the JSON object
			String researcherId = researcherObject.get("researcherId").getAsString();
			String name = researcherObject.get("name").getAsString();
			String emailaddress = researcherObject.get("emailaddress").getAsString();
			String workOnProduct = researcherObject.get("workOnProduct").getAsString();
			String productCategory = researcherObject.get("productCategory").getAsString();
			String purposeOfResearch = researcherObject.get("purposeOfResearch").getAsString();
			String previousProducts= researcherObject.get("previousProducts").getAsString();
			
			String output = sellerObj.updateSeller(researcherId, name, emailaddress, workOnProduct, productCategory, purposeOfResearch,previousProducts);
			return output;
		}
	
	//create delete ---
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearcher(String researcherData)
	{
	
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(SellerData, "", Parser.xmlParser());
	
	//Read the value from the element <researcherId>
	
	String researcherId = doc.select("researcherId").text().toString();
	String output = sellerObj.deleteSeller(researcherId);
	return output;
	}
	
} 
