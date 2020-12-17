package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        Report r;

        if (request.getParameter("id") == null) {           //いいねを押したあとに戻ってくる場合
            int id = (int)request.getSession().getAttribute("id"); //セッションスコープにしないと推移しない
            r = em.find(Report.class, id);
            request.setAttribute("report", r);
            request.getSession().removeAttribute("id");
        } else {                                            //一覧ページから飛んでparameterとしてid番号を持っている場合
            int id = Integer.parseInt(request.getParameter("id"));
            r = em.find(Report.class, id);
            request.setAttribute("report", r);
        }

        //いいねのカウント
        long like_count = (long)em.createNamedQuery("getLike_Count",Long.class)
                 .setParameter("report_id", r.getId())
                .getSingleResult();
        request.setAttribute("like_count", like_count);

        //いいねしているかどうか
        long like_or = (long)em.createNamedQuery("like_or",Long.class)
                .setParameter("employee", (Employee)request.getSession().getAttribute("login_employee")) //セッションスコープからログインユーザーの識別
                .setParameter("report_id", r.getId())
                .getSingleResult();
        request.setAttribute("like_or", like_or);


        request.setAttribute("_token", request.getSession().getId());

        em.close();

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);

    }

}
