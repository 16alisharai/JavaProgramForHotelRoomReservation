import java.text.*;
import java.util.Date;
import java.util.*;
import java.lang.*;

public class FindCheapestHotel {

    static HashMap<String, List<Integer>> weekDaysHotelPrices = new HashMap<String, List<Integer>>();
    static HashMap<String, List<Integer>> weekEndHotelPrices = new HashMap<String, List<Integer>>();
    static String customerType;
    static ArrayList<Integer> listOfWeekDay = new ArrayList<Integer>();

    public static void weekDaysHotelRates() {
        weekDaysHotelPrices.put("Oberoi", new ArrayList<Integer>(Arrays.asList(11000, 8000)));
        weekDaysHotelPrices.put("Taj", new ArrayList<Integer>(Arrays.asList(16000, 11000)));
        weekDaysHotelPrices.put("Raddison", new ArrayList<Integer>(Arrays.asList(22000, 10000)));
    }

    public static void weekEndHotelRates() {
        weekEndHotelPrices.put("Oberoi", new ArrayList<Integer>(Arrays.asList(9000, 8000)));
        weekEndHotelPrices.put("Taj", new ArrayList<Integer>(Arrays.asList(6000, 5000)));
        weekEndHotelPrices.put("Raddison", new ArrayList<Integer>(Arrays.asList(15000, 4000)));
    }

    public static int splitDate(String date) {
        String tempDate = addChar(date, '-', 2);     //16-Mar2009
        String tempDate1 = addChar(tempDate, '-', 6);      //16-Mar-2009
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
        Date date2;

        try {
            date2 = formatter2.parse(tempDate1);
            return getDayNumberOld(date2);

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Date format incorrect!!");
        }
        return 0;
    }

    public static String addChar(String str, char ch, int position) {
        return str.substring(0, position) + ch + str.substring(position);  // to add '-' in between
    }

    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String findBestHotel(String custType) {
        int costOfOberoi, costOfTaj, costOfRaddison;
        costOfOberoi = calculateCostOfHotel("Oberoi", custType);
        costOfTaj = calculateCostOfHotel("Taj", custType);
        costOfRaddison = calculateCostOfHotel("Raddison", custType);

        if (costOfOberoi < costOfTaj && costOfOberoi < costOfRaddison) return "Oberoi";
        else if (costOfTaj < costOfOberoi && costOfTaj < costOfRaddison) return "Taj";
        else if (costOfRaddison < costOfOberoi && costOfRaddison < costOfTaj) return "Raddison";
        else {
            int minValue = Math.min(Math.min(costOfOberoi, costOfTaj), costOfRaddison);
            if (minValue == costOfOberoi && minValue == costOfTaj && minValue == costOfRaddison) return "Raddison";
            else if (minValue == costOfOberoi && minValue == costOfTaj) return "Taj";
            else return "Raddison";
        }
    }

    public static int calculateCostOfHotel(String hotelName, String custType) {

        int cost = 0;
        for (int a : listOfWeekDay) {
            if (a == 1 || a == 7) {
                if (custType.equals("Regular")) {
                    cost += weekEndHotelPrices.get(hotelName).get(0);
                } else {
                    cost += weekEndHotelPrices.get(hotelName).get(1);
                }
            } else {
                if (customerType.equals("Regular")) {
                    cost += weekDaysHotelPrices.get(hotelName).get(0);
                } else {
                    cost += weekDaysHotelPrices.get(hotelName).get(1);
                }
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        weekDaysHotelRates();
        weekEndHotelRates();
        String date = sc.next();                                     //date input separated by comma
        String[] dates = date.split(",", -1);
        customerType = sc.next();                                  //input for customer type
        for (String dd : dates) {
            listOfWeekDay.add(splitDate(dd));
        }
        System.out.println(findBestHotel(customerType));
    }
}





