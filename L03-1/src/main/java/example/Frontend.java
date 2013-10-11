package example;

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

public class Frontend extends HttpServlet implements Abonent, Runnable {
    private MessageSystem ms;
    private Address address;
    private Map<String, UserSession> sessionIdToUserSession = new HashMap<>();

    public Frontend(MessageSystem ms) {
        this.ms = ms;
        this.address = new Address();
        ms.addService(this);
    }

    public static String getTime() {
        Date date = new Date();
        date.getTime();
        DateFormat formatter = new SimpleDateFormat("HH.mm.ss");
        return formatter.format(date);
    }

    public Address getAddress() {
        return address;
    }

    public void setId(String sessionId, Long userId) {
        UserSession userSession = sessionIdToUserSession.get(sessionId);
        if (userSession == null) {
            System.out.append("Can't find user session for: ").append(sessionId);
            return;
        }
        userSession.setUserId(userId);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo().equals("/userid")) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

            HttpSession session = request.getSession();
            UserSession userSession = sessionIdToUserSession.get(session.getId());
            if (userSession == null) {
                responseUserPage(response, "Auth error");
                return;
            }
            if (userSession.getUserId() == null) {
                responseUserPage(response, "wait for authorization");
                return;
            }
            responseUserPage(response, "name = " + userSession.getName() + ", id = " + userSession.getUserId());
        }
    }

    private void responseUserPage(HttpServletResponse response, String userState) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("refreshPeriod", "1000");
        pageVariables.put("serverTime", getTime());
        pageVariables.put("userState", userState);
        response.getWriter().println(PageGenerator.getPage("userid.tml", pageVariables));
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo().equals("/userid")) {
            String login = request.getParameter("login");
            String sessionId = request.getSession().getId();
            UserSession userSession = new UserSession(sessionId, login, ms.getAddressService());
            sessionIdToUserSession.put(sessionId, userSession);

            Address frontendAddress = getAddress();
            Address accountServiceAddress =userSession.getAccountService();

            ms.sendMessage(new MsgGetUserId(frontendAddress, accountServiceAddress, login, sessionId));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

            responseUserPage(response, "authorization started");
        }
    }

    public void run() {
        while (true) {
            ms.execForAbonent(this);
            TimeHelper.sleep(10);
        }
    }
}
