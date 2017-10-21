RequestLoggerValve Sample Project
=================================

This is a sample project to reproduce the issues described in this StackOverflow
question:

https://stackoverflow.com/questions/46670355/wrap-requests-in-tomcat-custom-valve-to-allow-reading-the-request-body

It contains two projects: `capture`, which is the actual valve. And `demowebapp`
providing a simple JAX-RS webservice to demo the positive case.

Steps to reproduce
------------------

* Get the Tomcat valve up and running:
  * Build the capture project with ShadowJAR
  * Put the resulting jar into the `libs` folder inside your tomcat root directory
  * Configure the valve by adding the following in your `server.xml`: 
    `<Valve className="codekoenig.reqlogvalve.capture.CaptureValve" />`
  * Restart Tomcat.
* Get the demo web app up and running:
  * Build the project
  * Deploy the resulting WAR in your Tomcat.
  * _Important:_ I noticed that with the latest Tomcat builds on either major version
    (8.0.x, 8.5.x and 9.x) the valve is not working. I reverted to 8.5.20 and it works
    with that version again.
* Positive case:
  * POST a simple text/plain body to `http://localhost:8080/demowebapp-0.1.0-SNAPSHOT/services/demowebappapi/testpost`
  * Notice that everything works fine and you get your POST body contents returned in the response
  * Notice that in catalina log, the valve logs the body content as desired, a message like: 
    `codekoenig.reqlogvalve.capture.CaptureValve.invoke Parsed body: adsljkablasdf` appears at
    the end of the request.
  * Also notice that in the log `getInputStream()` is called on `LoggingRequest` and also `read()` is called on
    `LoggingInputStream()`. That is what is needed so the valve is able to cache the body.
* Negative case:
  * Open the Tomcat Manager app (http://localhost:8080/manager/html)
  * Use the "Expire sessions" button and textbox to also create a simple HTTP POST to the server
  * Notice that this time, in the catalina log the logged body is empty.
  * Notice that also in the catalina log, no calls to `getInputStream()` on `LoggingRequest` and 
    `read()` on `LoggingInputStream()` are ever done.
