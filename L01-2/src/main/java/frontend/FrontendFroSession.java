package frontend;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class FrontendFroSession extends HttpServlet {

    private String login = "";
    private AtomicLong userIdGenerator = new AtomicLong();

    public static String getTime() {
        Date date = new Date();
        date.getTime();
        DateFormat formatter = new SimpleDateFormat("HH.mm.ss");
        return formatter.format(date);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<>();

        if (request.getPathInfo().equals("/authform")) {
            pageVariables.put("lastLogin", login == null ? "" : login);
            response.getWriter().println(PageGenerator.getPage("authform.tml", pageVariables));
            return;
        }

        if (request.getPathInfo().equals("/timer")) {
            pageVariables.put("refreshPeriod", "1000");
            pageVariables.put("serverTime", getTime());
            response.getWriter().println(PageGenerator.getPage("timer.tml", pageVariables));
            return;
        }

        if (request.getPathInfo().equals("/userid")) {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                userId = userIdGenerator.getAndIncrement();
                session.setAttribute("userId", userId);
            }
            pageVariables.put("refreshPeriod", "1000");
            pageVariables.put("serverTime", getTime());
            pageVariables.put("userId", userId);
            response.getWriter().println(PageGenerator.getPage("userid.tml", pageVariables));
            return;
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        login = request.getParameter("login");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("lastLogin", login == null ? "" : login);
        response.getWriter().println(PageGenerator.getPage("authform.tml", pageVariables));
    }
}
