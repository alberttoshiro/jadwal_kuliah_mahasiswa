// package com.albert;
//
// import javax.enterprise.context.ApplicationScoped;
// import javax.inject.Inject;
// import javax.ws.rs.GET;
// import javax.ws.rs.Path;
// import javax.ws.rs.Produces;
// import javax.ws.rs.core.MediaType;
// import org.jboss.logging.Logger;
//
// @ApplicationScoped
// @Path("/app")
// public class App {
//
// @Inject
// Logger log;
//
// @GET
// @Produces(MediaType.TEXT_PLAIN)
// @Path("{name}")
// public String testapp(String name) {
// // FolderListener folderListener = new FolderListener();
// // Thread thread = new Thread(folderListener);
// // thread.start();
// log.info("name parameter: " + name);
// return "Hello " + name + " Now will read and process file data";
// }
//
// }
