package Calender;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Calender {

    private final List<String> Months = Arrays.asList(new DateFormatSymbols().getMonths());
    private final List<String> Days = Arrays.asList(new DateFormatSymbols().getShortWeekdays());
    private final String GAP = "   ";

    private boolean isValidMonth(int month) {
        if (month <= 0 || month > 12)
            throw new InvalidMonthException();
        return true;
    }

    private boolean isValidYear(int year) {
        if (year <= 0)
            throw new InvalidYearException();
        return true;
    }

    public boolean isLeapYear(int year) {
        if (isValidYear(year)) {
            return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        }
        return false;
    }

    public int getNoOfDays(int year, int month) {

        final List<Integer> _days30 = Arrays.asList(4,6,9,11);
        final List<Integer> _days31 = Arrays.asList(1,3,5,7,8,10,12);

        if (isValidYear(year) && isValidMonth(month)) {
            if (month == 2)
                return isLeapYear(year) ? 29 : 28;
            if (_days30.contains(month))
                return 30;
            if (_days31.contains(month))
                return 31;
        }

        return -1;
    }

    public String dayOfMonth(int year, int month, int date) {
        if (isValidYear(year) && isValidMonth(month)) {
            YearMonth yobj = YearMonth.of(year, month);
            String day =  yobj.atDay(date).getDayOfWeek().name();
            return day.charAt(0)+day.substring(1).toLowerCase();
        }
        return null;
    }

    public void showMonth(int year, int month) {

        if (isValidYear(year) && isValidMonth(month)) {

            int currentDay = Days.indexOf(dayOfMonth(year,month,1).substring(0,3))-1;
            int totalDays = getNoOfDays(year,month);
            print(Months.get(month-1)+", "+year+"\n");
            Days.stream().map(d -> d + GAP).forEachOrdered(System.out::print);
            print("\n");
            int s;
            for (s=0; s<currentDay; s++)
                print(GAP+GAP);
            for (int d=1; d<=totalDays; d++) {
                print(String.format("%6s", d));
                if (++s > 6) {
                    s = 0;
                    print("\n");
                }
            }
            if (s > 0) print("\n");
        }
    }

    public void showYear(int year) {
        if (isValidYear(year)) {
            print("Calender "+year+"\n");
            for (int i=1; i<=12; i++) {
                showMonth(year,i);
            }
        }
    }

    public int getCurrentYear() {
        return YearMonth.now().getYear();
    }

    public int getCurrentMonthIndex() {
        return YearMonth.now().getMonthValue();
    }

    public String getCurrentMonth() {
        return Months.get(getCurrentMonthIndex() - 1);
    }

    public void showClock() {
        SwingUtilities.invokeLater(() -> new clock());
    }


//    Utility Methods
    private void print(Object s) {
        System.out.print(s);
    }

}

// Exceptions
class InvalidMonthException extends RuntimeException {
    public InvalidMonthException() {
        super("Invalid Month, Month index should lie between 1-12");
    }
}

class InvalidYearException extends RuntimeException {
    public InvalidYearException() {
        super("Year Can't be negative or zero");
    }
}

// Clock
class clock extends JFrame implements Runnable {
    public clock() {
        super("CLock");
        Image icon = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB_PRE);
        setIconImage(icon);
        setLayout(new BorderLayout());
        setSize(new Dimension(AppSize));

        timeThread = new Thread(this);
        timeThread.start();

        initComponents();
        add(mainCompPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                SimpleDateFormat tFormat = new SimpleDateFormat("hh:mm:ss");
                Date date = new Date();
                timeLabel.setText(tFormat.format(date));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void initComponents() {
        mainCompPanel = new JPanel();
        mainCompPanel.setSize(AppSize);
        mainCompPanel.setLayout(new GridBagLayout());
        mainCompPanel.setBackground(Color.WHITE);
        mainCompPanel.setBorder(BorderFactory.createMatteBorder(
                1,0,0,0,new Color(0,0,0,90)));

        timeLabel.setSize(150,80);
        timeLabel.setForeground(new Color(0,0,0,150));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        mainCompPanel.add(timeLabel);
    }

    private final Dimension AppSize = new Dimension(340,120);
    private JPanel mainCompPanel;
    private final JLabel timeLabel = new JLabel();

    Thread timeThread;

}

