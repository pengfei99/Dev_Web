package org.bioaster.security.registration;

import org.bioaster.security.config.ValidatorConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 2/21/17.
 */
public class RegistrationValidatorService {

    private static final RegistrationValidatorService serviceInstance= new RegistrationValidatorService();

    private RegistrationValidatorService(){}

    public static RegistrationValidatorService getInstance(){
        return serviceInstance;
    }

    //Get the list of project validator name
    private static final String bioasterValidatorConfFile= ValidatorConfig.BA_VALIDATOR_CONF_File;
    //Get the bioaster project validator list
    private static final ProjectValidator bioasterValidatorList=new ProjectValidator(bioasterValidatorConfFile);



    /* The section of methoods for geting the validator name and email complete list*/

    public Map<String,String> getBioasterValidatorList() {
        return bioasterValidatorList.getValidatorList();
    }


    /*Tthis section of methods for geting the validator name list*/
    //get the bioaster project validator name list
    public List<String> getBioasterValidatorNameList() {
        bioasterValidatorList.init();
        return  bioasterValidatorList.getValidatorNameList();
    }


    /*This section of methods for getting all the eamils of one project validators*/
    public String getBioasterValidatorEmail(String projectValidatorName){
        return bioasterValidatorList.getValidatorEmail(projectValidatorName);
    }

    public List<String> getAllBioasterValidatorEmails(){
        return bioasterValidatorList.getAllValidatorEmails();
    }

}

