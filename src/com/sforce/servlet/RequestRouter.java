package com.sforce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.SObject;

/**
 * Servlet implementation class RequestRouter
 */
public class RequestRouter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestRouter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.print("List of Contacts added in Salesforce" + "\n");
		
		SObject[] records = new SalesforceContacts().getContactsList();
		 for (int i = 0; i < records.length; i++) {
             Contact contact = (Contact) records[i];
             out.print("Contact " + (i + 1) + ": "
                  + contact.getName() + "\n");
          }
		
		
		
	}

}
