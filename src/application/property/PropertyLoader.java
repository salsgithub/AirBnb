package application.property;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class PropertyLoader {

	/**
	 * Loads the properties from a csv file.
	 * @return - an arraylist containing the properties to rent loaded from the csv file.
	 */
    public ArrayList<Property> load() {
        System.out.println("Loading London property data...");
        ArrayList<Property> listings = new ArrayList<Property>();
        try { 
            String url = "./data/airbnb-london.csv";
            CSVReader reader = new CSVReader(new FileReader(new File(url).getAbsolutePath()));
            String [] line;
            //skip the first row (column headers)
            reader.readNext(); 
            while ((line = reader.readNext()) != null) {
                String id = line[0];
                String name = line[1]; 
                String host_id = line[2];
                String host_name = line[3]; 
                String neighbourhood = line[4];
                double latitude = convertDouble(line[5]);
                double longitude = convertDouble(line[6]);
                String room_type = line[7];
                int price = convertInt(line[8]);
                int minimumNights = convertInt(line[9]);
                int numberOfReviews = convertInt(line[10]);
                String lastReview = line[11];
                double reviewsPerMonth = convertDouble(line[12]);
                int calculatedHostListingsCount = convertInt(line[13]);
                int availability365 = convertInt(line[14]);
                Property listing = new Property(id, name, host_id,
                        host_name, neighbourhood, latitude, longitude, room_type,
                        price, minimumNights, numberOfReviews, lastReview,
                        reviewsPerMonth, calculatedHostListingsCount, availability365
                    );
                listings.add(listing);
            }
            reader.close();
        } catch(IOException e){
            System.out.println("Error trying to load data from csv file.");
            e.printStackTrace();
        }
        System.out.println("Loaded " + listings.size() + " rental properties.");
        return listings;
    }

    /**
     * Converts a string into a double.
     * @param doubleString the string to be converted to Double type.
     * @return the Double value of the string, or -1.0 if the string is 
     * either empty or just whitespace.
     */
    private Double convertDouble(String doubleString){
        if (doubleString != null && !doubleString.trim().equals("")){
            return Double.parseDouble(doubleString);
        }
        return -1.0;
    }

    /**
     * Converts a string into an integer.
     * @param intString the string to be converted to Integer type
     * @return the Integer value of the string, or -1 if the string is 
     * either empty or just whitespace
     */
    private Integer convertInt(String intString){
        if (intString != null && !intString.trim().equals("")){
            return Integer.parseInt(intString);
        }
        return -1;
    }
}
