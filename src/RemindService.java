import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


public class RemindService {
    Calendar calendar = Calendar.getInstance();
    public RemindService() throws IOException, SQLException {
        StringBuffer sb = new StringBuffer("");
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);      
        sb.append(DateStd.dateStd(month));  
        sb.append("-");
        sb.append(DateStd.dateStd(day));
        
        //��ȡ��������յ��˵������б�
        ArrayList<String> nameList = PersonDao.getBirthday((String)sb.toString());
        StringBuffer sb2 = new StringBuffer("");
        sb2.append(calendar.get(Calendar.YEAR));
        sb2.append("-");
        sb2.append(sb);
        
        //��ȡ���컹δ��ɵ������б�
        ArrayList<String> affairList = AffairDao.getAffairs((String)sb2.toString());
        StringBuffer message = new StringBuffer("");
        int nameNum = nameList.size();
        if (nameNum != 0) {
            message.append("��������յ���:\n");
            for (int i = 0; i < nameNum; i ++) {
                message.append(nameList.get(i) + "\n");
            }
        }
        int affairNum = affairList.size();
        if (affairNum != 0) {
            message.append("��������:\n");
            for (int i = 0; i < affairNum; i ++) {
                message.append(affairList.get(i) + ".\n");
            }
        }
        //���������˹����ջ�������δ����򵯴���ʾ
        if (nameNum + affairNum > 0) {
            JOptionPane.showMessageDialog(null, message);
        }
    }
}