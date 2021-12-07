package hopperPackage;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdk.internal.util.StaticProperty;

@WebServlet(urlPatterns = {"/hopperServlet"})
public class hopperServlet extends HttpServlet {

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
        if (request.getParameter("action") == null || request.getParameter("action").equalsIgnoreCase("userlist")) {
            ArrayList<User> users = UserModel.getUsers();
            request.setAttribute("users", users);
            String url = "/UserList.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("loginUser")) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            HttpSession session = request.getSession();
            session.setAttribute(userName, request);
            Cookie cookie = new Cookie("usernameCookie", userName);
            response.addCookie(cookie);

            response.sendRedirect("hopperServlet?action=hopperHomePage");
        } else if (request.getParameter("action").equalsIgnoreCase("addUser")) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            if (userName == null || userName.isBlank() || password == null || password.isBlank()) {
                throw new ServletException("Blank input");
            } else {

                try {
                    //https://www.geeksforgeeks.org/sha-256-hash-in-java/
                    String hashedPassword = toHexString(getSHA(password));
                    User user = new User(0, userName, hashedPassword);
                    if (UserModel.uniqueUsername(userName)) {
                        throw new ServletException("Blank input");
                    } else {
                        UserModel.addUser(user);
                    }

                } catch (NoSuchAlgorithmException ex) {
                    throw new ServletException("Blank input");
                }
            }
            response.sendRedirect("hopperServlet?action=hopperHomePage");
        } else if (request.getParameter("action").equalsIgnoreCase("updateUser")) {

            String id = request.getParameter("id");
            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            if (id == null || id.isBlank() || userName == null || userName.isBlank() || password == null || password.isBlank()) {
                throw new ServletException("Blank input");
            } else {

                try {
                    //https://www.geeksforgeeks.org/sha-256-hash-in-java/
                    String hashedPassword = toHexString(getSHA(password));

                    User user = new User(Integer.parseInt(id), userName, hashedPassword);

                    UserModel.updateUser(user);

                } catch (NoSuchAlgorithmException ex) {
                    throw new ServletException("Blank input");
                }
            }
            response.sendRedirect("hopperServlet");
        } else if (request.getParameter("action").equalsIgnoreCase("searchUser")) {
            int id = Integer.parseInt(request.getParameter("user_id"));
            if (id == 0) {
                throw new ServletException("Blank input");
            } else {
                ArrayList<hop> hopList = hopModel.getUsersHops(id);
                request.setAttribute("hopList", hopList);
                String url = "/personsHops.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
        } //START OF HOPPER HOME PAGE
        else if (request.getParameter("action").equalsIgnoreCase("hopperHomePage")) {
            /*ArrayList<hop> hopsList = hopModel.getHops();
            request.setAttribute("hopsList", hopsList);
             */
            int user_id = 0;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String username = cookie.getValue();
                    if (UserModel.uniqueUsername(username)) {
                        user_id = UserModel.getIDfromUsername(username);
                    }
                }
            }
            ArrayList<hop> hopsList = followingModel.getFollowHops(user_id);
            request.setAttribute("hopsList", hopsList);
            String url = "/hopperHomePage.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("addHop")) {
            String content = request.getParameter("content");
            int user_id = 0;
            //https://stackoverflow.com/questions/22804409/get-cookie-value-in-java/46121394
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String username = cookie.getValue();
                    if (UserModel.uniqueUsername(username)) {
                        user_id = UserModel.getIDfromUsername(username);
                    }
                }
                hop hops = new hop(0, user_id, content, "", 0);
                hopModel.addHop(hops);
            }
            response.sendRedirect("hopperServlet?action=hopperHomePage");
        } else if (request.getParameter("action").equalsIgnoreCase("like")) {

            String content = request.getParameter("content");

            hopModel.likeHop(content);

            response.sendRedirect("hopperServlet?action=hopperHomePage");
        } //START OF HOPPER USER SEARCH PAGE
        else if (request.getParameter("action").equalsIgnoreCase("personsHops")) {
            String url = "/personsHops.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("followUser")) {
            int searched_id = Integer.parseInt(request.getParameter("follow_user_id"));
            int user_id = 0;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String username = cookie.getValue();
                    if (UserModel.uniqueUsername(username)) {
                        user_id = UserModel.getIDfromUsername(username);
                    }
                }
            }
            following follow = new following(user_id, searched_id);
            followingModel.addFollow(follow);

            response.sendRedirect("hopperServlet?action=personsHops");
        }
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
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
