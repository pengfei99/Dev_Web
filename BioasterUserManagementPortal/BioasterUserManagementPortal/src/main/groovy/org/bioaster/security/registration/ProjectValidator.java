package org.bioaster.security.registration;

import org.bioaster.utils.CSVFileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pliu on 2/21/17.
 */
public class ProjectValidator {

    static Logger log = LoggerFactory.getLogger(ProjectValidator.class);
    private final String fileLocation;
    private Map<String,String> validatorList=new HashMap<String, String>();

    public ProjectValidator(String fileLocation){
        this.fileLocation=fileLocation;
    }

    //This method read the Validator file and return a map of validator, key is the validator's name and the vaule is the validator's email
    public void init(){
        validatorList.clear();
        CSVFileManager csvFileManager=new CSVFileManager(fileLocation);
        List<List<String>> allLines
                = csvFileManager.readAllLines();
        for(List<String> line:allLines){
            String validatorName=line.get(0);
            String validatorEmail=line.get(1);
            validatorList.put(validatorName,validatorEmail);
        }

    }
    /*This method get the list of validator's name */
    public List<String> getValidatorNameList(){
        List<String> validatorNameList=new ArrayList<String>();
        for(Map.Entry<String,String> entry:validatorList.entrySet()){
            validatorNameList.add(entry.getKey());
        }
        return validatorNameList;
    }
    /*This method take the validator name and return the validator emails.*/
    public String getValidatorEmail(String validatorName){

        return validatorList.get(validatorName);


    }
    public List<String> getAllValidatorEmails(){
        List<String> validatorEmailList=new ArrayList<String>();
        for(Map.Entry<String,String> entry:validatorList.entrySet()){
            validatorEmailList.add(entry.getValue());
        }
        return validatorEmailList;
    }

    public Map<String,String> getValidatorList(){
        this.init();
        return validatorList;
    }


}
