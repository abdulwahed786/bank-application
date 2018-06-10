/*----------------Bank-Application-System--------------------
        By - ABDUL WAHED and ABRAR ATHAR HASHMI       */
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
class Account
{
	int acc_no;
	String name,gender,adhar_no,branch,pswd;
	int bal;
/*	public Account(int acc_no)
	{
		this.acc_no=acc_no;
	}*/
}
class MyFrame extends Frame
{
	int acno=1;
	HashMap hm=new HashMap<Integer,Account>();
	public MyFrame(String n)
	{	
		super(n);
		setSize(400,600);
		try{    
				Class.forName("oracle.jdbc.driver.OracleDriver");    
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##scott","scott");    
				Statement stmt=con.createStatement();    
				ResultSet rs=stmt.executeQuery("select * from accounts"); 
				hm.clear();
				while (rs.next()){
					Account acc=new Account();
					acc.acc_no=rs.getInt(1);
					acc.name=rs.getString(2);
					acc.gender=rs.getString(3);
					acc.adhar_no=rs.getString(4);
					acc.branch=rs.getString(5);
					acc.bal=rs.getInt(6);
					acc.pswd=rs.getString(7);
					hm.put(acc.acc_no,acc);
					acno++;
				}
			/*	while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getInt(6));
				*/
				con.close();  
			}
			catch(Exception e){ System.out.println(e);}  
		HomePage p=new HomePage(this);
		p.setBackground(new Color(0,51,102));
		p.setPreferredSize(new Dimension(400,600));
		setVisible(true);
		add(p);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
	}
}
class HomePage extends Panel implements ActionListener					//	Panel_1: The HomePage
{
	MyFrame myFrame;
	public HomePage(MyFrame myFrame)
	{
		setLayout(null);
		setFont(new Font("TimesNewRoman",Font.BOLD,30));
		this.myFrame=myFrame;
		
		Label name=new Label("The Bank");
		name.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,40));
		name.setBounds(85,5,300,100);
		name.setForeground(Color.white);
		add(name);
	
		Button b1=new Button("Login");
		b1.setBounds(40,120,300,150);
		b1.addActionListener(this);
		add(b1);
		
		Button b2=new Button("Create an Account");
		b2.setBounds(40,290,300,150);
		b2.addActionListener(this);
		add(b2);
		
		Button b3=new Button("Admin");
		b3.setFont(new Font("TimesNewRoman",Font.BOLD,10));
		b3.setBounds(290,500,50,30);
		b3.addActionListener(this);
		add(b3);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String s=ae.getActionCommand();
		if(s.equals("Login"))
		{			
			myFrame.remove(this);
			Login l=new Login(myFrame);
			l.setBackground(new Color(0,51,102));
			l.setPreferredSize(new Dimension(400,600));
			myFrame.add(l);
			myFrame.validate();
		}
		else if(s.equals("Create an Account"))
		{
			myFrame.remove(this);
			Register p=new Register(myFrame);
			p.setBackground(new Color(0,51,102));
			p.setPreferredSize(new Dimension(400,600));
			myFrame.add(p);
			myFrame.validate();
		}
		else if(s.equals("Admin"))
		{
			myFrame.remove(this);
			AdminLogin p=new AdminLogin(myFrame);
			myFrame.add(p);
			myFrame.validate();
		}
	}
}
class AdminLogin extends Panel implements ActionListener
{
	MyFrame myFrame;
	TextField tf;
	AdminLogin(MyFrame myFrame)
	{
		this.myFrame=myFrame;
		setLayout(null);
		setBackground(Color.green);
		
		Label l=new Label("Administrator");
		l.setFont(new Font("TimesNewRoman",Font.BOLD,20));
		l.setBounds(120,20,150,40);
		add(l);
		
		Label l2=new Label("Password");
		l2.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,15));
		l2.setBounds(80,300,100,25);
		add(l2);
		
		tf=new TextField("");
		tf.setEchoChar('*');
		tf.setBounds(200,300,100,25);
		add(tf);
		
		Button b=new Button("Authenticate");
		b.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,12));
		b.setBounds(130,400,100,30);
		b.addActionListener(this);
		add(b);
		
		Button back=new Button("<-(Back)");
		back.setBounds(300,0,85,30);
		back.setBackground(Color.white);
		back.addActionListener(this);
		add(back);
	
	}
	class AdminPage extends Frame
	{
		AdminPage(String n)
		{
			super(n);
			
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent we)
				{
					dispose();
				}
			});
			
			setLayout(new GridLayout((myFrame.hm.size()+1),6));
			
			Label l1=new Label("Account Number",Label.CENTER);
			l1.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,15));
			l1.setForeground(Color.white);
			l1.setBackground(Color.blue);
			add(l1);
			
			Label l2=new Label("Name",Label.CENTER);
			l2.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,15));
			l2.setForeground(Color.white);
			l2.setBackground(Color.blue);
			add(l2);
			
			Label l3=new Label("Gender",Label.CENTER);
			l3.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,15));
			l3.setForeground(Color.white);
			l3.setBackground(Color.blue);
			add(l3);
			
			Label l4=new Label("Aadhar Number",Label.CENTER);
			l4.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,15));
			l4.setForeground(Color.white);
			l4.setBackground(Color.blue);
			add(l4);
			
			Label l5=new Label("Branch",Label.CENTER);
			l5.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,15));
			l5.setForeground(Color.white);
			l5.setBackground(Color.blue);
			add(l5);
			
			Label l6=new Label("Balance",Label.CENTER);
			l6.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,15));
			l6.setForeground(Color.white);
			l6.setBackground(Color.blue);
			add(l6);

			Collection<Account>c=myFrame.hm.values();
			setBackground(Color.yellow);
			for(Account a:c)
			{
					add(new Label(Integer.toString(a.acc_no),Label.CENTER));
					add(new Label(a.name,Label.CENTER));
					add(new Label(a.gender,Label.CENTER));
					add(new Label(a.adhar_no,Label.CENTER));
					add(new Label(a.branch,Label.CENTER));
					add(new Label(Double.toString(a.bal),Label.CENTER));					
			}
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Authenticate"))
		{
			if(tf.getText().equals("touseef"))   //PASSWORD ADMIN---------------------------------------
			{
				AdminPage p=new AdminPage("Welcome Admin :)");
				p.setSize(800,500);
				p.setVisible(true);
			}
			else
			{
				EmptyDialog ed=new EmptyDialog(myFrame,"Wrong Password! Try Again");
				ed.setSize(400,100);
				ed.setVisible(true);
			}
		}
		else
		{
			myFrame.remove(this);
			HomePage p=new HomePage(myFrame);
			p.setBackground(new Color(0,51,102));
			myFrame.add(p);
			myFrame.validate();
		}
	}
}
class Register extends Panel implements ActionListener					//	Panel_3: Register Page
{
	MyFrame myFrame;
	TextField name,adhr,init,branch,pass;
	Checkbox m;
		Register(MyFrame myFrame)
		{
			this.myFrame=myFrame;
			setLayout(null);
			
			Label lacc=new Label("Account Number : "+Integer.toString(myFrame.acno));
			lacc.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,15));
			lacc.setForeground(Color.white);
			lacc.setBounds(10,10,250,20);
			add(lacc);
			
			Label lname=new Label("Name");
			lname.setForeground(Color.white);
			lname.setFont(new Font("TimesNewRoman",Font.BOLD,15));
			lname.setBounds(30,100,100,20);
			add(lname);
			
			name=new TextField();
			name.setBounds(150,100,130,25);
			name.addActionListener(this);
			add(name);
			
			CheckboxGroup gender=new CheckboxGroup();
			Label lgender=new Label("Gender");
			lgender.setForeground(Color.white);
			lgender.setFont(new Font("TimesNewRoman",Font.BOLD,15));
			lgender.setBounds(30,150,100,20);
			add(lgender);
			
			m=new Checkbox("Male",gender,true);
			m.setForeground(Color.white);
			m.setBounds(150,170,50,20);
			add(m);
			
			Checkbox f=new Checkbox("Female",gender,false);
			f.setForeground(Color.white);
			f.setBounds(150,200,60,20);
			add(f);
			
			Label ladhr=new Label("Adhar No. ");
			ladhr.setForeground(Color.white);
			ladhr.setFont(new Font("TimesNewRoman",Font.BOLD,15));
			ladhr.setBounds(30,250,100,20);
			add(ladhr);
			
			adhr=new TextField();
			adhr.setBounds(150,250,130,25);
			adhr.addActionListener(this);
			add(adhr);
			
			Label linit=new Label("Initial Bal. ");
			linit.setForeground(Color.white);
			linit.setFont(new Font("TimesNewRoman",Font.BOLD,15));
			linit.setBounds(30,300,100,20);
			add(linit);
			
			init=new TextField();
			init.setBounds(150,300,130,25);
			init.addActionListener(this);
			add(init);
			
			Label lbranch=new Label("Branch ");
			lbranch.setForeground(Color.white);
			lbranch.setFont(new Font("TimesNewRoman",Font.BOLD,15));
			lbranch.setBounds(30,350,100,20);
			add(lbranch);
			
			branch=new TextField();
			branch.setBounds(150,350,130,25);
			branch.addActionListener(this);
			add(branch);
			
			Label lpass=new Label("Password ");
			lpass.setForeground(Color.white);
			lpass.setFont(new Font("TimesNewRoman",Font.BOLD,15));
			lpass.setBounds(30,400,100,20);
			add(lpass);
			
			pass=new TextField();
			pass.setBounds(150,400,130,25);
			pass.addActionListener(this);
			add(pass);
			
			Button b=new Button("Create Account!");
			b.setFont(new Font("TimesNewRoman",Font.BOLD,12));
			b.setBounds(125,455,100,25);
			b.addActionListener(this);
			add(b);
			
			Button back=new Button("<-(Back)");
			back.setBounds(300,0,85,30);
			back.setBackground(Color.white);
			back.addActionListener(this);
			add(back);
		}
		public void actionPerformed(ActionEvent ae)
		{
			Account nAcc=new Account();
			if(ae.getActionCommand().equals("<-(Back)"))
			{
				myFrame.remove(this);
				HomePage p=new HomePage(myFrame);
				p.setBackground(new Color(0,51,102));
				myFrame.add(p);
				myFrame.validate();
			}
			if((ae.getActionCommand()).equals("Create Account!"))
			{
				try{
				nAcc.name=name.getText();
				nAcc.adhar_no=adhr.getText();
				nAcc.bal=Integer.parseInt(init.getText());
				nAcc.branch=branch.getText();
				if(m.getState())
				{
					nAcc.gender="Male";
				}
				else
				{
					nAcc.gender="Female";
				}
				nAcc.pswd=pass.getText();
//				myFrame.hm.put(new Integer(myFrame.acno),nAcc);
				try{  
						Class.forName("oracle.jdbc.driver.OracleDriver");    
						Connection con=(Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##scott","scott");    
						Statement stmt=con.createStatement();
						stmt.executeUpdate("insert into accounts(acno,aname,gender,adharno,branch,balance,pass) values('"+myFrame.acno+"','"+nAcc.name+"','"+nAcc.gender+"','"+nAcc.adhar_no+"','"+nAcc.branch+"','"+nAcc.bal+"','"+nAcc.pswd+"')");
						con.close();  
					}
				catch(Exception e){ System.out.println(e);}
				myFrame.remove(this);
				AccountDialog ac=new AccountDialog(myFrame,"Attention!",myFrame.acno);
				ac.setSize(400,200);
				ac.setVisible(true);
				myFrame.acno++;
				
				Login p=new Login(myFrame);
				p.setBackground(new Color(0,51,102));
				myFrame.add(p);
				myFrame.validate();
				}
				catch(NullPointerException npe)
				{
					EmptyDialog ed=new EmptyDialog(myFrame,"NullPointerException");
					ed.setSize(400,100);
					ed.setVisible(true);
				}
				catch(NumberFormatException nfe)
				{
					EmptyDialog ed=new EmptyDialog(myFrame,"NumberFormatException");
					ed.setSize(400,100);
					ed.setVisible(true);
				}
			}
		}
}
class UserPage extends Panel implements ActionListener					//	Panel_4: User Page
{
	Account acc;
	MyFrame myFrame;
	Label lbal,sDep,sWith;
	Button bDep,bWith,logout;
	TextField dep_amount,with_amount;
	UserPage(Account acc,MyFrame myFrame)
	{
		this.acc=acc;
		setLayout(null);
		this.myFrame=myFrame;
		setForeground(Color.white);
		
		Label usrname=new Label("Welcome "+acc.name+"!");
		usrname.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,20));
		usrname.setBounds(20,25,400,20);
		add(usrname);
		
		lbal=new Label("$ "+Integer.toString(acc.bal));
		lbal.setFont(new Font("TimesNewRoman",Font.BOLD,15));
		lbal.setBounds(200,50,300,20);
		add(lbal);
		
		Label lblc=new Label("Current Balance");
		lblc.setFont(new Font("TimesNewRoman",Font.BOLD,15));
		lblc.setBounds(20,50,150,20);
		add(lblc);
		
		Label dep=new Label("Deposit :");
		dep.setFont(new Font("TimesNewRoman",Font.BOLD,15));
		dep.setBounds(20,80,200,20);
		add(dep);
		
		dep_amount=new TextField();
		dep_amount.setForeground(Color.black);
		dep_amount.setBounds(20,100,150,30);
		dep_amount.addActionListener(this);
		add(dep_amount);
		
		bDep=new Button("Deposit!");
		bDep.setForeground(Color.black);
		bDep.setFont(new Font("TimesNewRoman",Font.BOLD,15));
		bDep.addActionListener(this);
		bDep.setBounds(230,100,100,30);
		add(bDep);
		
		sDep=new Label("Status :");
		sDep.setBounds(20,145,135,25);
		add(sDep);
		
		sWith=new Label("Status : ");
		sWith.setBounds(20,320,300,25);
		add(sWith);
		
		Label lWith=new Label("Withdraw : ");
		lWith.setFont(new Font("TimesNewRoman",Font.BOLD,15));
		lWith.setBounds(20,200,200,20);
		add(lWith);
		
		with_amount=new TextField();
		with_amount.setForeground(Color.black);
		with_amount.setBounds(20,250,150,30);
		add(with_amount);
		
		bWith=new Button("Withdraw!");
		bWith.setFont(new Font("TimesNewRoman",Font.BOLD,15));
		bWith.setBounds(230,250,100,30);
		bWith.setForeground(Color.black);
		bWith.addActionListener(this);
		add(bWith);
		
		logout=new Button("Logout!");
		logout.setFont(new Font("TimesNewRoman",Font.BOLD,15));
		logout.setBounds(150,400,100,30);
		logout.setForeground(Color.black);
		logout.addActionListener(this);
		add(logout);
		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		try{
			if(ae.getActionCommand().equals("Deposit!"))
			{
				acc.bal+=Integer.parseInt(dep_amount.getText());
				lbal.setText("$ "+Double.toString(acc.bal));
				dep_amount.setText("");
				sDep.setText("Status : Success! :)");
				
			}
			else if(ae.getActionCommand().equals("Withdraw!"))
			{
				if(acc.bal-Integer.parseInt(with_amount.getText())<1000)
				{
					EmptyDialog ed=new EmptyDialog(myFrame,"Insufficient Funds! :(");
					ed.setSize(250,100);
					ed.setVisible(true);
					sWith.setText("Status : Please maintain a minimum Balance of $1000");
				}
				else
				{
					acc.bal-=Integer.parseInt(with_amount.getText());
					lbal.setText("$ "+Integer.toString(acc.bal));
					sWith.setText("Status : Success! :)");
				}
				with_amount.setText("");
			}
			else if(ae.getActionCommand().equals("Logout!"))
			{
				ConfirmDialog cd=new ConfirmDialog(myFrame,"Logout?");
				cd.setSize(400,190);
				cd.setVisible(true);
				if(cd.yes)
				{
					try{    
				Class.forName("oracle.jdbc.driver.OracleDriver");    
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##scott","scott");    
				Statement stmt=con.createStatement();    
				stmt.executeUpdate("update accounts set balance='"+acc.bal+"' where acno='"+acc.acc_no+"'"); 
				con.close();  
			}
			catch(Exception e){ System.out.println(e);}  
					myFrame.remove(this);
					HomePage p=new HomePage(myFrame);
					p.setBackground(new Color(0,51,102));
					myFrame.add(p);
					myFrame.validate();
				}
			}
		}
		catch(NumberFormatException nfe)
		{
			EmptyDialog ed=new EmptyDialog(myFrame,"NumberFormatException");
			ed.setSize(400,100);
			ed.setVisible(true);
			
			with_amount.setText("");
			dep_amount.setText("");
			
		}
		catch(NullPointerException npe)
		{
			EmptyDialog ed=new EmptyDialog(myFrame,"NullPointerException");
			ed.setSize(400,100);
			ed.setVisible(true);
			
			dep_amount.setText("");
			with_amount.setText("");
		}
	}
}
class Login extends Panel implements ActionListener						//	Panel_2: Login Page
{	
	MyFrame myFrame;
	TextField tf1;
	TextField tf2;
	Login(MyFrame myFrame)
	{
		this.myFrame=myFrame;
		setLayout(null);
		
		Label name=new Label("Login");
		name.setFont(new Font("TimesNewRoman",Font.BOLD|Font.ITALIC,50));
		name.setForeground(Color.white);
		name.setBounds(100,60,150,100);
		add(name);
		
		Label l1=new Label("Account Number : ",Label.LEFT);	
		l1.setForeground(Color.white);
		l1.setFont(new Font("TimesNewRoman",Font.BOLD,15));	
		l1.setBounds(50,200,130,20);
		add(l1);
		
		Label l2=new Label("Password : ",Label.LEFT);
		l2.setForeground(Color.white);
		l2.setFont(new Font("TimesNewRoman",Font.BOLD,15));	
		l2.setBounds(50,250,100,20);
		add(l2);
		
		tf1=new TextField(15);
		tf1.setBounds(200,200,130,25);
		tf1.addActionListener(this);
		add(tf1);
		
		tf2=new TextField(15);
		tf2.setBounds(200,250,130,25);
		tf2.setEchoChar('*');
		tf2.addActionListener(this);
		add(tf2);
		
		Button b=new Button("Login");
		b.setFont(new Font("TimesNewRoman",Font.BOLD,12));	
		b.setBounds(150,300,50,25);
		b.addActionListener(this);
		add(b);
		
		Button back=new Button("<-(Back)");
		back.setBounds(300,0,85,30);
		back.setBackground(Color.white);
		back.addActionListener(this);
		add(back);	
	}
	public void actionPerformed(ActionEvent ae)
	{	
		if(ae.getActionCommand().equals("<-(Back)"))
		{
			myFrame.remove(this);
			HomePage p=new HomePage(myFrame);
			p.setBackground(new Color(0,51,102));
			myFrame.add(p);
			myFrame.validate();
		}
		else
		{
			try
			{
				int uAcNo=Integer.parseInt(tf1.getText());
			
			String uPass=(String)tf2.getText();
			if(myFrame.hm.containsKey(new Integer(uAcNo)))
			{
				Account uAcc;
				if((((Account)(myFrame.hm.get(new Integer(uAcNo)))).pswd).equals(uPass))
				{
					myFrame.remove(this);
					uAcc=(Account)myFrame.hm.get(uAcNo);
					UserPage p=new UserPage(uAcc,myFrame);
					p.setBackground(new Color(0,51,102));
					p.setPreferredSize(new Dimension(400,600));
					myFrame.add(p);
					myFrame.validate();
				}
				else 
				{
					EmptyDialog ed=new EmptyDialog(myFrame,"Wrong Password! Try Again");
					ed.setSize(400,100);
					ed.setVisible(true);
					
					tf2.setText("");
				}
			}
			else
			{
				EmptyDialog ed=new EmptyDialog(myFrame,"Enter valid Account Number!");
				ed.setSize(400,100);
				ed.setVisible(true);
			}
			}
			catch(NullPointerException npe)
			{
				EmptyDialog ed=new EmptyDialog(myFrame,"NullPointerException");
				ed.setSize(400,100);
				ed.setVisible(true);	
			}
			catch(NumberFormatException nfe)
			{
				EmptyDialog ed=new EmptyDialog(myFrame,"NumberFormatException");
				ed.setSize(400,100);
				ed.setVisible(true);
			}
		}
	}
}
class AccountDialog extends Dialog implements ActionListener			// This Dialog gives the information about the new Account number
{
	AccountDialog(Frame f,String s,int ac)
	{
		super(f,s,true);
		setLayout(new GridLayout(2,1));
		Label l=new Label("Your Account number is "+Integer.toString(ac)+"!");
		l.setBackground(new Color(4,188,255));
		add(l);
		Button b=new Button("Okay");
		b.setBounds(0,100,300,100);
		b.setBackground(new Color(4,188,255));
		b.addActionListener(this);
		add(b);
	}
	public void actionPerformed(ActionEvent ae)
	{
		dispose();
	}
}
class ConfirmDialog extends Dialog implements ActionListener			// This Dialog forces the user to confirm action
{
	boolean yes=false;
	ConfirmDialog(Frame f,String s)
	{
		super(f,s,true);
		setLayout(new GridLayout(2,1));
		setFont(new Font("TimesNewRoman",Font.BOLD,20));
		
		Button b1=new Button("Confirm");
		b1.setBackground(new Color(4,188,255));
		b1.addActionListener(this);
		add(b1);
		
		Button b2=new Button("Cancel");
		b2.setBackground(new Color(4,188,255));
		b2.addActionListener(this);
		add(b2);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Confirm"))
		{
			dispose();
			yes=true;
		}
		else
		{
			dispose();
		}
	}
}
class EmptyDialog extends Dialog implements ActionListener				// This Dialog gives all Exception details
{
	EmptyDialog(Frame f,String s)
	{
		super(f,s,true);
		setLayout(new GridLayout(1,1));
		
		Button b=new Button("OK");
		b.setFont(new Font("TimesNewRoman",Font.BOLD,12));	
		b.addActionListener(this);
		b.setBackground(new Color(4,188,255));
		add(b);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		dispose();
	}
}
class BankdbApp															// 	The Main class
{
	public static void main(String a[])
	{ 
		new MyFrame("The Bank App");
	}
}