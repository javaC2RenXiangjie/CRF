//package app.serviceImpl;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import app.dto.*;
//import app.entities.AdmissionDiagnosisDO;
//import app.entities.DischargeDiagnosisDO;
//import app.entities.MlPatientDO;
//import app.repo.*;
//import app.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import app.Utils.UserMsgTool;
//
///**
// * @author Administrator PatientServiceImpl
// */
//@Service
//public class MlPatientServiceImpl implements MlPatientService {
//
//    @Autowired
//    private MlPatientRepo mlpatientRepo;
//
//    @Autowired
//    private UserProjectRoleRepo userProjectRoleRepo;
//
//    @Transactional
//    public int saveMlPatientGeneralInformation(MlPatientDTO mlPatientDTO) {
//        return mlpatientRepo.save(convertToMlPatientDO(mlPatientDTO)).getId();
//    }
//
//    public MlPatientDTO getMlPatientGeneralInformation(int id) {
//
//        if (mlpatientRepo.getMlPatientInformationById(id) == null) {
//            return null;
//        } else {
//            return convertToMlPatientDTO(mlpatientRepo.getMlPatientInformationById(id));
//        }
//    }
//
//    public Boolean getCompleteById(int id) {
//        return mlpatientRepo.getCompleteById(id)?mlpatientRepo.getCompleteById(id):null;
//    }
//
//    @Override
//    public PageDTO<MlPatientDTO> getAllPatient(int projectId, Pageable pageable) {
//        List<MlPatientDTO> mlPatientDTOs = new ArrayList<>();
//        Page<MlPatientDO> mlPatientDOs = mlpatientRepo.getAll(projectId,new UserMsgTool().getCurrentUser().getHospital().getId(),pageable);
//        if(mlPatientDOs.hasContent() && mlPatientDOs != null) {
//            for (MlPatientDO mlPatientDO : mlPatientDOs) {
//                mlPatientDTOs.add(convertToMlPatientDTO(mlPatientDO));
//            }
//        }
//        PageDTO<MlPatientDTO> pageDTO = new PageDTO<>();
//        pageDTO.setContent(mlPatientDTOs);
//        pageDTO.setTotalNumber(mlPatientDOs.getTotalPages());
//        return pageDTO;
//    }
//
//    @Override
//    public PageDTO<MlPatientDTO> getMlPatientByQueryStr(int projectId, String queryStr, Pageable pageable) {
//        List<MlPatientDTO> mlPatientDTOs = new ArrayList<>();
//        Page<MlPatientDO> mlPatientDOs = mlpatientRepo.getMlPatientByQueryStr(projectId, queryStr, pageable);
//        for (MlPatientDO mlPatientDO : mlPatientDOs) {
//            mlPatientDTOs.add(convertToMlPatientDTO(mlPatientDO));
//        }
//        PageDTO<MlPatientDTO> pageDTO = new PageDTO<>();
//        pageDTO.setContent(mlPatientDTOs);
//        pageDTO.setTotalNumber(mlPatientDOs.getTotalPages());
//        return pageDTO;
//    }
//
//    @Transactional
//    public void editMlPatient(MlPatientDTO mlPatientDTO) {
//        MlPatientDO mlPatientDO = mlpatientRepo.findOne(mlPatientDTO.getId());
//        editToMlPatientDO(mlPatientDO, mlPatientDTO);
//        mlpatientRepo.save(mlPatientDO);
//    }
//
//    private MlPatientDO convertToMlPatientDO(MlPatientDTO mlPatientDTO) {
//        MlPatientDO mlPatientDO = new MlPatientDO();
//        mlPatientDO.setIdentifier(new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()));
//        mlPatientDO.setAbbreviation(mlPatientDTO.getAbbreviation());
//        mlPatientDO.setAdmissionDate(mlPatientDTO.getAdmissionDate());
//        List<AdmissionDiagnosisDO> admissionDiagnosisDOS = new ArrayList<>();
//        List<DischargeDiagnosisDO> dischargeDiagnosisDOS = new ArrayList<>();
//        if (mlPatientDTO.getAdmissionDiagnosisDTOS()!=null) {
//            for (AdmissionDiagnosisDTO admissionDiagnosisDTO : mlPatientDTO.getAdmissionDiagnosisDTOS()) {
//                AdmissionDiagnosisDO admissionDiagnosisDO = new AdmissionDiagnosisDO();
//                admissionDiagnosisDO.setDiagnosis(admissionDiagnosisDTO.getDiagnosis());
//                admissionDiagnosisDO.setDiagnosisDate(admissionDiagnosisDTO.getDiagnosisDate());
//                admissionDiagnosisDO.setMlPatientDO(mlPatientDO);
//                admissionDiagnosisDOS.add(admissionDiagnosisDO);
//            }
//        }
//        if(mlPatientDTO.getDischargeDiagnosisDTOS()!=null) {
//            for (DischargeDiagnosisDTO dischargeDiagnosisDTO : mlPatientDTO.getDischargeDiagnosisDTOS()) {
//                DischargeDiagnosisDO dischargeDiagnosisDO = new DischargeDiagnosisDO();
//                dischargeDiagnosisDO.setDiagnosisDate(dischargeDiagnosisDTO.getDiagnosisDate());
//                dischargeDiagnosisDO.setDiagnosis(dischargeDiagnosisDTO.getDiagnosis());
//                dischargeDiagnosisDO.setMlPatientDO(mlPatientDO);
//                dischargeDiagnosisDOS.add(dischargeDiagnosisDO);
//            }
//        }
//        mlPatientDO.setAdmissionDiagnosisDOS(admissionDiagnosisDOS);
//        mlPatientDO.setDischargeDiagnosisDOS(dischargeDiagnosisDOS);
//        mlPatientDO.setCareer(mlPatientDTO.getCareer());
//        mlPatientDO.setDepartment(mlPatientDTO.getDepartment());
//        mlPatientDO.setDischargeDate(mlPatientDTO.getDischargeDate());
//        mlPatientDO.setHospitalizedNumber(mlPatientDTO.getHospitalizedNumber());
//        mlPatientDO.setHospitalizedDepartment(mlPatientDTO.getHospitalizedDepartment());
//        mlPatientDO.setHospitalizedAge(mlPatientDTO.getHospitalizedAge());
//        mlPatientDO.setNation(mlPatientDTO.getNation());
//        mlPatientDO.setDrink(mlPatientDTO.isDrink());
//        mlPatientDO.setFamilyHistory(mlPatientDTO.getFamilyHistory());
//        mlPatientDO.setGender(mlPatientDTO.getGender());
//        mlPatientDO.setHeight(mlPatientDTO.getHeight());
//        mlPatientDO.setName(mlPatientDTO.getName());
//        mlPatientDO.setSmoke(mlPatientDTO.isSmoke());
//        mlPatientDO.setWeight(mlPatientDTO.getWeight());
//        mlPatientDO.setComplete(mlPatientDTO.isComplete());
//        mlPatientDO.setBirthday(mlPatientDTO.getBirthday());
//        mlPatientDO.setBmi(mlPatientDTO.getBmi());
//        mlPatientDO.setDegreeOfEducation(mlPatientDTO.getDegreeOfEducation());
//        mlPatientDO.setFirstTimeLiverInjury(mlPatientDTO.getFirstTimeLiverInjury());
//        mlPatientDO.setInvestigateHospital(mlPatientDTO.getInvestigateHospital());
//        mlPatientDO.setTelephone(mlPatientDTO.getTelephone());
//        mlPatientDO.setDurationOfVisit(mlPatientDTO.getDurationOfVisit());
//        mlPatientDO.setFirstVisitAge(mlPatientDTO.getFirstVisitAge());
//        mlPatientDO.setFirstVisitTime(mlPatientDTO.getFirstVisitTime());
//        mlPatientDO.setSmokeDrinkFamHis(mlPatientDTO.getSmokeDrinkFamHis());
//        mlPatientDO.setConcurrentAutoDate(mlPatientDTO.getConcurrentAutoDate());
//        mlPatientDO.setConcurrentAutoDisease(mlPatientDTO.isConcurrentAutoDisease());
//        mlPatientDO.setConAutoDisFirstOrNot(mlPatientDTO.isConAutoDisFirstOrNot());
//        mlPatientDO.setHospitalId(new UserMsgTool().getCurrentUser().getHospital().getId());
//        mlPatientDO.setProjectId(mlPatientDTO.getProjectId());
//        return mlPatientDO;
//    }
//
//    public MlPatientDTO convertToMlPatientDTO(MlPatientDO mlPatientDO) {
//        MlPatientDTO mlPatientDTO = new MlPatientDTO();
//        mlPatientDTO.setId(mlPatientDO.getId());
//        mlPatientDTO.setIdentifier(mlPatientDO.getIdentifier());
//        mlPatientDTO.setNation(mlPatientDO.getNation());
//        mlPatientDTO.setDrink(mlPatientDO.isDrink());
//        mlPatientDTO.setFamilyHistory(mlPatientDO.getFamilyHistory());
//        mlPatientDTO.setGender(mlPatientDO.getGender());
//        mlPatientDTO.setHeight(mlPatientDO.getHeight());
//        mlPatientDTO.setName(mlPatientDO.getName());
//        mlPatientDTO.setSmoke(mlPatientDO.isSmoke());
//        mlPatientDTO.setWeight(mlPatientDO.getWeight());
//        mlPatientDTO.setBirthday(mlPatientDO.getBirthday());
//        mlPatientDTO.setBmi(mlPatientDO.getBmi());
//        mlPatientDTO.setDegreeOfEducation(mlPatientDO.getDegreeOfEducation());
//        mlPatientDTO.setFirstTimeLiverInjury(mlPatientDO.getFirstTimeLiverInjury());
//        mlPatientDTO.setInvestigateHospital(mlPatientDO.getInvestigateHospital());
//        mlPatientDTO.setTelephone(mlPatientDO.getTelephone());
//        mlPatientDTO.setDurationOfVisit(mlPatientDO.getDurationOfVisit());
//        mlPatientDTO.setFirstVisitAge(mlPatientDO.getFirstVisitAge());
//        mlPatientDTO.setFirstVisitTime(mlPatientDO.getFirstVisitTime());
//        mlPatientDTO.setSmokeDrinkFamHis(mlPatientDO.getSmokeDrinkFamHis());
//        mlPatientDTO.setConcurrentAutoDate(mlPatientDO.getConcurrentAutoDate());
//        mlPatientDTO.setConcurrentAutoDisease(mlPatientDO.getConcurrentAutoDisease());
//        mlPatientDTO.setConAutoDisFirstOrNot(mlPatientDO.isConAutoDisFirstOrNot());
//        mlPatientDTO.setAbbreviation(mlPatientDO.getAbbreviation());
//        mlPatientDTO.setDepartment(mlPatientDO.getDepartment());
//        mlPatientDTO.setAdmissionDate(mlPatientDO.getAdmissionDate());
//        mlPatientDTO.setDischargeDate(mlPatientDO.getDischargeDate());
//        mlPatientDTO.setHospitalizedNumber(mlPatientDO.getHospitalizedNumber());
//        mlPatientDTO.setHospitalizedDepartment(mlPatientDO.getHospitalizedDepartment());
//        mlPatientDTO.setHospitalizedAge(mlPatientDO.getHospitalizedAge());
//        mlPatientDTO.setCareer(mlPatientDO.getCareer());
//        List<AdmissionDiagnosisDTO> admissionDiagnosisDTOS = new ArrayList<>();
//        List<DischargeDiagnosisDTO> dischargeDiagnosisDTOS = new ArrayList<>();
//        for (AdmissionDiagnosisDO admissionDiagnosisDO : mlPatientDO.getAdmissionDiagnosisDOS()) {
//            AdmissionDiagnosisDTO admissionDiagnosisDTO = new AdmissionDiagnosisDTO();
//            admissionDiagnosisDTO.setDiagnosis(admissionDiagnosisDO.getDiagnosis());
//            admissionDiagnosisDTO.setDiagnosisDate(admissionDiagnosisDO.getDiagnosisDate());
//            admissionDiagnosisDTO.setId(admissionDiagnosisDO.getId());
//            admissionDiagnosisDTO.setMlPatientId(admissionDiagnosisDO.getMlPatientDO().getId());
//            admissionDiagnosisDTOS.add(admissionDiagnosisDTO);
//        }
//        for (DischargeDiagnosisDO dischargeDiagnosisDO : mlPatientDO.getDischargeDiagnosisDOS()) {
//            DischargeDiagnosisDTO dischargeDiagnosisDTO = new DischargeDiagnosisDTO();
//            dischargeDiagnosisDTO.setMlPatientId(dischargeDiagnosisDO.getMlPatientDO().getId());
//            dischargeDiagnosisDTO.setId(dischargeDiagnosisDO.getId());
//            dischargeDiagnosisDTO.setDiagnosisDate(dischargeDiagnosisDO.getDiagnosisDate());
//            dischargeDiagnosisDTO.setDiagnosis(dischargeDiagnosisDO.getDiagnosis());
//            dischargeDiagnosisDTOS.add(dischargeDiagnosisDTO);
//        }
//        mlPatientDTO.setAdmissionDiagnosisDTOS(admissionDiagnosisDTOS);
//        mlPatientDTO.setDischargeDiagnosisDTOS(dischargeDiagnosisDTOS);
//        mlPatientDTO.setHospitalId(mlPatientDO.getHospitalId());
//        mlPatientDTO.setProjectId(mlPatientDO.getProjectId());
//        return mlPatientDTO;
//    }
//
//    private MlPatientDO editToMlPatientDO(MlPatientDO mlPatientDO, MlPatientDTO mlPatientDTO) {
//        mlPatientDO.setId(mlPatientDTO.getId());
//        mlPatientDO.setIdentifier(mlPatientDTO.getIdentifier());
//        mlPatientDO.setNation(mlPatientDTO.getNation());
//        mlPatientDO.setDrink(mlPatientDTO.isDrink());
//        mlPatientDO.setFamilyHistory(mlPatientDTO.getFamilyHistory());
//        mlPatientDO.setGender(mlPatientDTO.getGender());
//        mlPatientDO.setHeight(mlPatientDTO.getHeight());
//        mlPatientDO.setName(mlPatientDTO.getName());
//        mlPatientDO.setSmoke(mlPatientDTO.isSmoke());
//        mlPatientDO.setWeight(mlPatientDTO.getWeight());
//        mlPatientDO.setComplete(mlPatientDTO.isComplete());
//        mlPatientDO.setBirthday(mlPatientDTO.getBirthday());
//        mlPatientDO.setBmi(mlPatientDTO.getBmi());
//        mlPatientDO.setDegreeOfEducation(mlPatientDTO.getDegreeOfEducation());
//        mlPatientDO.setFirstTimeLiverInjury(mlPatientDTO.getFirstTimeLiverInjury());
//        mlPatientDO.setInvestigateHospital(mlPatientDTO.getInvestigateHospital());
//        mlPatientDO.setTelephone(mlPatientDTO.getTelephone());
//        mlPatientDO.setDurationOfVisit(mlPatientDTO.getDurationOfVisit());
//        mlPatientDO.setFirstVisitAge(mlPatientDTO.getFirstVisitAge());
//        mlPatientDO.setFirstVisitTime(mlPatientDTO.getFirstVisitTime());
//        mlPatientDO.setSmokeDrinkFamHis(mlPatientDTO.getSmokeDrinkFamHis());
//        mlPatientDO.setConcurrentAutoDate(mlPatientDTO.getConcurrentAutoDate());
//        mlPatientDO.setConcurrentAutoDisease(mlPatientDTO.isConcurrentAutoDisease());
//        mlPatientDO.setConAutoDisFirstOrNot(mlPatientDTO.isConAutoDisFirstOrNot());
//        mlPatientDO.setAbbreviation(mlPatientDTO.getAbbreviation());
//        mlPatientDO.setDepartment(mlPatientDTO.getDepartment());
//        mlPatientDO.setAdmissionDate(mlPatientDTO.getAdmissionDate());
//        mlPatientDO.setDischargeDate(mlPatientDTO.getDischargeDate());
//        mlPatientDO.setHospitalizedNumber(mlPatientDTO.getHospitalizedNumber());
//        mlPatientDO.setHospitalizedDepartment(mlPatientDTO.getHospitalizedDepartment());
//        mlPatientDO.setHospitalizedAge(mlPatientDTO.getHospitalizedAge());
//        mlPatientDO.setCareer(mlPatientDTO.getCareer());
//        List<AdmissionDiagnosisDO> admissionDiagnosisDOS = new ArrayList<>();
//        List<DischargeDiagnosisDO> dischargeDiagnosisDOS = new ArrayList<>();
//        for (AdmissionDiagnosisDTO admissionDiagnosisDTO : mlPatientDTO.getAdmissionDiagnosisDTOS()) {
//            AdmissionDiagnosisDO admissionDiagnosisDO = new AdmissionDiagnosisDO();
//            admissionDiagnosisDO.setDiagnosis(admissionDiagnosisDTO.getDiagnosis());
//            admissionDiagnosisDO.setDiagnosisDate(admissionDiagnosisDTO.getDiagnosisDate());
//            admissionDiagnosisDO.setMlPatientDO(mlPatientDO);
//            admissionDiagnosisDO.setId(admissionDiagnosisDTO.getId());
//            admissionDiagnosisDOS.add(admissionDiagnosisDO);
//        }
//        for (DischargeDiagnosisDTO dischargeDiagnosisDTO : mlPatientDTO.getDischargeDiagnosisDTOS()) {
//            DischargeDiagnosisDO dischargeDiagnosisDO = new DischargeDiagnosisDO();
//            dischargeDiagnosisDO.setDiagnosisDate(dischargeDiagnosisDTO.getDiagnosisDate());
//            dischargeDiagnosisDO.setDiagnosis(dischargeDiagnosisDTO.getDiagnosis());
//            dischargeDiagnosisDO.setMlPatientDO(mlPatientDO);
//            dischargeDiagnosisDO.setId(dischargeDiagnosisDTO.getId());
//            dischargeDiagnosisDOS.add(dischargeDiagnosisDO);
//        }
//        mlPatientDO.setAdmissionDiagnosisDOS(admissionDiagnosisDOS);
//        mlPatientDO.setDischargeDiagnosisDOS(dischargeDiagnosisDOS);
//        mlPatientDO.setHospitalId(new UserMsgTool().getCurrentUser().getHospital().getId());
//        mlPatientDO.setProjectId(mlPatientDTO.getProjectId());
//        return mlPatientDO;
//    }
//
//}