import java.io.IOException;
import java.sql.SQLException;


public class Runner {

    public static void main(String[] args) throws IOException, SQLException {
        RunnerView runnerView = new RunnerView("����/��������ϵͳ");
        runnerView.setBounds(300, 300, 200, 300);
        runnerView.setVisible(true);
        runnerView.validate();
        new RemindService();
        //����˳�����
        runnerView.addWindowListener(new java.awt.event.WindowAdapter() {
        	public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
    }
}