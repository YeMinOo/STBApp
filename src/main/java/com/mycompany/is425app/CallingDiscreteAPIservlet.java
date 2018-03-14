/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.is425app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yemin
 */
public class CallingDiscreteAPIservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String POST_URL = "https://apistore.datasparkanalytics.com:8243/discretevisit/v2/query";

        String POST_DATA = "{\n"
                + " \"date\": \"2017-05-30\",\n"
                + " \"location\": {\n"
                + "   \"locationType\": \"locationHierarchyLevel\",\n"
                + "   \"levelType\": \"discrete_visit_subzone\",\n"
                + "   \"id\": \"OTSZ02\"\n"
                + " },\n"
                + " \"queryGranularity\": {\n"
                + "   \"type\": \"period\",\n"
                + "   \"period\": \"PT1H\"\n"
                + " },\n"
                + " \"aggregations\": [\n"
                + "   {\n"
                + "     \"metric\": \"unique_agents\",\n"
                + "     \"type\": \"hyperUnique\",\n"
                + "     \"describedAs\": \"footfall\"\n"
                + "   }\n"
                + " ]\n"
                + "}";

        URL line_api_url = new URL(POST_URL);
        String payload = POST_DATA;

        HttpURLConnection linec = (HttpURLConnection) line_api_url
                .openConnection();
        linec.setDoInput(true);
        linec.setDoOutput(true);
        linec.setRequestMethod("POST");
        linec.setRequestProperty("Content-Type", "application/json");
        linec.setRequestProperty("Authorization", "Bearer "
                + "d3e8d257-b3e5-333e-9da0-e2cd9c9d59b2");

        OutputStreamWriter writer = new OutputStreamWriter(
                linec.getOutputStream(), "UTF-8");
        writer.write(payload);
        writer.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(
                linec.getInputStream()));
        String inputLine;
//        String[] jsonArray = null;

        while ((inputLine = in.readLine()) != null) {
//            jsonArray = inputLine.split(",|\\:");
            System.out.println(inputLine);
        }

//        for(String s : jsonArray) {
//            System.out.println(s);
//        }
        in.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
