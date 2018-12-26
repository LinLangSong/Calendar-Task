import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RunnerView extends JFrame implements ActionListener{

    Button calendarButton;
    Button personButton;
    Button affairButton;
    JPanel panel;

    public RunnerView(String s) {
        calendarButton = new Button("������ѯ");
        personButton = new Button("�ҵ���ϵ��");
        affairButton = new Button("�ҵ�����");
        panel = new JPanel(new FlowLayout(100, 50, 50));
        panel.add(calendarButton);
        panel.add(personButton);
        panel.add(affairButton);
        this.setTitle(s);
        add(panel);

        calendarButton.addActionListener(this);
        personButton.addActionListener(this);
        affairButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calendarButton) {
            CalendarView calendarView = new CalendarView();
            calendarView.setTitle("����");
            calendarView.setBounds(300, 300, 500, 400);
            calendarView.setVisible(true);
            calendarView.validate();
        }
        else if (e.getSource() == personButton) {
            PersonView personView = new PersonView();
            personView.setTitle("�ҵ���ϵ��");
            personView.setBounds(300, 300, 600, 300);
            personView.setVisible(true);
            personView.validate();
            personView.setResizable(false);
        }
        else if (e.getSource() == affairButton) {
            AffairView affairView = new AffairView();
            affairView.setTitle("�ҵ�����");
            affairView.setBounds(300, 300, 600, 450);
            affairView.setVisible(true);
            affairView.validate();
            affairView.setResizable(false);
        }
    }
}