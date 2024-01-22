import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        Scanner scan = new Scanner(System.in);
        File selectedFile;
        String rec = "";

        ArrayList<String> lines = new ArrayList<>();
        final int FIELDS_LENGTH = 5;
        String id, firstName, lastName, title;
        int yob;
        String regExPattern = "";

        boolean done = false;

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);      // this is in reading only, but i think it also goes here for safe file selector
            Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");        // this after chooser to not confuse
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                /*
                Your program will prompt the user to enter lines of data for a file on persons
                You don’t know ahead of time how many Persons will be entered so use an ArrayList to store the records

                Once the user indicates they have entered all the data elements for each person,
                    save it to a text file using a name they provided
                    (Be sure to only save complete sets of elements)
                 */

                /*
                String[] fields;
                for(String l:lines)
                {
                    fields = l.split(","); // Split the record into the fields

                    if(fields.length == FIELDS_LENGTH)
                    {
                        id        = fields[0].trim();
                        firstName = fields[1].trim();
                        lastName  = fields[2].trim();
                        title     = fields[3].trim();
                        yob       = Integer.parseInt(fields[4].trim());
                        System.out.printf("\n%-8s%-25s%-25s%-6s%6d", id, firstName, lastName, title, yob);
                    }
                    else
                    {
                        System.out.println("Found a record that may be corrupt: ");
                        System.out.println(l);
                    }
                 }
                 */

                do
                {
                    String userInput = SafeInput.getNonZeroLenString(scan, "Enter line " + lines.length + " of data for the file [type done when done]");
                    if (userInput.equalsIgnoreCase("done"))
                    {
                        done = true;
                    }
                    else if (userInput.matches(regExPattern))
                    {
                        // YIPPEEEEEE
                    }
                    else
                    {
                        // trash
                    }

                } while (!done);
            }
            else
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void ShowLines(ArrayList<String> arrList)
    {

    }
}