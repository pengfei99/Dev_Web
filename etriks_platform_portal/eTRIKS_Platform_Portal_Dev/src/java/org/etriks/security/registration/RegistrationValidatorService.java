package org.etriks.security.registration;

import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 3/26/15.
 */
public class RegistrationValidatorService {

    private static final RegistrationValidatorService serviceInstance= new RegistrationValidatorService();

    private RegistrationValidatorService(){}

    public static RegistrationValidatorService getInstance(){
      return serviceInstance;
    }

    //Get the list of hosting validator name
    private static final String hostingValidatorConfFile="/usr/share/tomcat7/.grails/portalConfig/hostingValidator.txt";
    //Get the hosting validator list
    private static final ProjectValidator hostingValidatorList=new ProjectValidator(hostingValidatorConfFile);

    //Get the list of project validator name
    private static final String bioasterValidatorConfFile="/usr/share/tomcat7/.grails/portalConfig/bioasterValidator.txt";
    //Get the bioaster project validator list
    private static final ProjectValidator bioasterValidatorList=new ProjectValidator(bioasterValidatorConfFile);

    //Get the list of project validator name
    private static final String abiriskValidatorConfFile="/usr/share/tomcat7/.grails/portalConfig/abiriskValidator.txt";
    //Get the abirisk project validator list
    private static final ProjectValidator abiriskValidatorList=new ProjectValidator(abiriskValidatorConfFile);

    //Get the list of project validator name
    private static final String oncotrackValidatorConfFile="/usr/share/tomcat7/.grails/portalConfig/oncotrackValidator.txt";
    //Get the oncotrack project validator list
    private static final ProjectValidator oncotrackValidatorList=new ProjectValidator(oncotrackValidatorConfFile);


    /* The section of methoods for geting the validator name and email complete list*/
    //Get the hosting validator list
    public Map<String,String> getHostingValidatorList() {
        return hostingValidatorList.getValidatorList();
    }


    public Map<String,String> getBioasterValidatorList() {
        return bioasterValidatorList.getValidatorList();
    }


    public Map<String,String> getAbiriskValidatorList() {
        return abiriskValidatorList.getValidatorList();
    }


    public Map<String,String> getOncotrackValidatorList() {
        return oncotrackValidatorList.getValidatorList();
    }

    /*Tthis section of methods for geting the validator name list*/
    //get the bioaster project validator name list
    public List<String> getBioasterValidatorNameList() {
        bioasterValidatorList.init();
        return  bioasterValidatorList.getValidatorNameList();
    }

    //get the abirisk project validator name list
    public List<String> getAbiriskValidatorNameList() {
        abiriskValidatorList.init();
        return  abiriskValidatorList.getValidatorNameList();
    }
    //get the oncotrack project validator name list
    public List<String> getOncotrackValidatorNameList() {
        oncotrackValidatorList.init();
        return  oncotrackValidatorList.getValidatorNameList();
    }


    //get the hosting validator name list
    public List<String> getHostingValidatorNameList(){
        hostingValidatorList.init();
        return hostingValidatorList.getValidatorNameList();
    }


/*This section of methods for geting the email of a given validator name*/
    public String getHostingValidatorEmail(String projectValidatorName){
        return hostingValidatorList.getValidatorEmail(projectValidatorName);
    }

    public String getAbiriskValidatorEmail(String projectValidatorName){
        return abiriskValidatorList.getValidatorEmail(projectValidatorName);
    }
    public String getBioasterValidatorEmail(String projectValidatorName){
        return bioasterValidatorList.getValidatorEmail(projectValidatorName);
    }
    public String getOncotrackValidatorEmail(String projectValidatorName){
        return oncotrackValidatorList.getValidatorEmail(projectValidatorName);
    }

    /*This section of methods for getting all the eamils of one project validators*/
    public List<String> getAllAbiriskValidatorEmails(){
        return abiriskValidatorList.getAllValidatorEmails();
    }
    public List<String> getAllBioasterValidatorEmails(){
        return bioasterValidatorList.getAllValidatorEmails();
    }
    public List<String> getAllOncotrackValidatorEmails(){
        return oncotrackValidatorList.getAllValidatorEmails();
    }
    public List<String> getAllHostingValidatorEmails(){
        return hostingValidatorList.getAllValidatorEmails();
    }
}
