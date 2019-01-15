package org.etriks.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Created by pliu on 3/10/15.
 */
/*
* This class takes a file location and allow user to read and write on the file with csv format
* the cvs separator is ","
*
* */
public class CSVFileManager {
    private final org.apache.log4j.Logger log= Logger.getLogger(getClass());
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

            e.printStackTrace();

        }
        return result;
    }
//This method can creat the first line of the file and set it as column key name
    public void setColumnValue(List<String> columnValueList){
        Iterator<String> iterator = columnValueList.iterator();
       while(iterator.hasNext()){
           try {
               writer.append(iterator.next());
               if(iterator.hasNext()) {  writer.append(',');}
               else { writer.append('\n');}
           } catch (IOException e) {
               e.printStackTrace();
           }


       }
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
                e.printStackTrace();
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
