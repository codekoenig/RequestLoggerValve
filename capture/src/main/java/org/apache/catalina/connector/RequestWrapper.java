package org.apache.catalina.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.http.*;

import codekoenig.reqlogvalve.capture.LoggingInputStream;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.Session;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.AsyncContextImpl;
import org.apache.catalina.mapper.MappingData;
import org.apache.catalina.servlet4preview.http.PushBuilder;
import org.apache.catalina.servlet4preview.http.ServletMapping;
import org.apache.tomcat.util.buf.B2CConverter;
import org.apache.tomcat.util.buf.MessageBytes;
import org.apache.tomcat.util.http.ServerCookies;

/**
 *
 * see http://osdir.com/ml/users-tomcat.apache.org/2009-10/msg00075.html
 * see https://issues.apache.org/bugzilla/show_bug.cgi?id=45014
 *
 */
public class RequestWrapper extends Request {
    private static Logger logger = Logger.getLogger(RequestWrapper.class.getName());

    /**
     * The {@link org.apache.catalina.connector.Request Request} being wrapped
     * by this class.
     */
    private Request wrappedCatalinaRequest;

    /**
     * Wrapper to the inner {@link org.apache.catalina.connector.RequestFacade
     * RequestFacade} that is actually used by the container to read the input
     * stream from.
     */
    private LoggingRequest loggingRequest;

    /**
     * Creates a new RequestWrapper with the specified wrapped Request.
     *
     * @param wrapped
     *            The Request being wrapped by this class.
     * @throws IOException IOException
     */
    public RequestWrapper(Request wrapped) throws IOException {
        logger.info("ctor RequestWrapper");
        wrappedCatalinaRequest = wrapped;
        loggingRequest = new LoggingRequest(wrapped);
    }

    /**
     * Gets the body of the request independently from the original's request body stream.
     * @return The body of the request as a String
     */
    public String getBody() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return loggingRequest.getPayload();
    }

    @Override
    public void addCookie(Cookie cookie) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.addCookie(cookie);
    }

    public void addLocale(Locale locale) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.addLocale(locale);
    }

    @Override
    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.authenticate(response);
    }

    @Override
    public void changeSessionId(String arg0) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.changeSessionId(arg0);
    }


    @Override
    public void clearCookies()
    {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.clearCookies();
    }


    @Override
    public void clearLocales() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.clearLocales();
    }

    @Override
    public ServletInputStream createInputStream() throws IOException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.createInputStream();
    }

    @Override
    public boolean equals(Object obj) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.equals(obj);
    }

    @Override
    public void finishRequest() throws IOException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.finishRequest();
    }

    @Override
    public AsyncContext getAsyncContext() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getAsyncContext();
    }

    @Override
    public Object getAttribute(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getAttributeNames();
    }

    @Override
    public String getAuthType() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getAuthType();
    }

    @Override
    public String getCharacterEncoding() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getCharacterEncoding();
    }

    @Override
    public Connector getConnector() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getConnector();
    }

    @Override
    public int getContentLength() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getContentLength();
    }

    @Override
    public long getContentLengthLong() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getContentLengthLong();
    }

    @Override
    public String getContentType() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getContentType();
    }

    @Override
    public Context getContext() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getContext();
    }

    @Override
    public String getContextPath() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getContextPath();
    }

    @Override
    public Cookie[] getCookies() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getCookies();
    }

    @Override
    public org.apache.coyote.Request getCoyoteRequest() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getCoyoteRequest();
    }

    @Override
    protected void addPathParameter(String name, String value) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.addPathParameter(name, value);
    }

    @Override
    protected void recycleSessionInfo() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.recycleSessionInfo();
    }

    @Override
    protected B2CConverter getURIConverter() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getURIConverter();
    }

    @Override
    public boolean isAsyncDispatching() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isAsyncDispatching();
    }

    @Override
    public boolean isAsyncCompleting() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isAsyncCompleting();
    }

    @Override
    protected int readPostBody(byte[] body, int len) throws IOException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.readPostBody(body, len);
    }

    @Override
    protected void parseLocalesHeader(String value, TreeMap<Double, ArrayList<Locale>> locales) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.parseLocalesHeader(value, locales);
    }

    @Override
    protected void parseLocales() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.parseLocales();
    }

    @Override
    protected byte[] readChunkedPostBody() throws IOException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.readChunkedPostBody();
    }

    @Override
    protected void parseParameters() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.parseParameters();
    }

    @Override
    protected void convertCookies() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.convertCookies();
    }

    @Override
    protected void parseCookies() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.parseCookies();
    }

    @Override
    protected String unescape(String s) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.unescape(s);
    }

    @Override
    protected Session doGetSession(boolean create) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.doGetSession(create);
    }

    @Override
    protected void checkSwallowInput() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.checkSwallowInput();
    }

    @Override
    public boolean isFinished() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isFinished();
    }

    @Override
    public boolean isParametersParsed() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isParametersParsed();
    }

    @Override
    public String changeSessionId() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.changeSessionId();
    }

    @Override
    public ServerCookies getServerCookies() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getServerCookies();
    }

    @Override
    public void setRequestedSessionSSL(boolean flag) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setRequestedSessionSSL(flag);
    }

    @Override
    public AsyncContextImpl getAsyncContextInternal() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getAsyncContextInternal();
    }

    @Override
    public boolean isAsync() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isAsync();
    }

    @Override
    public void setLocalPort(int port) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setLocalPort(port);
    }

    @Override
    protected void setURIConverter(B2CConverter URIConverter) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setURIConverter(URIConverter);
    }

    @Override
    public void setFilterChain(FilterChain filterChain) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setFilterChain(filterChain);
    }

    @Override
    protected void recycleCookieInfo(boolean recycleCoyote) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.recycleCookieInfo(recycleCoyote);
    }

    @Override
    public void setAsyncSupported(boolean asyncSupported) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setAsyncSupported(asyncSupported);
    }

    @Override
    protected String getPathParameter(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getPathParameter(name);
    }

    @Override
    public long getDateHeader(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getDateHeader(name);
    }

    @Override
    public String getDecodedRequestURI() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getDecodedRequestURI();
    }

    @Override
    public MessageBytes getDecodedRequestURIMB() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getDecodedRequestURIMB();
    }

    @Override
    public DispatcherType getDispatcherType() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getDispatcherType();
    }

    @Override
    public FilterChain getFilterChain() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getFilterChain();
    }

    @Override
    public String getHeader(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getHeaderNames();
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getHeaders(name);
    }

    @Override
    public Host getHost() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getHost();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getInputStream();
    }

    @Override
    public int getIntHeader(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getIntHeader(name);
    }

    @Override
    public String getLocalAddr() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getLocalAddr();
    }

    @Override
    public String getLocalName() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getLocalName();
    }

    @Override
    public int getLocalPort() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getLocalPort();
    }

    @Override
    public Locale getLocale() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getLocale();
    }

    @Override
    public Enumeration<Locale> getLocales() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getLocales();
    }

    @Override
    public MappingData getMappingData() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getMappingData();
    }

    @Override
    public String getMethod() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getMethod();
    }

    @Override
    public Object getNote(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getNote(name);
    }

    @Override
    public String getParameter(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getParameterMap();
    }

    @Override
    public Enumeration<String> getParameterNames() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getParameterValues(name);
    }

    @Override
    public Part getPart(String name) throws IOException, ServletException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getPart(name);
    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getParts();
    }

    @Override
    public String getPathInfo() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getPathInfo();
    }

    @Override
    public String getPathTranslated() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getPathTranslated();
    }

    @Override
    public Principal getPrincipal() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getPrincipal();
    }

    @Override
    public String getProtocol() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getProtocol();
    }

    @Override
    public String getQueryString() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getQueryString();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        logger.info("RequestWrapper.getReader()");
        return wrappedCatalinaRequest.getReader();
    }

    @Override
    @SuppressWarnings("deprecation")
    public String getRealPath(String arg0) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getRealPath(arg0);
    }

    @Override
    public String getRemoteAddr() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getRemoteAddr();
    }

    @Override
    public String getRemoteHost() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getRemoteHost();
    }

    @Override
    public int getRemotePort() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getRemotePort();
    }

    @Override
    public String getRemoteUser() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getRemoteUser();
    }

    @Override
    public HttpServletRequest getRequest() {
        // here is where the actual request used to read from is retrieved
        logger.info("getRequest()");
        return loggingRequest;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getRequestDispatcher(path);
    }

    @Override
    public MessageBytes getRequestPathMB() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getRequestPathMB();
    }

    @Override
    public String getRequestURI() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getRequestURI();
    }

    @Override
    public StringBuffer getRequestURL() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getRequestURL();
    }

    @Override
    public String getRequestedSessionId() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getRequestedSessionId();
    }

    @Override
    public Response getResponse() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getResponse();
    }

    @Override
    public String getScheme() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getScheme();
    }

    @Override
    public String getServerName() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getServerName();
    }

    @Override
    public int getServerPort() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getServerPort();
    }

    @Override
    public ServletContext getServletContext() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getServletContext();
    }

    @Override
    public String getServletPath() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getServletPath();
    }

    @Override
    public HttpSession getSession() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getSession();
    }

    @Override
    public HttpSession getSession(boolean create) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getSession(create);
    }

    @Override
    public Session getSessionInternal() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getSessionInternal();
    }

    @Override
    public Session getSessionInternal(boolean create) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getSessionInternal(create);
    }

    @Override
    public InputStream getStream() {
        logger.info("RequestWrapper.getStream()");
        return wrappedCatalinaRequest.getStream();
    }

    @Override
    public Principal getUserPrincipal() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getUserPrincipal();
    }

    @Override
    public Wrapper getWrapper() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getWrapper();
    }

    @Override
    public int hashCode() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.hashCode();
    }

    @Override
    public boolean isAsyncStarted() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isAsyncStarted();
    }

    @Override
    public boolean isAsyncSupported() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isAsyncSupported();
    }


    @Override
    public boolean isRequestedSessionIdFromCookie() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isRequestedSessionIdFromCookie();
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isRequestedSessionIdFromURL();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isRequestedSessionIdFromUrl() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isRequestedSessionIdFromUrl();
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isRequestedSessionIdValid();
    }

    @Override
    public boolean isSecure() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isSecure();
    }

    @Override
    public boolean isUserInRole(String role) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.isUserInRole(role);
    }

    @Override
    public void login(String username, String password) throws ServletException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.login(username, password);
    }

    @Override
    public void logout() throws ServletException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.logout();
    }

    @Override
    public ServletMapping getServletMapping() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.getServletMapping();
    }

    @Override
    public PushBuilder newPushBuilder() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.newPushBuilder();
    }

    @Override
    public void setRequest(HttpServletRequest applicationRequest) {
        logger.info("setRequest()");
        wrappedCatalinaRequest.setRequest(applicationRequest);
    }

    @Override
    public void recycle() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.recycle();
    }

    @Override
    public void removeAttribute(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.removeAttribute(name);
    }

    @Override
    public void removeNote(String name) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.removeNote(name);
    }


    @Override
    public void setAttribute(String arg0, Object arg1) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setAttribute(arg0, arg1);
    }

    @Override
    public void setAuthType(String type) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setAuthType(type);
    }

    @Override
    public void setCharacterEncoding(String enc) throws UnsupportedEncodingException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setCharacterEncoding(enc);
    }

    @Override
    public void setConnector(Connector arg0) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setConnector(arg0);
    }


    @Override
    public void setContentType(String type) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setContentType(type);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setContext(Context context) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setContext(context);
    }

    @Override
    public void setCoyoteRequest(org.apache.coyote.Request coyoteRequest) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setCoyoteRequest(coyoteRequest);
    }

    @Override
    public void setNote(String name, Object value) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setNote(name, value);
    }

    @Override
    public void setPathInfo(String path) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setPathInfo(path);
    }

    @Override
    public void setRemoteAddr(String remoteAddr) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setRemoteAddr(remoteAddr);
    }

    @Override
    public void setRemoteHost(String remoteHost) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setRemoteHost(remoteHost);
    }

    @Override
    public void setRequestedSessionCookie(boolean flag) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setRequestedSessionCookie(flag);
    }

    @Override
    public void setRequestedSessionId(String id) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setRequestedSessionId(id);
    }

    @Override
    public void setRequestedSessionURL(boolean flag) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setRequestedSessionURL(flag);
    }

    @Override
    public void setResponse(Response response) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setResponse(response);
    }

    @Override
    public void setSecure(boolean secure) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setSecure(secure);
    }

    @Override
    public void setServerPort(int port) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setServerPort(port);
    }

    @Override
    public void setUserPrincipal(Principal principal) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setUserPrincipal(principal);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setWrapper(Wrapper wrapper) {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        wrappedCatalinaRequest.setWrapper(wrapper);
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.startAsync();
    }

    @Override
    public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) throws IllegalStateException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.startAsync(arg0, arg1);
    }

    @Override
    public String toString() {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.toString();
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass) throws ServletException, IOException {
        logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
        return wrappedCatalinaRequest.upgrade(httpUpgradeHandlerClass);
    }

    /**
     * Wraps the inner request, a
     * {@link org.apache.catalina.connector.RequestFacade RequestFacade} object,
     * that is wrapped by the outer request, a
     * {@link org.apache.catalina.connector.Request Request} object, that we are
     * wrapping. So keep in mind that there are two layers of wrapping done by
     * the Tomcat API. It is the inner request object that we have to extend to
     * capture the input stream from.
     *
     */
    private class LoggingRequest extends RequestFacade {

        private LoggingInputStream is;

        LoggingRequest(Request request) throws IOException {
            super(request);

            logger.info("ctor LoggingRequest");

            int len = 0;
            try {
                len = Integer.parseInt(request.getHeader("content-length"));
            } catch (NumberFormatException e) {
                // ignore and assume 0 length
            }

            String contentType = request.getHeader("content-type");
            String charset = null;

            if (contentType != null) {
                for (String ct : contentType.split(";")) {
                    String s = ct.trim();
                    if (s.startsWith("charset")) {
                        charset = s.substring(s.indexOf("=") + 1);
                        break;
                    }
                }
            }

            is = new LoggingInputStream(request.getRequest().getInputStream(), len, charset);
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            logger.info("LoggingRequest.getInputStream()");
            return is;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            logger.info("LoggingRequest.getReader()");
            return super.getReader();
        }

        String getPayload() {
            logger.info("Method: " + new Object() {}.getClass().getEnclosingMethod().getName());
            return is.getPayload();
        }
    }

}
