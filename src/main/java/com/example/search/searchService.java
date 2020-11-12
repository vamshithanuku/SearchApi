package com.example.search;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.ebay.api.client.auth.oauth2.CredentialUtil;
import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.Environment;
import com.ebay.api.client.auth.oauth2.model.OAuthResponse;
import org.springframework.web.client.RestTemplate;

@Service
public class searchService {
	@Autowired
	private searchProductRepo spr;
	OAuth2Api oauth2Api = new OAuth2Api();

	private List<searchProduct> products = new ArrayList<searchProduct>();

   public searchService() throws FileNotFoundException
   {
	   try
	   {
		   CredentialUtil.load(new FileInputStream("/Users/vamshikrishna/Desktop/SearchApi/src/main/resources/application.yaml"));
	   }
	   catch(FileNotFoundException e)
	   {
		   throw e;
	   }
    }

	public List<searchProduct> getAllProducts()
	{
		spr.findAll().forEach(products::add);
		return products;
		
	}
	
	public List<searchProduct> getProduct(String url) throws IOException
	{
		searchProduct sp = new searchProduct("http://google.com", "Google", "100");
		List<String> scopes = new ArrayList<String>();
		String scope =  "https://api.ebay.com/oauth/api_scope";
		scopes.add(scope);
		Environment environment = Environment.SANDBOX;
		OAuthResponse oAuth = oauth2Api.getApplicationToken(environment, scopes);

		String theUrl = "https://api.sandbox.ebay.com/buy/browse/v1/item_summary/search?q="+url+"&limit=3";
		String token1 =  "Bearer "+oAuth.getAccessToken().get().getToken();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", token1);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response1 = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class);

		JSONObject obj = new JSONObject(response1.getBody());
		List<searchProduct> products = new ArrayList<searchProduct>();

		JSONArray arr = obj.getJSONArray("itemSummaries");
		for (int i = 0; i < arr.length(); i++) {
			String title = arr.getJSONObject(i).getString("title");
			String imageName = arr.getJSONObject(i).getString("itemHref");
			String price = arr.getJSONObject(i).getJSONObject("price").getString("value");
			searchProduct searchProduct = new searchProduct(imageName, title, price);
			products.add(searchProduct);
		}
		return products;
	}

	public void addProduct(searchProduct product) {
		products.add(product);
	}
}
