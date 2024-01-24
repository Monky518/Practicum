import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec;
        final int FIELDS_LENGTH = 4;

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

                System.out.printf("\n%-8s%-10s%-25s%-7s", "ID#", "NAME", "DESCRIPTION", "COST");
                System.out.println("\n=================================================="); // do this exactly 50 times

                while (reader.ready())
                {
                    rec = reader.readLine();

                    String[] fields = rec.split(",");
                    if (fields.length == FIELDS_LENGTH)
                    {
                        System.out.printf("\n%-8s%-10s%-25s%-6.1f", fields[0].trim(), fields[1].trim(), fields[2].trim(), Double.parseDouble(fields[3].trim()));
                    }
                    else
                    {
                        System.out.println("\n\nData table stopped!\nFound a record that may be corrupt: ");
                        System.out.println(rec);
                    }
                }
                reader.close();
                System.out.println("\n\nData file read!");
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
