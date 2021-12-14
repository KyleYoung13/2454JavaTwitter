/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package hopperPackage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andre
 */
@WebServlet(name = "getImage", urlPatterns = {"/getImage"})
public class getImage extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        //https://mail.codejava.net/coding/how-to-display-images-from-database-in-jsp-page-with-java-servlet#:~:text=%20How%20to%20display%20images%20from%20database%20in,image%20source%20is%20the%20base64%20string.%20More%20
        String id = request.getParameter("id");

        try {
            Connection connection = DBConnection.getConnection();
            String preparedSQL = "select image, filename from hop where id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(preparedSQL);

            // TODO FIX USERNAME FOR ID
            preparedStatement.setString(1, id);

            ResultSet result = preparedStatement.executeQuery();
            Blob blob = null;
            String filename = "";
            while (result.next()) {
                blob = result.getBlob("image");
                filename = result.getString("filename");
            }

            byte[] imageBytes = blob.getBytes(1, (int) blob.length());

            preparedStatement.close();
            connection.close();

            String contentType = this.getServletContext().getMimeType(filename);

            response.setHeader("Content-Type", contentType);

            OutputStream os = response.getOutputStream();
            os.write(imageBytes);
            os.flush();
            os.close();

        } catch (SQLException exception) {
            System.out.println(exception);
        } catch (ClassNotFoundException exception) {
            System.out.println(exception);
        }

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
