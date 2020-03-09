// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;
import com.google.sps.data.Comment;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  List<String> enteries = new ArrayList<>();
  static int count = 0;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Query query = new Query("Comment");
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery results = datastore.prepare(query);
        List<Comment> comments = new ArrayList<>();

        for (Entity entity : results.asIterable()) {
            long id = entity.getKey().getId();
            String name = (String) entity.getProperty("name");
            String title = (String) entity.getProperty("title");
            String comment_string = (String) entity.getProperty("comment");
            Comment comment = new Comment(id,name,title,comment_string);
            comments.add(comment);
        }

        Gson gson = new Gson();
        response.setContentType("application/json;");
        response.getWriter().println(gson.toJson(comments));
    }
   
  
  private String convertToJson(String name, String title, String comment) {
    String json = "{";
    json += "\"name\": ";
    json += "\"" + name + "\"";
    json += ", ";
    json += "\"title\": ";
    json += "\"" + title + "\"";
    json += ", ";
    json += "\"comment\":"; 
    json += "\"" + comment + "\"";
    json += "}";
    return json;
  }


  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }


  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String name = request.getParameter("name");
    String comment = request.getParameter("comment-box");
    String title = request.getParameter("comment-title");

    //create datastore and insert comments
    Entity commentEntry = new Entity("Comment");
    commentEntry.setProperty("name", name);
    commentEntry.setProperty("comment", comment);
    commentEntry.setProperty("title", title);


    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();//instantiate dataclass
    datastore.put(commentEntry);
    response.sendRedirect("/index.html");
  }
}
