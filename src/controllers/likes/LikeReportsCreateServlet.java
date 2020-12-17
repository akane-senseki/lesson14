package controllers.likes;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Like_reports;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class LikeReportsCreateServlet
 */
@WebServlet("/like_add")
public class LikeReportsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeReportsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Report r = em.find(Report.class , (Integer.parseInt(request.getParameter("id"))));
            int r_id = r.getId();

            //いいねしているかどうか
            long like_or = (long)em.createNamedQuery("like_or",Long.class)
                    .setParameter("employee", (Employee)request.getSession().getAttribute("login_employee")) //セッションスコープからログインユーザーの識別
                    .setParameter("report_id", r.getId())
                    .getSingleResult();

            if(like_or == 0){

            Like_reports l = new Like_reports();
            l.setEmployee((Employee)request.getSession().getAttribute("login_employee")); //LoginServletにてセッションスコープに登録されてる
            l.setReport_id(r.getId());

            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();

            request.getSession().setAttribute("id", l.getReport_id());  //セッションスコープにしないと推移しない
            em.close();

            request.setAttribute("_token", request.getSession().getId());
            response.sendRedirect(request.getContextPath()+"/reports/show");
            }else{

                //いいねのIdを取得
                List<Like_reports> l_id = em.createNamedQuery("getMyLikesReportId",Like_reports.class)
                        .setParameter("report_id", r.getId())
                        .getResultList();

                Like_reports l ;

                for(int i = 0 ; i < l_id.size() ; i++){
                    if(r.getId() == l_id.get(i).getReport_id()){
                        l = em.find(Like_reports.class, l_id.get(i).getId());

                        em.getTransaction().begin();
                        em.remove(l);
                        em.getTransaction().commit();
                    }
                }


                em.close();

                request.getSession().setAttribute("id", r_id);  //セッションスコープにしないと推移しない

                request.setAttribute("_token", request.getSession().getId());
                response.sendRedirect(request.getContextPath()+"/reports/show");

                }
        }
    }

}
