package app.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/23 0023.
 */
@Entity(name = "discharge_diagnosis")
public class DischargeDiagnosisDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "medicine_liver_patient_id")
    private MlPatientDO mlPatientDO;
    private String diagnosis;
    private Date diagnosisDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MlPatientDO getMlPatientDO() {
        return mlPatientDO;
    }

    public void setMlPatientDO(MlPatientDO mlPatientDO) {
        this.mlPatientDO = mlPatientDO;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }
}
