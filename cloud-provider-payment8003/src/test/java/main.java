import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class main {
    public static void main(String[] args) {
        String[] monthBetween = getMonthBetween("2022-09", "2023-01");
        for(String s:monthBetween){
            System.out.println(s);
        }

    }
    /**获取两个时间节点之间的月份列表**/
    private static String[] getMonthBetween(String minDate, String maxDate){
        ArrayList<Calendar> result = new ArrayList<Calendar>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();
            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(curr.getTime());
                result.add(calendar);
                curr.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] stringArray= new String[result.size()];
        for(int i=0;i<result.size();i++) {

            Calendar calendar = result.get(i);
            int month = calendar.get(Calendar.MONTH)+1;
            switch (month) {
                case 1:
                    stringArray[i] = "january";break;
                case 2:
                    stringArray[i] = "february";break;
                case 3:
                    stringArray[i] = "March";break;
                case 4:
                    stringArray[i] = "April";break;
                case 5:
                    stringArray[i] = "May";break;
                case 6:
                    stringArray[i] = "june";break;
                case 7:
                    stringArray[i] = "july";break;
                case 8:
                    stringArray[i] = "august";break;
                case 9:
                    stringArray[i] = "september";break;
                case 10:
                    stringArray[i] = "october";break;
                case 11:
                    stringArray[i] = "november";break;
                case 12:
                    stringArray[i] = "december";break;

            }
        }
        return stringArray;
    }
}
