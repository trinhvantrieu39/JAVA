package Button;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ButtonReload extends JButton{
	public ButtonReload() {
		this.setText(" Reload ");
		this.setIcon(new ImageIcon(this.getClass().getResource("/images/reload.png")));
		this.setBackground(new Color(220,220,220));
		//this.setForeground(Color.WHITE);	//đổi màu chữ
		this.addMouseListener(new MouseAdapter() {	//hover
						
			@Override
			public void mouseExited(MouseEvent e) {	
				// TODO Auto-generated method stub
				setBackground(new Color(220,220,220));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setBackground(new Color(169,169,169));
			}
		});
	}

}
