package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "like_reports")
@NamedQueries({
    @NamedQuery(
            name = "like_or",
            query = "SELECT COUNT(l) FROM Like_reports AS l WHERE l.employee = :employee AND l.report_id = :report_id ORDER BY l.id DESC"
            ),
    @NamedQuery(
            name = "getMyLiksAllReports",
            query = "SELECT l FROM Like_reports AS l WHERE l.employee = :employee ORDER BY l.id DESC"
            ),
    @NamedQuery(
            name = "getMyLikesReportId",
            query = "SELECT l FROM Like_reports AS l WHERE l.report_id = :report_id ORDER BY l.id DESC"
            ),
    @NamedQuery(
            name = "getLike_Count",
            query = "SELECT COUNT(l) FROM Like_reports AS l WHERE l.report_id = :report_id"
            )
})
@Entity
public class Like_reports {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "report_id", nullable = false)
    private Integer report_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

}
