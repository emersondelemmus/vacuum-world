import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.util.HashMap;

import ai.worlds.WorldCreatePanel;
import ai.worlds.vacuum.*;
/**
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */


public class AI
{
	public static void main(String[] args)
	{
		HashMap<String, VacuumAgent> agentList = new HashMap<String, VacuumAgent>();
		agentList.put("Random Vacuum Agent", new RandomVacuumAgent());
		agentList.put("Your Own Vacuum Agent", new DummyVacuum());
		agentList.put("Test's Vacumm", new TestAgent());
		try {
			UIManager.setLookAndFeel("MetalLookAndFeel");
		} catch (Exception e) {}

		JFrame f = new JFrame("Artificial Intelligence");
		
		f.setSize(1054, 750);
		f.setResizable(false);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(new AIPanel(f, agentList) , BorderLayout.NORTH);
		f.setVisible(true); 
	}
}

class AIPanel extends JPanel {

	public static final Color buttonColor = MetalLookAndFeel.getTextHighlightColor();
	private JFrame holder;
	public JTabbedPane centerPanel = new JTabbedPane();
	private WorldCreatePanel wcp;
	public AIPanel(JFrame h, HashMap<String, VacuumAgent> agents) {
		holder = h;
		wcp = new WorldCreatePanel(holder, agents);
		JPanel aboutPanel = createAboutPanel();
		centerPanel.addTab("Agents and Environments", wcp);
		centerPanel.addTab("About", aboutPanel);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		holder.getContentPane().add(centerPanel, BorderLayout.CENTER);
	}
	private JPanel createAboutPanel() {
		JPanel aboutPanel = new JPanel(new BorderLayout());
		JTextPane aboutText = new JTextPane();
		SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setAlignment(set,StyleConstants.ALIGN_CENTER);
		aboutText.setParagraphAttributes(set,true);
		aboutText.setText("Artificial Intelligence Software Package\n Written by Jill Zimmerman in 2000");
		aboutText.setBackground(MetalLookAndFeel.getTextHighlightColor());
		aboutText.setEditable(false);
		aboutPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		aboutPanel.add(aboutText, BorderLayout.CENTER);
		return aboutPanel;
	}
}


