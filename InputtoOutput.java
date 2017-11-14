import java.io.*;

public class InputtoOutput{
  public static void main(String arg[]){
    String fileName = "input.txt";
    System.out.println("--Input Cleaner--");
    System.out.println("-Reading input file");
    String [] inputText = readInput(fileName);
    System.out.println("-Processing input file");
    String [][] processText = new String[inputText.length][];
    for (int x = 0; x < inputText.length; x++)
    {
      processText [x] =  inputText[x].split(",");
    }
    String [][] outputText = processData(processText);
    System.out.println("-Creating output file");
    createOutput(outputText);
    System.out.println("--Completed Program--");
  }
  
  public static String [] readInput(String fileName) {
    // This will reference one line at a time
    String line = null;
    String [] inputText;
    int lineIndex = 0;
    int fileSize = 0;
    try {
      // FileReader reads text files in the default encoding.
      FileReader fileReader = new FileReader(fileName);

      //BufferReader to find file size
      BufferedReader lineReader = new BufferedReader(fileReader);
      while((line = lineReader.readLine()) != null) {
        fileSize++;
      }    
      // Always close files.
      lineReader.close();         
    }
    catch(FileNotFoundException ex) {
      System.out.println("Unable to open file '" + 
                         fileName + "'");                
    }
    catch(IOException ex) {
      System.out.println(
                         "Error reading file '" 
                           + fileName + "'");                  
    }
    //Declare array size
    inputText = new String[fileSize];
    try {
      //FileReader reads text files in the default encoding.
      FileReader fileReader = new FileReader(fileName);
      //Reread the file to save the content
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null) {   
        inputText [lineIndex] = line;
        lineIndex++;
      }   
      //Always close files.
      bufferedReader.close();         
    }
    catch(FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");                
    }
    catch(IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");                  
    }
    return inputText;
  }
  
   public static String [][] processData(String [][] procData) {
     String [][] returnData = new String [procData.length][];
     for (int x = 0; x < procData.length; x++)
     {
       returnData[x] = new String [procData[x].length];
       for (int y = 0; y < procData[x].length; y++)
       {
         if(procData[x][y].equals(""))
         {
         returnData[x][y] = "null";
         }
         else if(procData[x][y].equals("\"\""))
         {
           returnData[x][y] = "null";
         }
         else{
           returnData[x][y] = procData[x][y];
         }
       }
     }
     return returnData;
   }
   
   public static void createOutput(String [][] outputData) {
     BufferedWriter writer = null;
     String line;
     try {
       writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));
       for (int x = 0; x < outputData.length; x++)
       {
         line = String.join(",",outputData[x]);
         //System.out.println(line);
         writer.write(line);
         writer.newLine();
       }
     } catch (IOException ex) {
       System.out.println("Error writing file 'output.txt'"); 
     } finally {
       try {writer.close();} 
       catch (Exception ex) {}
     }
   }
}