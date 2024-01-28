import java.time.Year;

public class Person
{
    private String firstName;
    private String lastName;
    private String ID;      // should never change sequence of digits
    private String title;   // a prefix: Mr. Mrs. Ms, Prof. Dr. Hon. Etc.
    private int YOB;        // Year of birth  // Range should be 1940 - 2000

    public Person(String firstName, String lastName, String ID, String title, int YOB) // add data
    {
        this.firstName = firstName; // this is the class while just firstName is the class
        this.lastName = lastName;
        this.ID = ID;
        this.title = title;
        this.YOB = YOB;
    }

    public String fullName()    // returns firstName, space, lastName
    {
        return (firstName + " " + lastName);
    }

    public String formalName()  // returns title, space, fullName
    {
        return (title + " " + fullName());
    }

    public String getAge()  // returns the age assuming the current year
    {
        return (String.valueOf(Year.now().getValue() - YOB));
    }

    public String getAge(int year)  // uses YOB to calculate age for a specified year
    {
        return (String.valueOf(year - YOB));
    }

    public String toCSVDataRecord()
    {
        // returns a comma separated value (csv) String suitable to writing to a java text file
        // Be sure to use this function when you save data to the file
        // You can use the for each loop to traverse the ArrayList and use this function to generate the CSV record to write

        // When you read the data in from the CSV text file, instantiate a Person object from each record and store the Person objects within an ArrayList of type <Person>
        // Similarly, when in PersonGenerator as the user enters the data fields for each person, use it to create a Person Object
        // Store it in an ArrayList and then write all the records to the text file when they are finished

        return (ID + "," + firstName + "," + lastName + "," + YOB);
    }
}
