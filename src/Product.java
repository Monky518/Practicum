import java.time.Year;

public class Product
{
    private String name;
    private String description;
    private String ID;
    private double cost;

    public Product(String name, String description, String ID, double cost) // add data
    {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.cost = cost;
    }

    public String toCSVDataRecord()
    {
        // returns a comma separated value (csv) String suitable to writing to a java text file
        // Be sure to use this function when you save data to the file
        // You can use the for each loop to traverse the ArrayList and use this function to generate the CSV record to write

        // When you read the data in from the CSV text file, instantiate a Person object from each record and store the Person objects within an ArrayList of type <Person>
        // Similarly, when in PersonGenerator as the user enters the data fields for each person, use it to create a Person Object
        // Store it in an ArrayList and then write all the records to the text file when they are finished

        return (name + "," + description + "," + ID + "," + cost);
    }
}
