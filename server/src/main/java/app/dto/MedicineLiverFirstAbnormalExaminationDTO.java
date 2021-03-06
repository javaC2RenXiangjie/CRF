package app.dto;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/24 0024.
 */
public class MedicineLiverFirstAbnormalExaminationDTO {

    private int id;
    private int patientId;
    private Date tab1CheckDate;//肝脏生化检查时间
    private Date tab2CheckDate;//肾功能检查时间
    private Date tab3CheckDate;//血糖检查时间
    private Date tab4CheckDate;//凝血功能检查时间
    private Date tab5CheckDate;//肿瘤标志物
    private String alt;
    private Date altDate;
    private String ast;
    private Date astDate;
    private String ggt;
    private String ggtDate;
    private String alp;
    private String alpDate;
    private String bileAcid;//胆汁酸
    private Date bileAcidDate;//胆汁酸检查日期
    private String tbil;
    private String tbilDate;
    private String dbil;
    private Date dbilDate;
    private String tp;
    private Date tpDate;
    private String alb;
    private Date albDate;
    private String scr;
    private Date scrDate;
    private String bun;
    private Date bunDate;
    private String plasmaGlucose;//空腹血糖
    private Date plasmaGlucoseDate;//空腹血糖检查日期
    private String pt;
    private Date ptDate;
    private String inr;
    private Date inrDate;
    private String afp;
    private Date afpDate;
    private String remark;//备注
    private boolean first;
    private boolean complete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Date getAltDate() {
        return altDate;
    }

    public void setAltDate(Date altDate) {
        this.altDate = altDate;
    }

    public String getAst() {
        return ast;
    }

    public void setAst(String ast) {
        this.ast = ast;
    }

    public Date getAstDate() {
        return astDate;
    }

    public void setAstDate(Date astDate) {
        this.astDate = astDate;
    }

    public String getGgt() {
        return ggt;
    }

    public void setGgt(String ggt) {
        this.ggt = ggt;
    }

    public String getGgtDate() {
        return ggtDate;
    }

    public void setGgtDate(String ggtDate) {
        this.ggtDate = ggtDate;
    }

    public String getAlp() {
        return alp;
    }

    public void setAlp(String alp) {
        this.alp = alp;
    }

    public String getAlpDate() {
        return alpDate;
    }

    public void setAlpDate(String alpDate) {
        this.alpDate = alpDate;
    }

    public String getBileAcid() {
        return bileAcid;
    }

    public void setBileAcid(String bileAcid) {
        this.bileAcid = bileAcid;
    }

    public Date getBileAcidDate() {
        return bileAcidDate;
    }

    public void setBileAcidDate(Date bileAcidDate) {
        this.bileAcidDate = bileAcidDate;
    }

    public String getTbil() {
        return tbil;
    }

    public void setTbil(String tbil) {
        this.tbil = tbil;
    }

    public String getTbilDate() {
        return tbilDate;
    }

    public void setTbilDate(String tbilDate) {
        this.tbilDate = tbilDate;
    }

    public String getDbil() {
        return dbil;
    }

    public void setDbil(String dbil) {
        this.dbil = dbil;
    }

    public Date getDbilDate() {
        return dbilDate;
    }

    public void setDbilDate(Date dbilDate) {
        this.dbilDate = dbilDate;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public Date getTpDate() {
        return tpDate;
    }

    public void setTpDate(Date tpDate) {
        this.tpDate = tpDate;
    }

    public String getAlb() {
        return alb;
    }

    public void setAlb(String alb) {
        this.alb = alb;
    }

    public Date getAlbDate() {
        return albDate;
    }

    public void setAlbDate(Date albDate) {
        this.albDate = albDate;
    }

    public String getScr() {
        return scr;
    }

    public void setScr(String scr) {
        this.scr = scr;
    }

    public Date getScrDate() {
        return scrDate;
    }

    public void setScrDate(Date scrDate) {
        this.scrDate = scrDate;
    }

    public String getBun() {
        return bun;
    }

    public void setBun(String bun) {
        this.bun = bun;
    }

    public Date getBunDate() {
        return bunDate;
    }

    public void setBunDate(Date bunDate) {
        this.bunDate = bunDate;
    }

    public String getPlasmaGlucose() {
        return plasmaGlucose;
    }

    public void setPlasmaGlucose(String plasmaGlucose) {
        this.plasmaGlucose = plasmaGlucose;
    }

    public Date getPlasmaGlucoseDate() {
        return plasmaGlucoseDate;
    }

    public void setPlasmaGlucoseDate(Date plasmaGlucoseDate) {
        this.plasmaGlucoseDate = plasmaGlucoseDate;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public Date getPtDate() {
        return ptDate;
    }

    public void setPtDate(Date ptDate) {
        this.ptDate = ptDate;
    }

    public String getInr() {
        return inr;
    }

    public void setInr(String inr) {
        this.inr = inr;
    }

    public Date getInrDate() {
        return inrDate;
    }

    public void setInrDate(Date inrDate) {
        this.inrDate = inrDate;
    }

    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }

    public Date getAfpDate() {
        return afpDate;
    }

    public void setAfpDate(Date afpDate) {
        this.afpDate = afpDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Date getTab1CheckDate() {
        return tab1CheckDate;
    }

    public void setTab1CheckDate(Date tab1CheckDate) {
        this.tab1CheckDate = tab1CheckDate;
    }

    public Date getTab2CheckDate() {
        return tab2CheckDate;
    }

    public void setTab2CheckDate(Date tab2CheckDate) {
        this.tab2CheckDate = tab2CheckDate;
    }

    public Date getTab3CheckDate() {
        return tab3CheckDate;
    }

    public void setTab3CheckDate(Date tab3CheckDate) {
        this.tab3CheckDate = tab3CheckDate;
    }

    public Date getTab4CheckDate() {
        return tab4CheckDate;
    }

    public void setTab4CheckDate(Date tab4CheckDate) {
        this.tab4CheckDate = tab4CheckDate;
    }

    public Date getTab5CheckDate() {
        return tab5CheckDate;
    }

    public void setTab5CheckDate(Date tab5CheckDate) {
        this.tab5CheckDate = tab5CheckDate;
    }
}

