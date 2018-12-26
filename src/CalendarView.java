import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;


public class CalendarView extends JFrame implements ActionListener {

    public JLabel[] dayLabel = new JLabel[50];
    public JLabel[] weekLabel = new JLabel[7];
    public static final String[] WEEKNAME = { "��", "һ", "��", "��", "��", "��", "��" };
    public JLabel queryLabel, monthLabel, yearLabel;
    public JLabel showMessage = new JLabel("", JLabel.CENTER);

    public JTextField yearInput, monthInupt;
    public JButton nextMonthButton, previousMonthButton, queryButton;
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    private String[] days;
    private CalendarModel calendar;

    public CalendarView() {
        Panel pCenter = new Panel();
        pCenter.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < 7; i ++) {
            weekLabel[i] = new JLabel("", JLabel.CENTER);
            weekLabel[i].setText(WEEKNAME[i]);
            pCenter.add(weekLabel[i]);
        }
        for (int i = 0; i < 42; i ++) {
            dayLabel[i] = new JLabel("", JLabel.CENTER);
            pCenter.add(dayLabel[i]);
        }
        calendar = new CalendarModel();
        calendar.setYear(year);
        calendar.setMonth(month);
        
        //��ȡ�����������鲢��ֵ����Ӧ��ǩ
        days = calendar.getCalendar();
        for (int i = 0; i < 42; i ++) {
            dayLabel[i].setText(days[i]);
        }

        queryLabel = new JLabel("��ѯ  ");
        yearLabel = new JLabel("���");
        monthLabel = new JLabel("�·�");
        yearInput = new JTextField(5);
        monthInupt = new JTextField(5);
        queryButton = new JButton("ȷ��");
        nextMonthButton = new JButton("����");
        previousMonthButton = new JButton("����");
        showMessage.setText(year + " �� " + month + " �� ");
        queryButton.addActionListener(this);
        nextMonthButton.addActionListener(this);
        previousMonthButton.addActionListener(this);
        Panel pNorth = new Panel();
        Panel pSouth = new Panel();
        pSouth.add(queryLabel);
        pSouth.add(yearLabel);
        pSouth.add(yearInput);
        pSouth.add(monthLabel);
        pSouth.add(monthInupt);
        pSouth.add(queryButton);
        pNorth.add(showMessage);
        pNorth.add(previousMonthButton);
        pNorth.add(nextMonthButton);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(pCenter);
        add(scrollPane, BorderLayout.CENTER);
        add(pNorth, BorderLayout.NORTH);
        add(pSouth, BorderLayout.SOUTH);
    }
    
    //�ؼ�����
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextMonthButton) {
            month ++;
            if (month > 12) {
                month = 1;
                year ++;
            }
        } else if (e.getSource() == previousMonthButton) {
            month --;
            if (month < 1) {
                month = 12;
                year --;
            }
        } else {
            String yearValue = yearInput.getText();
            String monthValue = monthInupt.getText();

            try {
                year = Integer.parseInt(yearValue);
                month = Integer.parseInt(monthValue);
                if (month > 12 || month < 1 || year < 1) {
                    JOptionPane.showMessageDialog(null, "��������ȷ�·ݻ��·�");
                    return;
                }
            } catch (NumberFormatException ee) {
                JOptionPane.showMessageDialog(null, "��������ȷ����ݼ��·�");
            }
        }
        try {
            Thread.sleep(100);
            showMessage.setText(year + " �� " + month + " �� ");
            calendar.setMonth(month);
            calendar.setYear(year);
            days = calendar.getCalendar();
            for (int i = 0; i < 42; i ++) {
                dayLabel[i].setText(days[i]);
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
