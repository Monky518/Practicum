import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec;
        final int FIELDS_LENGTH = 5;

        ArrayList<Person> folks = new ArrayList<>();

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                System.out.printf("\n%-8s%-15s%-15s%-6s%6s", "ID#", "FIRST NAME", "LAST NAME", "TITLE", "YOB");
                System.out.println("\n=================================================="); // do this exactly 50 times

                while (reader.ready())
                {
                    rec = reader.readLine();
                    String[] fields = rec.split(",");

                    if (fields.length == FIELDS_LENGTH)
                    {
                        System.out.printf("\n%-8s%-15s%-15s%-6s%6d", fields[0].trim(), fields[1].trim(), fields[2].trim(), fields[3].trim(), Integer.parseInt(fields[4].trim()));

                        // In the Reader, as you read a line of the data file use the java String split function to separate the fields of this single record into an array
                        // Then use the array data to create an object and add it to the arrayList
                        // Your table of Person or Product data should be generated from the ArrayList
                        folks.add(new Person(fields[0].trim(), fields[1].trim(), fields[2].trim(), fields[3].trim(), Integer.parseInt(fields[4].trim())));
                    }
                    else
                    {
                        System.out.println("\n\nData table stopped!\nFound a record that may be corrupt: ");
                        System.out.println(rec);
                    }
                }
                reader.close();
                System.out.println("\n\nData file read!");

                for (Person peep : folks)
                {
                    System.out.println("CSV:  "+ peep.toCSVDataRecord());
                }
            }
            else
            {
                System.out.println("\nNo file selected; Run the program again");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\nFile not found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
