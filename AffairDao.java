import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class AffairDao {
    private static Connection conn = null;
    private static Statement statement = null;
    private static String mysqlCode = "";
    private static ResultSet resultSet = null;
    private static int result = 0;

    //������д��mysql��Ӧ�ı���
    public static void writeToMysql(AffairModel affairs) throws IOException {
        if(conn == null) {
            conn = MysqlConnect.getConnet();
        }
        try {
            mysqlCode = "insert into affairs(startDate, endDate, content, state) values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(mysqlCode);
            //���ò����ֶε�ֵ
            ps.setString(1, affairs.getStartDate().toString());
            ps.setString(2, affairs.getEndData().toString());
            ps.setString(3, affairs.getContent().toString());
            ps.setString(4, String.valueOf(affairs.getState()));
            result = ps.executeUpdate();
            //result���سɹ�����ļ�¼������������0��˵������ɹ������ص�ǰ���е����ID����ΪID���õ�����������Ϊ�����¼��ID
            if (result > 0) {
                statement = conn.createStatement();
                mysqlCode = "select max(id) from affairs";
                resultSet = statement.executeQuery(mysqlCode);
                int id = 0;
                while (resultSet.next()) {
                    id = Integer.parseInt(resultSet.getString(1));
                }
                //������ʾ
                JOptionPane.showMessageDialog(null, "�½�������IDΪ: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFromMysql(int id) throws IOException, SQLException {
        if(conn == null) {
            conn = MysqlConnect.getConnet();
        }
        statement = conn.createStatement();
        mysqlCode = "delete from affairs where id = " + id;
        result = statement.executeUpdate(mysqlCode);
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "��ɾ��IDΪ:" + id + "������");
        }
        else {
            JOptionPane.showMessageDialog(null, "ɾ���쳣");
        }
    }
    //��IDΪ0���ʾ��ѯȫ���������ѯ��ӦID�ļ�¼

    public static void queryFromMysql(int id) throws IOException, SQLException{
        if(conn == null) {
            conn = MysqlConnect.getConnet();
        }
        statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        if (id > 0) {
            mysqlCode = "select * from affairs where id = " + id;
        }
        else if(id == 0) {
            mysqlCode = "select * from affairs";
        }
        resultSet = statement.executeQuery(mysqlCode);
        //����ѯ���������һ���ӽ�����ʾ����
        JFrame f = new JFrame("��ѯ���");
        Container contentPane = f.getContentPane();
        Vector vector = new Vector();
        vector.add("ID");
        vector.add("��ʼʱ��");
        vector.add("����ʱ��");
        vector.add("��������");
        vector.add("����������");
        DefaultTableModel tablemodel = new DefaultTableModel(new Vector(), vector);
        Vector value = new Vector();
        try {
            while (resultSet.next()) {
                Vector vt = new Vector();
                vt.add(resultSet.getString(1));
                vt.add(resultSet.getString(2));
                vt.add(resultSet.getString(3));
                vt.add(resultSet.getString(4));
                //state�ֶ�ֵΪ1��ʾ����ɣ�0��ʾδ���
                if (resultSet.getString(5).equals("1")) {
                    vt.add("�����");
                }
                else {
                    vt.add("δ���");
                }
                value.add(vt);
            }
            tablemodel.setDataVector(value, vector);
            JTable table = new JTable(tablemodel);
            contentPane.add(new JScrollPane(table));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        f.pack();
        f.setVisible(true);
    }

    public static void changeData (AffairModel affairs) throws IOException {
        if(conn == null) {
            conn = MysqlConnect.getConnet();
        }
        try {
            mysqlCode = "update affairs set startDate = \'" + affairs.getStartDate() + "\',endDate = \'" + affairs.getEndData() +
                    "\',content = \'" + affairs.getContent() + "\' where id = " + affairs.getId();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = statement.executeUpdate(mysqlCode);
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "���³ɹ�");
            }
            else {
                JOptionPane.showMessageDialog(null, "����ʧ��");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void finishAffair(int id) throws IOException, SQLException {
        if(conn == null) {
            conn = MysqlConnect.getConnet();
        }
        statement = conn.createStatement();
        mysqlCode = "update affairs set state = 1 where id = " + id;
        result = statement.executeUpdate(mysqlCode);
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "IDΪ" + id + "�����������");
        }
        else {
            JOptionPane.showMessageDialog(null, "�����쳣");
        }
    }
    //��ȡ����ǰ���ڻ�δ��ɵ���������

    public static ArrayList<String> getAffairs(String endDate) throws IOException, SQLException {
        java.util.List<String> affairs = new ArrayList<>();
        if(conn == null) {
            conn = MysqlConnect.getConnet();
        }
        statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        mysqlCode = "select * from affairs where state = \'0\'";
        resultSet = statement.executeQuery(mysqlCode);
        while(resultSet.next()) {
        	StringBuffer sb = new StringBuffer("");
        	sb.append(resultSet.getString(4));
        	sb.append(" �Ľ�ֹ����Ϊ");
        	sb.append(resultSet.getString(3));
            affairs.add(sb.toString());
        }
        return (ArrayList<String>) affairs;
    }

    public static void queryAllFromMysql() throws IOException, SQLException {
        queryFromMysql(0);
    }
}