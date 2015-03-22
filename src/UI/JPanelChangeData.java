package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Data.JSONReader;
import Main.Constants;
import Main.Constants.LINE_COLOR_SELECTOR;

/**JPanel in which the user chooses the source of the data.
 * 
 *
 */
@SuppressWarnings("serial")
public class JPanelChangeData extends JPanel {

	//UI Components
	private ButtonGroup m_radioButtonsGroup;
	private JRadioButton m_rb_UseLive;
	private JRadioButton m_rb_UseFiles;
	private JPanel m_panel_fileSelection;
	private JPanelLineColorSelector m_color_selector;
	private JTextPane m_fileStatus;
	private JFileChooser m_fileChooser;
	
	private JButton m_b_refreshLive;

	//State Variables
	private LINE_COLOR_SELECTOR m_selectedColor;

	/**Sets up RadioButtons to allow user to select either Live Data or Local Files;
	 * Sets up Buttons and action listeners to allow for users to select Local Files to use as 
	 * data for the Blue, Orange, and Red lines.
	 */
	public JPanelChangeData(){
		super();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		m_fileChooser = new JFileChooser();
		m_fileChooser.setFileFilter(new FileNameExtensionFilter("JSON files (*.json)", "json"));

		ActionListener action_red = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(m_selectedColor != LINE_COLOR_SELECTOR.RED) {
						m_fileChooser.setSelectedFile(null);
					}
					m_selectedColor = LINE_COLOR_SELECTOR.RED;
					if(Constants.FILE_RED != null){
						m_fileChooser.setSelectedFile(Constants.FILE_RED);
					}
					if(m_fileChooser.showOpenDialog(m_color_selector) == JFileChooser.APPROVE_OPTION){
						Constants.FILE_RED = m_fileChooser.getSelectedFile();
						JSONReader.loadData(Constants.DATA_SOURCE_SELECTOR.FILES, Constants.LINE_COLOR_SELECTOR.RED);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(m_rb_UseFiles.getParent(), "Error Loading from File: " + ex.getMessage());
				} finally {
					displayFileSource();
				}
			}
		};

		ActionListener action_blue = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(m_selectedColor != LINE_COLOR_SELECTOR.BLUE) {
						m_fileChooser.setSelectedFile(null);
					}
					m_selectedColor = LINE_COLOR_SELECTOR.BLUE;
					if(Constants.FILE_BLUE != null){
						m_fileChooser.setSelectedFile(Constants.FILE_BLUE);
					}
					if(m_fileChooser.showOpenDialog(m_color_selector) == JFileChooser.APPROVE_OPTION){
						Constants.FILE_BLUE = m_fileChooser.getSelectedFile();
						JSONReader.loadData(Constants.DATA_SOURCE_SELECTOR.FILES, Constants.LINE_COLOR_SELECTOR.BLUE);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(m_rb_UseFiles.getParent(), "Error Loading from File: " + ex.getMessage());
				} finally {
					displayFileSource();
				}
			}
		};

		ActionListener action_orange = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(m_selectedColor != LINE_COLOR_SELECTOR.ORANGE) {
						m_fileChooser.setSelectedFile(null);
					}
					m_selectedColor = LINE_COLOR_SELECTOR.ORANGE;
					if(Constants.FILE_ORANGE != null){
						m_fileChooser.setSelectedFile(Constants.FILE_ORANGE);
					}
					if(m_fileChooser.showOpenDialog(m_color_selector) == JFileChooser.APPROVE_OPTION){
						Constants.FILE_ORANGE = m_fileChooser.getSelectedFile();
						JSONReader.loadData(Constants.DATA_SOURCE_SELECTOR.FILES, Constants.LINE_COLOR_SELECTOR.ORANGE);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(m_rb_UseFiles.getParent(), "Error Loading from File: " + ex.getMessage());
				} finally {
					displayFileSource();
				}
			}
		};

		m_color_selector = new JPanelLineColorSelector(action_red, action_blue, action_orange);

		m_radioButtonsGroup = new ButtonGroup();
		m_rb_UseLive = new JRadioButton(Constants.RADIOBUTTON_USE_LIVE);
		m_rb_UseFiles = new JRadioButton(Constants.RADIOBUTTON_USE_FILES);

		m_rb_UseLive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JSONReader.loadData(Constants.DATA_SOURCE_SELECTOR.LIVE, Constants.LINE_COLOR_SELECTOR.ALL);
				} catch (Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(m_rb_UseLive.getParent(), "Error loading Live Data: " + ex.getMessage());
				}
				m_panel_fileSelection.setVisible(false);
				m_b_refreshLive.setVisible(true);
			}
		});

		m_rb_UseFiles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JSONReader.loadData(Constants.DATA_SOURCE_SELECTOR.FILES, Constants.LINE_COLOR_SELECTOR.ALL);
				} catch (Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(m_rb_UseFiles.getParent(), "Please fix the files: " + ex.getMessage());
				} finally {
					displayFileSource();
				}
				m_panel_fileSelection.setVisible(true);
				m_b_refreshLive.setVisible(false);
			}
		});

		m_radioButtonsGroup.add(m_rb_UseLive);
		m_radioButtonsGroup.add(m_rb_UseFiles);

		m_panel_fileSelection = new JPanel();
		m_panel_fileSelection.setLayout(new BoxLayout(m_panel_fileSelection, BoxLayout.X_AXIS));
		m_fileStatus = new JTextPane();
		m_panel_fileSelection.add(m_color_selector);
		//m_panel_fileSelection.add(Box.createHorizontalGlue());
		m_panel_fileSelection.add(new JScrollPane(m_fileStatus));

		m_b_refreshLive = new JButton(Constants.BUTTON_REFRESH_DATA);
		m_b_refreshLive.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(m_rb_UseLive.isSelected()){
						JSONReader.loadData(Constants.DATA_SOURCE_SELECTOR.LIVE, Constants.LINE_COLOR_SELECTOR.ALL);
					}
				} catch (Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(m_rb_UseLive.getParent(), "Error loading Live Data: " + ex.getMessage());
				}
			}
		});
		
		this.add(m_rb_UseLive);
		this.add(m_b_refreshLive);
		this.add(m_rb_UseFiles);
		this.add(Box.createVerticalGlue());
		this.add(m_panel_fileSelection);
	
		//Load the Data for the first time.
		try {
			switch(Constants.DATA_SOURCE){
			case FILES:
				m_rb_UseLive.setSelected(false);
				m_rb_UseFiles.setSelected(true);
				m_panel_fileSelection.setVisible(true);
				JSONReader.loadData(Constants.DATA_SOURCE_SELECTOR.FILES, Constants.LINE_COLOR_SELECTOR.ALL);
				displayFileSource();
				break;
			case LIVE:
			default:
				m_rb_UseLive.setSelected(true);
				m_rb_UseFiles.setSelected(false);
				m_panel_fileSelection.setVisible(false);
				JSONReader.loadData(Constants.DATA_SOURCE_SELECTOR.LIVE, Constants.LINE_COLOR_SELECTOR.ALL);
				break;
			}
		} catch (Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error loading Data: " + ex.getMessage());
		}
	}

	private void displayFileSource(){
		StringBuilder sb = new StringBuilder();
		sb.append("Red Line: ");
		sb.append(Constants.FILE_RED == null ? "NULL" : Constants.FILE_RED.getPath());
		sb.append("\nBlue Line: ");
		sb.append(Constants.FILE_BLUE == null ? "NULL" : Constants.FILE_BLUE.getPath());
		sb.append("\nOrange Line: ");
		sb.append(Constants.FILE_ORANGE == null ? "NULL" : Constants.FILE_ORANGE.getPath());
		m_fileStatus.setText(sb.toString());
	}
}
