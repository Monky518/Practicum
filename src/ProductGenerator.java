import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        final int FIELDS_LENGTH = 4;
        boolean done = false;

        ArrayList<Product> products = new ArrayList<>();

        try
        {
            do
            {
                if (!lines.isEmpty())
                    ShowLines(lines);

                String userInput = SafeInput.getNonZeroLenString(scan, "Enter product ID, Name, Description, and Cost or type done");

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
                    System.out.println("Data file written!\n");

                    for (Product piece : products)
                    {
                        System.out.println("CSV:  "+ piece.toCSVDataRecord());
                    }
                }
                else if (test.length == FIELDS_LENGTH)
                {
                    // add data
                    lines.add(userInput);
                    products.add(new Product(test[0].trim(), test[1].trim(), test[2].trim(), Double.parseDouble(test[3].trim())));
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

            String id = fields[0].trim();
            String name = fields[1].trim();
            String description = fields[2].trim();
            double cost = Double.parseDouble(fields[3].trim());

            System.out.printf("\nLine %4d | %-8s%-10s%-25s%-6.1f", num, id, name, description, cost);
        }
        System.out.println("\n");
    }
}
