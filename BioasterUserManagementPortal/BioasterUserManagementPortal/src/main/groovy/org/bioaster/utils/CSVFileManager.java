package org.bioaster.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Created by pliu on 2/21/17.
 */
public class CSVFileManager {

    static Logger log = LoggerFactory.getLogger(CSVFileManager.class);
    private final String fileLocation;
    private Map<String,String> validatorList=new HashMap<String, String>();
    String line=" ";
    String cvsSplitBy=",";
    private FileWriter writer;

    public CSVFileManager(String fileLocation){
        this.fileLocation=fileLocation;
    }
    // this method create file write on the file
    public boolean writeToCsvFile(){
        boolean result=false;
        try {
            writer=new FileWriter(fileLocation);
            result=true;
        } catch (IOException e) {
            log.error(e.toString());
        }
        return result;
    }
    //This method can creat the first line of the file and set it as column key name
    public void setColumnValue(List<String> columnValueList){
        this.addline(columnValueList);
    }

    /*
    * This method add lines to the file. The line value should match the column key name*/
    public void addline(List<String> lineValueList){
        Iterator<String> iterator = lineValueList.iterator();
        while(iterator.hasNext()){
            try {
                writer.append(iterator.next());
                if(iterator.hasNext()) {  writer.append(',');}
                else { writer.append('\n');}
            } catch (IOException e) {
                log.error(e.toString());
            }


        }
    }

    /*
    * Close the file writer
    * */
    public boolean stopWriting(){
        boolean result=false;
        try {
            writer.close();
            result=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<List<String>> readFile(){
        BufferedReader br=null;
        List<List<String>> result=new ArrayList<List<String>>();
        try {
            br=new BufferedReader(new FileReader(fileLocation));
            while((line=br.readLine())!=null){
                String[] lineValues=line.split(cvsSplitBy);

                List<String> lines= new ArrayList<String>(Arrays.asList(lineValues));

                result.add(lines);

            }
            br.close();
        } catch (FileNotFoundException e) {
            log.error("Unable to open bioasterValidator csv file at"+fileLocation+" !!! Java exception:"+e.toString());
        } catch (IOException e) {
            log.error("Unable to read line in bioasterValidator csv file at"+fileLocation+" !!! Java exception:"+e.toString());
        }
        return result;
    }

    public List<String> readLine(int lineNumber){
        List<List<String>> allLines = this.readFile();
        return allLines.get(lineNumber);

    }

    public List<List<String>> readAllLines(){
        List<List<String>> allLines=this.readFile();
        allLines.remove(0);
        return allLines;
    }
    public List<String> readColumn(String columnName) throws IOException {
        List<String> columnValueList=new ArrayList<String>();
        List<List<String>> allLines=this.readFile();
        List<String> columnKeys=allLines.get(0);
        //the column name exist
        if( columnKeys.contains(columnName) ){
            int index= columnKeys.indexOf(columnName);

            for (int i=1;i<allLines.size();i++){
                String columnValue= allLines.get(i).get(index);
                columnValueList.add(columnValue);
            }
        }
        //the column does not exist
        else {
            throw new IOException(" Column name which user entered does not exist in the target file");
        }
        return columnValueList;
    }
}
