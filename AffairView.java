import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AffairView extends JFrame {

    JPanel panel;

    JLabel affairIdLabel;
    JTextField affairIdText;
    JPanel idPanel;

    JRadioButton addRadioButton;
    JLabel startDateLabel;
    JTextField startDateText;
    JLabel endDateLabel;
    JTextField endDateText;
    JLabel contentLabel;
    TextArea contentText;
    JPanel addPanel;

    JRadioButton delRadioButton;
    JPanel delPanel;
    JRadioButton queryRadioButton;
    JPanel queryPanel;
    JRadioButton queryAllRadioButton;
    JPanel queryAllPanel;
    JRadioButton finishButton;
    JPanel finishedPanel;

    JRadioButton chgRadioButton;
    JLabel startDateLabel2;
    JTextField startDateText2;
    JLabel endDateLabel2;
    JTextField endDateText2;
    JLabel contentLabel2;
    TextArea contentText2;
    JPanel chgPanel;

    ButtonGroup buttonGroupOption;
    JButton okButton;

    AffairModel affairs;
    static final int SIZE = 7;

    public AffairView() {
        affairs = new AffairModel();

        addRadioButton = new JRadioButton("�������");
        startDateText = new JTextField(SIZE);
        startDateLabel = new JLabel("��ʼ����");
        endDateText = new JTextField(SIZE);
        endDateLabel = new JLabel("��������");
        contentText = new TextArea(3, 15);
        contentLabel = new JLabel("��������");

        affairIdLabel = new JLabel("����ID");
        affairIdText = new JTextField(SIZE);

        delRadioButton = new JRadioButton("ɾ������");

        queryRadioButton = new JRadioButton("��ѯ����");
        queryAllRadioButton = new JRadioButton("��ѯ��������");

        chgRadioButton = new JRadioButton("�޸�����");
        startDateText2 = new JTextField(SIZE);
        startDateLabel2 = new JLabel("��ʼ����");
        endDateText2 = new JTextField(SIZE);
        endDateLabel2 = new JLabel("��������");
        contentText2 = new TextArea(3, 15);
        contentLabel2 = new JLabel("��������");

        finishButton = new JRadioButton("�������");

        okButton = new JButton("�ύ");
        buttonGroupOption = new ButtonGroup();
        buttonGroupOption.add(addRadioButton);
        buttonGroupOption.add(delRadioButton);
        buttonGroupOption.add(finishButton);
        buttonGroupOption.add(queryRadioButton);
        buttonGroupOption.add(chgRadioButton);
        buttonGroupOption.add(queryAllRadioButton);

        addPanel = new JPanel(new FlowLayout());
        addPanel.add(addRadioButton);
        addPanel.add(startDateLabel);
        addPanel.add(startDateText);
        addPanel.add(endDateLabel);
        addPanel.add(endDateText);
        addPanel.add(contentLabel);
        addPanel.add(contentText);

        idPanel = new JPanel(new FlowLayout());
        idPanel.add(affairIdLabel);
        idPanel.add(affairIdText);

        JPanel tmpPanel = new JPanel(new GridLayout(5, 1));
        delPanel = new JPanel(new FlowLayout());
        delPanel.add(delRadioButton);

        queryPanel = new JPanel(new FlowLayout());
        queryPanel.add(queryRadioButton);

        queryAllPanel = new JPanel(new FlowLayout());
        queryAllPanel.add(queryAllRadioButton);

        finishedPanel = new JPanel(new FlowLayout());
        finishedPanel.add(finishButton);

        tmpPanel.add(queryAllPanel);
        tmpPanel.add(idPanel);
        tmpPanel.add(delPanel);
        tmpPanel.add(queryPanel);
        tmpPanel.add(finishedPanel);

        chgPanel = new JPanel(new FlowLayout());
        chgPanel.add(chgRadioButton);
        chgPanel.add(startDateLabel2);
        chgPanel.add(startDateText2);
        chgPanel.add(endDateLabel2);
        chgPanel.add(endDateText2);
        chgPanel.add(contentLabel2);
        chgPanel.add(contentText2);

        panel = new JPanel(new FlowLayout());
        panel.add(addPanel);
        panel.add(tmpPanel);
        panel.add(chgPanel);
        panel.add(okButton);
        add(panel);
        
        //�����ύ��ť�ļ�����
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//�ж��ĸ���ѡ��ť��ѡ��
                if (addRadioButton.isSelected()) {
                    try {
                        affairs.setState(false);
                        affairs.setStartDate(startDateText.getText());
                        affairs.setEndData(endDateText.getText());
                        affairs.setContent(new StringBuffer(contentText.getText()));
                        AffairDao.writeToMysql(affairs);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if(queryAllRadioButton.isSelected()) {
                    try {
                        AffairDao.queryAllFromMysql();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                } else if (delRadioButton.isSelected()) {
                    try {
                        AffairDao.deleteFromMysql(Integer.parseInt(affairIdText.getText()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else if (queryRadioButton.isSelected()) {
                    try {
                        AffairDao.queryFromMysql(Integer.parseInt(affairIdText.getText()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else if (chgRadioButton.isSelected()) {
                    try {
                        affairs.setId(Integer.parseInt(affairIdText.getText()));
                        affairs.setState(false);
                        affairs.setStartDate(startDateText2.getText());
                        affairs.setEndData(endDateText2.getText());
                        affairs.setContent(new StringBuffer(contentText2.getText()));
                        AffairDao.changeData(affairs);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (finishButton.isSelected()) {
                    try {
                        AffairDao.finishAffair(Integer.parseInt(affairIdText.getText()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}

