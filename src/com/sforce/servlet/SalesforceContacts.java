package com.sforce.servlet;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class SalesforceContacts {

	static final String USERNAME = "sudha@finalhw.com";
	static final String PASSWORD = "@lt12345mCBJRd4FsBpqlLhvZTfGbRn5";
	static EnterpriseConnection connection;
	public SObject[] getContactsList() {
		ConnectorConfig config = new ConnectorConfig();
		config.setUsername(USERNAME);
		config.setPassword(PASSWORD);
		SObject[] records =null;
		try {
			config.setAuthEndpoint("https://login.salesforce.com/services/Soap/c/32.0/");
			connection = Connector.newConnection(config);
			// display some current settings
			System.out.println("Auth EndPoint:"+config.getAuthEndpoint());
			System.out.println("Service EndPoint:"+config.getServiceEndpoint());
			System.out.println("Username: "+config.getUsername());
			System.out.println("SessionId: "+config.getSessionId());
			QueryResult qr = connection.queryAll("select Name from Contact");
			System.out.println(qr.getSize());
			boolean done = false;
			if (qr.getSize() > 0) {
				while (!done) {
		             records = qr.getRecords();
		            if (qr.isDone()) {
		               done = true;
		            } else {
		               qr = connection.queryMore(qr.getQueryLocator());
		            }
				}
	      } else {
	         System.out.println("No records found.");
	      }	
		} catch (ConnectionException e1) {
			e1.printStackTrace();
		}
		return records;
	}
	
	//Test
	public static void main(String[] args) {
		SalesforceContacts contacts = new SalesforceContacts();
		SObject[] records = contacts.getContactsList();
		 for (int i = 0; i < records.length; i++) {
             Contact contact = (Contact) records[i];
//             boolean isDel = contact.getIsDeleted();
             System.out.println("Contact " + (i + 1) + ": "
                   + contact.getName() 
//                   + " isDeleted = "
//                   + contact.getIsDeleted()
                   );
          }
		
	}

}
