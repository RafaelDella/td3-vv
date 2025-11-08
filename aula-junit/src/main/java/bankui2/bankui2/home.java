package bankui2.bankui2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
public class home extends JFrame implements ActionListener {
	
	JButton b1,b2,b3,b4,b5,b6,b7,b8;
	home(){
	Container c=getContentPane();
	c.setLayout(new GridLayout(3,2));
	 b1=new JButton("open new account"); // buttons b 1,2,3,4,5,6
	 b1.addActionListener(this);
	 b2=new JButton("withdraw");
	 b2.addActionListener(this);
     b3=new JButton("deposite");
     b3.addActionListener(this);
     b4=new JButton("balance check");
     b4.addActionListener(this);
     b5=new JButton("close account");
     b5.addActionListener(this);
     b6=new JButton("EXIT");
     b6.addActionListener(this);
    
     
     b1.setBackground(Color.orange);
     b2.setBackground(Color.yellow);
     b3.setBackground(Color.yellow);
     b4.setBackground(Color.orange);
     b5.setBackground(Color.orange);
     b6.setBackground(Color.yellow);
     
     c.add(b1);c.add(b2);c.add(b3);c.add(b4);c.add(b5);c.add(b6);
	 }
	
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getSource()==b1){
			createaccount a=new createaccount ();
		a.setSize(500,600);
		a.setTitle(" open new account");
		a.setResizable(false); 
		a.setVisible(true);
		}
		if(ae.getSource()==b3){
			deposit h=new deposit();
				h.setSize(500,500);
				h.setTitle("deposite");
				h.setResizable(false); 
				h.setVisible(true);
			
		}
		if(ae.getSource()==b2){
			 withdraw w =new  withdraw();
					w.setSize(500,500);
					w.setTitle("withdraw");
					w.setResizable(false); 
					w.setVisible(true);
		}
		if(ae.getSource()==b5){
		closeaccount caa =new closeaccount ();
		caa.setSize(500,500);
		caa.setTitle(" close account ");
		caa.setResizable(false); 
		caa.setVisible(true);	
			
	}
		if(ae.getSource()==b4){
			searchbyaccountnumber ca =new searchbyaccountnumber ();
			ca.setSize(500,500);
			ca.setTitle("balance check");
			ca.setResizable(false); 
			ca.setVisible(true);	
		
		}
		if(ae.getSource()==b6){
			System.exit(0);
		}
		
}

	public static void main(String[] args) {
		
		home f=new home();
		f.setSize(500,500);
		f.setTitle("Bank Management System");
		f.setVisible(true);
	}

}
