package ai.worlds; 

/*  The Agents and Environment module panel.  This module allows 
    the creation of both vacuum and wumpus agents.  The worlds can
    be built and viewed or several agents within a world can be
    compared 
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.util.HashMap;
import ai.worlds.vacuum.*;

/**
 * A Panel containing the information for world creation.
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

@SuppressWarnings("serial")
public class WorldCreatePanel extends JPanel implements ActionListener, ItemListener {
	private static final Color metalColor = MetalLookAndFeel.getTextHighlightColor(); 
	
    private JComboBox envs;
    private JComboBox agents;
    private JTextField xsize;
    private JTextField ysize;
    private JList trialAgents;
    JTextField numTrials;
    private JCheckBox randomSizes;
    
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel northCenterPanel = new JPanel();
    
    private JScrollPane scroll;
    private JTextArea results;
    private JLabel title;
    private JPanel p = new JPanel();
    
    public JButton load = new JButton();
    public JButton rebuild = new JButton();
    public JButton buildWorld = new JButton();
    public JButton simWorld = new JButton();
    
    private boolean isLoaded = false;
    
    /**
     * The list of worlds.
     */
    String[] worldStrings = {"Vacuum World"};
    /**
     * The list of vacuum agents.
     */
    String[] vacuumStrings;
    /**
     * The list of wumpus agents.
     */
    String[] wumpusStrings = null;
    
    private GridBagLayout gridbag;
    private GridBagConstraints constraints;
    
    //private WumpusWorldEditor wwe = new WumpusWorldEditor();
    
    private JFrame holder;
    private HashMap<String, VacuumAgent> agentMap;
    
    public WorldCreatePanel(JFrame f, HashMap<String, VacuumAgent> agts)
    {
    	int numStrings = agts.keySet().size();
    	vacuumStrings = new String[numStrings];
    	agentMap = agts;
    	for(int i = 0; i< agentMap.keySet().toArray().length; i++){
    		vacuumStrings[i] = (String) (agentMap.keySet().toArray()[i]);
    	}
    	holder = f;
    	setLayout(new BorderLayout());
    	//setup NorthPanel
    	envs = new JComboBox(worldStrings);
    	envs.addItemListener(this);
    	agents = new JComboBox(vacuumStrings);
    	xsize = new JTextField("8",2);
    	ysize = new JTextField("8",2);
    	
    	
    	rebuild = new JButton("Rebuild Loaded World");
    	rebuild.setActionCommand("Rebuild World");
    	rebuild.addActionListener(this);
    	rebuild.setEnabled(false);
    	
    	simWorld.setText("Simulate an Agent in an Environment");
    	simWorld.addActionListener(this);
    
    	gridbag = new GridBagLayout();
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
    	
	    gridbag = new GridBagLayout();
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
		northPanel.setBackground(metalColor);
		northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		northPanel.setLayout(new BorderLayout());
		
		JPanel northNorthPanel = new JPanel(gridbag);
		
		constraints.gridwidth = 2;
		constraints.insets = new Insets(4, 35, 4, 35);
    	gridbag.setConstraints(buildWorld, constraints);
    	northNorthPanel.add(buildWorld);
    	constraints.gridwidth = GridBagConstraints.REMAINDER;
    	gridbag.setConstraints(simWorld, constraints);
    	northNorthPanel.add(simWorld);
    	constraints.gridwidth = 1;
    	constraints.insets = new Insets(8, 4, 8, 4);
		northNorthPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		northPanel.add(northNorthPanel, BorderLayout.NORTH);
		
		northCenterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		northCenterPanel.setPreferredSize(new Dimension(750, 75));
		northPanel.add(northCenterPanel, BorderLayout.CENTER);
    	
	    add(northPanel, BorderLayout.NORTH);
		trialAgents = new JList(vacuumStrings);
		JScrollPane scrollPane = new JScrollPane(trialAgents);
		scrollPane.setPreferredSize(new Dimension(175,95));
		scrollPane.setBackground(metalColor);
		numTrials = new JTextField("10",2);
		randomSizes = new JCheckBox("",false);
		randomSizes.setBackground(metalColor);
	    
		
		gridbag = new GridBagLayout();
	    constraints = new GridBagConstraints();
	    constraints.insets = new Insets(2,8,2,8);
	    constraints.gridwidth = 1;
		JPanel numTrialsPanel = new JPanel(gridbag);
		JLabel l = new JLabel("Random Sizes");
		gridbag.setConstraints(l, constraints);
		numTrialsPanel.add(l);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(randomSizes, constraints);
		numTrialsPanel.add(randomSizes);
		constraints.gridwidth = 1;
		JLabel label = new JLabel("Number of Trials");
		gridbag.setConstraints(label, constraints);
		numTrialsPanel.add(label);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(numTrials, constraints);
		numTrialsPanel.add(numTrials);
		numTrialsPanel.setBackground(metalColor);
	    
		p.setBackground(metalColor);
		p.setLayout(new BorderLayout());
		results = new JTextArea();
		results.setEditable(false);
		scroll = new JScrollPane(results);
		scroll.setPreferredSize(new Dimension(250, 75));
		title = new JLabel("Scores:");
		title.setFont(new Font("SansSerif",Font.ITALIC + Font.BOLD,14));
		p.add("North",title);
		p.add("Center",scroll);
		
		gridbag = new GridBagLayout();
	    constraints = new GridBagConstraints();
	    constraints.insets = new Insets(8,8,8,8);
		southPanel.setBackground(metalColor);
		southPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		southPanel.setLayout(gridbag);
		
		gridbag.setConstraints(numTrialsPanel, constraints);
		gridbag.setConstraints(p, constraints);
		centerPanel.setPreferredSize(new Dimension(750, 400));
		centerPanel.setBackground(Color.white);
		centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		add(centerPanel, BorderLayout.CENTER);
		load.setEnabled(false);
    }
	
    public void actionPerformed(ActionEvent e) {
    	String action = e.getActionCommand();
		if (action.equals("Build Random")) build();
		else if(action.equals("Rebuild World")) rebuild();
		else if(action.equals("Simulate an Agent in an Environment")) simulate();
    }
    
    public void simulate() {
    	buildWorld.setBackground(Color.gray.brighter());
    	simWorld.setBackground(metalColor);
    	northPanel.remove(northCenterPanel);
    	gridbag = new GridBagLayout();
    	constraints = new GridBagConstraints();
    	
    	JButton build = new JButton("Build Random");
    	build.addActionListener(this);
    	
		JPanel choicePanel = new JPanel(gridbag);
    	JLabel label1 = new JLabel(" Environment: ");
    	JLabel label2 = new JLabel(" Agent: ");
    	gridbag.setConstraints(label1, constraints);
    	choicePanel.add(label1);
    	constraints.gridwidth = GridBagConstraints.REMAINDER;
    	gridbag.setConstraints(envs, constraints);
    	choicePanel.add(envs);
    	constraints.gridwidth = 1;
    	gridbag.setConstraints(label2, constraints);
    	choicePanel.add(label2);
    	gridbag.setConstraints(agents, constraints);
    	choicePanel.add(agents);
    	choicePanel.setBackground(metalColor);
    	gridbag = new GridBagLayout();
	    constraints = new GridBagConstraints();
	    constraints.insets = new Insets(0,2,0,2);
	    constraints.fill = GridBagConstraints.BOTH;
		JPanel sizePanel = new JPanel(gridbag);
		JLabel xlabel = new JLabel(" x size: ");
		JLabel ylabel = new JLabel(" y size: ");
		gridbag.setConstraints(xlabel, constraints);
		sizePanel.add(xlabel);
		gridbag.setConstraints(xsize, constraints);
		sizePanel.add(xsize);
		gridbag.setConstraints(build, constraints);
		sizePanel.add(build);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(rebuild, constraints);
		sizePanel.add(rebuild);		
		constraints.gridwidth = 1;
		gridbag.setConstraints(ylabel, constraints);
		sizePanel.add(ylabel);
		gridbag.setConstraints(ysize, constraints);
		sizePanel.add(ysize);
		gridbag.setConstraints(load, constraints);		
		sizePanel.add(load);
		sizePanel.setBackground(metalColor); 
		JLabel buildTitle = new JLabel("Create an Environment and Agent",0);
		buildTitle.setFont(new Font("SansSerif", Font.ITALIC+Font.BOLD, 14));
		
		gridbag = new GridBagLayout();
		constraints = new GridBagConstraints();
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(2,2,2,2);
		northCenterPanel = new JPanel(gridbag);
    	northCenterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		northCenterPanel.setPreferredSize(new Dimension(750, 75));
		northCenterPanel.setBackground(metalColor);
		gridbag.setConstraints(buildTitle, constraints);
		northCenterPanel.add(buildTitle);
		gridbag.setConstraints(choicePanel, constraints);
		northCenterPanel.add(choicePanel);
		gridbag.setConstraints(sizePanel, constraints);
		northCenterPanel.add(sizePanel);
		northPanel.add(northCenterPanel, BorderLayout.CENTER);
		northPanel.setVisible(false);
		northPanel.setVisible(true);
		
		remove(centerPanel);
    	centerPanel = new JPanel();
    	centerPanel.setPreferredSize(new Dimension(750, 400));
    	centerPanel.setBackground(Color.white);
    	centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
    	centerPanel.setLayout(new BorderLayout());
    	add(centerPanel);
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
    }
	
    public void itemStateChanged(ItemEvent e) {
    	String world = (String)envs.getSelectedItem();
    	if (world.equals("Vacuum World")) {
    		changeAgentChoices(vacuumStrings);
    		xsize.setText("8");
    		ysize.setText("8");
    		load.setEnabled(false);
    		rebuild.setEnabled(false);
    		randomSizes.setSelected(true);
    	}
    }
    
    private void build() {
    	Agent[] a = new Agent[1];
    	a[0] = createAgent((String)agents.getSelectedItem());
    	GridEnvironment world=null;
    	if (envs.getSelectedItem() ==  "Vacuum World") {
    		int x = Integer.parseInt(xsize.getText());
        	int y = Integer.parseInt(ysize.getText());
    		world = new VacuumWorld(a, x, y, .25, holder);
    		holder.setTitle("Artificial Intelligence - Agents and Environments - Vacuum World");
    	}
    	else {
    	}
    	remove(centerPanel);
    	centerPanel = new JPanel();
    	centerPanel.setPreferredSize(new Dimension(750, 400));
		centerPanel.setBackground(Color.white);
		centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
    	centerPanel.setLayout(new BorderLayout());
    	JPanel center2 = new JPanel(new BorderLayout());
    	center2.add(world.canvas, BorderLayout.CENTER);
		centerPanel.add(center2, BorderLayout.CENTER);
		centerPanel.add("North",world.gridPanel);
		add(centerPanel);
		setVisible(false);
		setVisible(true);
    }
    
   
    
    private void rebuild() {
    	Agent[] a = new Agent[1];
    	a[0] = createAgent((String)agents.getSelectedItem());
    	//GridEnvironment world = new WumpusWorld(a, x, y, holder, filepath);
    	remove(centerPanel);
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(750, 400));
		centerPanel.setBackground(Color.white);
		centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		centerPanel.setLayout(new BorderLayout());
		JPanel center2 = new JPanel(new BorderLayout());
		centerPanel.add(center2, BorderLayout.CENTER);
		add(centerPanel);
		setVisible(false);
		setVisible(true);
    }
    
    private void createWorld() {
    	simWorld.setBackground(Color.gray.brighter());
    	buildWorld.setBackground(metalColor);
    	JButton saveButton = new JButton("Click here to save your world.");
    	saveButton.setActionCommand("save");
    	saveButton.addActionListener(this);
    	northPanel.remove(northCenterPanel);
    	gridbag = new GridBagLayout();
    	northCenterPanel = new JPanel(gridbag);
    	northCenterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		northCenterPanel.setPreferredSize(new Dimension(750, 75));
		northCenterPanel.add(saveButton);
		northPanel.add(northCenterPanel, BorderLayout.CENTER);
    	remove(centerPanel);
    	centerPanel = new JPanel();
    	centerPanel.setPreferredSize(new Dimension(750, 400));
    	centerPanel.setBackground(Color.white);
    	centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
    	centerPanel.setLayout(new BorderLayout());
    	//centerPanel.add(wwe, BorderLayout.CENTER);
    	centerPanel.add(new JLabel("Directions: Right-click in a cell you'd like to add something to.  Check what you'd like to add.  When you are satisfied with your world, save it."), BorderLayout.NORTH);
    	add(centerPanel);
    	centerPanel.setVisible(false);
    	centerPanel.setVisible(true);
    }
        
    private void changeAgentChoices(String[] agentName)
    // changes the agents listed in the agents and trialAgents
    // lists to the Strings given in the array agentName
    {
	agents.removeAllItems();
	trialAgents.removeAll();
	trialAgents.setListData(agentName);
	for (int i=0; i<agentName.length; i++)
	    agents.addItem(agentName[i]);
	
    }
    
    private Agent createAgent(String agentName) {
    	return agentMap.get(agentName);
    }
    
}
