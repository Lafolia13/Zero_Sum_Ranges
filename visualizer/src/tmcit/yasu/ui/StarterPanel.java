package tmcit.yasu.ui;

import javax.swing.JPanel;

import tmcit.yasu.util.FileManager;

public class StarterPanel extends JPanel{
	private FileManager fileManager;

	private MapSelectPanel mapSelectPanel;
	private AgentSelectPanel myAgentSelectPanel, rivalAgentSelectPanel;
	private SettingPanel settingPanel;

	public StarterPanel(FileManager fileManager0) {
		fileManager = fileManager0;
		init();
		initLayout();
	}

	private void init() {
		mapSelectPanel = new MapSelectPanel(fileManager);
		myAgentSelectPanel = new AgentSelectPanel(true);
		rivalAgentSelectPanel = new AgentSelectPanel(false);
		settingPanel = new SettingPanel();
	}

	private void initLayout() {
		setLayout(null);

		mapSelectPanel.setBounds(10, 10, 400, 700);
		myAgentSelectPanel.setBounds(420, 10, 300, 345);
		rivalAgentSelectPanel.setBounds(420, 365, 300, 345);
		settingPanel.setBounds(730, 10, 300, 700);

		add(mapSelectPanel);
		add(myAgentSelectPanel);
		add(rivalAgentSelectPanel);
		add(settingPanel);
	}
}
