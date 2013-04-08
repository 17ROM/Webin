package com.webin.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WebinMain
 */
@WebServlet("/WeChat")
public class WebinMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MessageFactory mMessageFactory;
	private WebinAuthorize mWebinAuthorize;

    /**
     * Default constructor. 
     */
    public WebinMainServlet() {
    	super();
    	mWebinAuthorize = WebinAuthorize.getInstance();
    	mMessageFactory = MessageFactory.getInstance();
    	//mMessageFactory.start();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (mMessageFactory != null) {
			mMessageFactory.handleMessage(request.getInputStream(), response.getWriter());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (mWebinAuthorize != null){
			mWebinAuthorize.doAuthorize(req, resp.getWriter());
		}
	}
	
	
}
