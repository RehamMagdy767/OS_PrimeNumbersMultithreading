import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

	public class MyFrame extends JFrame implements ActionListener{

		JPanel upper;
		JPanel lower;
		JButton button;
		JTextField numberField;
		JTextField bufferField;
		JTextField fileField;
		JLabel numberLabel;
		JLabel bufferLabel;
		JLabel fileLabel;
		JLabel lastNoLabel;
		JLabel cntLabel;
		JLabel timeLabel;
		JLabel lastNoField;
		JLabel cntField;
		JLabel timeField;
		int number,buffer;
		
		MyFrame()
		{
			
			Border border = BorderFactory.createLineBorder(Color.black,3);
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Threads Prime Numbers"); 
			
			upper= new JPanel();
			upper.setBounds(10,10,490,230);
			upper.setBackground(Color.GRAY);
			upper.setLayout(null);
			
			lower= new JPanel();
			lower.setBounds(10, 290, 490, 170);
			lower.setBackground(Color.GRAY);
			lower.setLayout(null);
			
			numberLabel = new JLabel();
			numberLabel.setText("N");
			numberLabel.setBounds(5, 4,225,45);
			numberLabel.setBackground(Color.blue);
			numberLabel.setBorder(border);
			numberLabel.setHorizontalAlignment(JLabel.CENTER);
			
			
			bufferLabel = new JLabel();
			bufferLabel.setText("buffer size");
			bufferLabel.setBounds(5, 53,225,45);
			bufferLabel.setBorder(border);
			bufferLabel.setHorizontalAlignment(JLabel.CENTER);
			
			fileLabel = new JLabel();
			fileLabel.setText("output file");
			fileLabel.setBounds(5, 102,225,45);
			fileLabel.setBorder(border);
			fileLabel.setHorizontalAlignment(JLabel.CENTER);
			
			button = new JButton();
			button.setBounds(5, 160, 150, 45);
			button.addActionListener(this);
			button.setText("start producer");
			
			numberField = new JTextField();
			numberField.setBounds(240,9,240,35);
			numberField.setFont(new Font("Consolas",Font.PLAIN,20));
			numberField.setBackground(Color.white);
			numberField.setCaretColor(Color.black);
			
			bufferField = new JTextField();
			bufferField.setBounds(240,58,240,35);
			bufferField.setFont(new Font("Consolas",Font.PLAIN,20));
			bufferField.setBackground(Color.white);
			bufferField.setCaretColor(Color.black);
			
			fileField = new JTextField();
			fileField.setBounds(240,107,240,35);
			fileField.setFont(new Font("Consolas",Font.PLAIN,20));
			fileField.setBackground(Color.white);
			fileField.setCaretColor(Color.black);
			
			upper.add(numberLabel);
			upper.add(bufferLabel);
			upper.add(fileLabel);
			upper.add(button);
			upper.add(numberField);
			upper.add(bufferField);
			upper.add(fileField);
			
			
			lastNoLabel = new JLabel();
			lastNoLabel.setText("the largest prime number");
			lastNoLabel.setBounds(5, 4,225,45);
			lastNoLabel.setBorder(border);
			lastNoLabel.setHorizontalAlignment(JLabel.CENTER);
			
			
			cntLabel = new JLabel();
			cntLabel.setText("number of elements generated");
			cntLabel.setBounds(5, 53,225,45);
			cntLabel.setBorder(border);
			cntLabel.setHorizontalAlignment(JLabel.CENTER);
			
			
			timeLabel = new JLabel();
			timeLabel.setText("time elapsed since start of process");
			timeLabel.setBounds(5, 102,225,45);
			timeLabel.setBorder(border);
			timeLabel.setHorizontalAlignment(JLabel.CENTER);
			
			lastNoField = new JLabel();
			lastNoField.setBounds(240, 9,225,45);
			lastNoField.setBorder(border);
			lastNoField.setHorizontalAlignment(JLabel.CENTER);
			
			cntField = new JLabel();
			cntField.setBounds(240, 58,225,45);
			cntField.setBorder(border);
			cntField.setHorizontalAlignment(JLabel.CENTER);
			
			
			timeField = new JLabel();
			timeField.setBounds(240, 107,225,45);
			timeField.setBorder(border);
			timeField.setHorizontalAlignment(JLabel.CENTER);
			
			lower.add(lastNoLabel);
			lower.add(cntLabel);
			lower.add(timeLabel);
			lower.add(lastNoField);
			lower.add(cntField);
			lower.add(timeField);
			
			
			this.setLayout(null);
			this.setSize(530,520);
			this.getContentPane().setBackground(Color.DARK_GRAY);
			this.add(upper);
			this.add(lower);
			this.setResizable(false);
			this.setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource()==button) 
			{
				numberField.setEditable(false);
				bufferField.setEditable(false);
				fileField.setEditable(false);
				
				number = Integer.valueOf(numberField.getText());
				buffer = Integer.valueOf(bufferField.getText());
				
				BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(buffer);

			    Producer p = new Producer(queue,number,buffer);
			    Consumer c = new Consumer(queue,number,buffer,fileField.getText());
			    Thread producer = new Thread(p);
			    Thread consumer = new Thread(c);
			    
			    producer.start();
			    consumer.start();
			 
			    while(true)
			    {
			    	if(!producer.isAlive())
			    	{
			    		lastNoField.setText(Integer.toString(p.last));
			    		cntField.setText(Integer.toString(p.cnt));
			    		timeField.setText(Long.toString(c.finish - p.start));
			    		break;
			    	}
			    }
			    
			}
			
		}

	}




