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
        Scanner scan = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        final int FIELDS_LENGTH = 5;
        boolean done = false;

        try
        {
            /*
                Your program will prompt the user to enter lines of data for a file on persons
                You don’t know ahead of time how many Persons will be entered so use an ArrayList to store the records

                Once the user indicates they have entered all the data elements for each person,
                    save it to a text file using a name they provided
                    (Be sure to only save complete sets of elements)
            */

            do
            {
                if (!lines.isEmpty())
                    ShowLines(lines);

                String userInput = SafeInput.getNonZeroLenString(scan, "Enter your ID, First name, Last name, Title, and Year of birth or type done");

                // checking data
                String[] test;
                test = userInput.split(","); // Split the record into the fields

                if (userInput.equalsIgnoreCase("done"))
                {
                    done = true;

                    // getting file name and saving it to location
                    String fileName = SafeInput.getNonZeroLenString(scan,"Enter your new file name");
                    File workingDirectory = new File(System.getProperty("user.dir"));
                    Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

                    // writing the file
                    OutputStream out =
                            new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                    BufferedWriter writer =
                            new BufferedWriter(new OutputStreamWriter(out));

                    for(String l:lines)
                    {
                        writer.write(l, 0, l.length());
                        writer.newLine();
                    }

                    writer.close();
                    System.out.println("Data file written!");
                }
                else if (test.length == FIELDS_LENGTH)
                {
                    // add data
                    lines.add(userInput);
                }
                else
                {
                    System.out.println("\n\nYour input does not match the requirement; try again");
                }

            } while (!done);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\n\nFile not found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void ShowLines(ArrayList<String> arrList)
    {
        int num = 0;
        for (String line:arrList)
        {
            num++;
            String[] fields = line.split(",");

            String id        = fields[0].trim();
            String firstName = fields[1].trim();
            String lastName  = fields[2].trim();
            String title     = fields[3].trim();
            int yob          = Integer.parseInt(fields[4].trim());

            System.out.printf("\nLine %4d | %-8s%-15s%-15s%-6s%6d", num, id, firstName, lastName, title, yob);
        }
        System.out.println("\n");
    }
}