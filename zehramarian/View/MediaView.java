package zehramarian.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import generator.Media;
import zehramarian.Controller.MediaController;

public class MediaView extends JFrame{
	
	private MediaController controller;
	private ArrayList<Media> mediaList = new ArrayList<Media>();
	private HashMap<JPanel, Media> map = new HashMap<JPanel, Media>();
	private JPanel jpFilms;
	private JPanel jpMusic;
	private JPanel jpUnclassified;
	private JPanel jpFilmsDropDown;
	private JPanel jpFilmsCenter;
	private JPanel jpMusicDropDown;
	private JPanel jpMusicCenter;
	private JPanel jpUnclassifiedDropDown;
	private JPanel jpUnclassifiedCenter;
	private JLabel jlFilms;
	private JLabel jlMusic;
	private JLabel jlUnclassified;
	private JComboBox jcFilms;
	private JComboBox jcMusic;
	private JComboBox jcUnclassified;
	private Matcher matcher;
	private JPanel panel;

	/**
	 * Constructor - initialisies the controller, creates a visible frame 'Media' 
	 * and calls the GUI widgets and design
	 * @param media
	 * @param controller
	 */
	public MediaView(ArrayList<Media> media, MediaController controller) {
		super("Media");
		
		mediaList = media;
		
		this.controller = controller;
		
		GUIWidgets();
		
		GUIDesign();
	}
	
	/**
	 * Creates the fromes by seting up the panels 
	 */
	public void GUIWidgets(){
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3,1));
		
		jpFilms = new JPanel(new BorderLayout(3,1));
		jpMusic = new JPanel(new BorderLayout(3,1));
		jpUnclassified = new JPanel(new BorderLayout(3,1));
		
		jpFilmsDropDown = new JPanel(new BorderLayout(1,2));
		jpFilmsCenter = new JPanel();
		jpMusicDropDown = new JPanel(new BorderLayout(1,2));
		jpMusicCenter = new JPanel();
		jpUnclassifiedDropDown = new JPanel(new BorderLayout(1,2));
		jpUnclassifiedCenter = new JPanel();
		
		jlFilms = new JLabel("Films");
		jlMusic = new JLabel("Music");
		jlUnclassified = new JLabel("Uncatagorised");

		String[]  filmsOptions = { "Title", "Release Year", "Quality" };
		String[]  musicOptions = { "Track Name", "Artist" };
		
		jcFilms = new JComboBox(filmsOptions);
		jcFilms.setSelectedIndex(0);
		jcMusic = new JComboBox(musicOptions);
		jcMusic.setSelectedIndex(0);
		
		jcFilms.addActionListener(controller);
		jcMusic.addActionListener(controller);
		
		add(jpFilms, BorderLayout.NORTH);
		add(jpMusic, BorderLayout.CENTER);
		add(jpUnclassified, BorderLayout.SOUTH);
		
		jpFilms.add(jpFilmsDropDown, BorderLayout.NORTH);
		jpFilms.add(jpFilmsCenter, BorderLayout.CENTER);
		jpMusic.add(jpMusicDropDown, BorderLayout.NORTH);
		jpMusic.add(jpMusicCenter, BorderLayout.CENTER);
		jpUnclassified.add(jpUnclassifiedDropDown, BorderLayout.NORTH);
		jpUnclassified.add(jpUnclassifiedCenter, BorderLayout.CENTER);
		
		jpFilmsDropDown.add(jlFilms, BorderLayout.WEST);
		jpMusicDropDown.add(jlMusic, BorderLayout.WEST);
		jpUnclassifiedDropDown.add(jlUnclassified, BorderLayout.WEST);
		
		JScrollPane jsFilms = new JScrollPane(jpFilmsCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane jsMusic = new JScrollPane(jpMusicCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane jsUnclassified = new JScrollPane(jpUnclassifiedCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		jpFilms.add(jsFilms, BorderLayout.CENTER);
		jpMusic.add(jsMusic, BorderLayout.CENTER);
		jpUnclassified.add(jsUnclassified, BorderLayout.CENTER);
		
		jpFilmsDropDown.add(jcFilms, BorderLayout.EAST);
		jpMusicDropDown.add(jcMusic, BorderLayout.EAST);
		
		Pattern pFilm = Pattern.compile("(.gif|.flv|.mkv|.mpeg|.mpg|.mov)");
		Pattern pMusic = Pattern.compile("(.aiff|.aac|.aax|.oog|.wav|.wma)");

		for (Media m : mediaList) {
			Matcher matchFilm = pFilm.matcher(m.getName());
			Matcher matchMusic = pMusic.matcher(m.getName());
			if (matchFilm.find() == true) {
				jpFilmsCenter.add(createMediaPane(m, 1));
			} else if (matchMusic.find() == true) {
				jpMusicCenter.add(createMediaPane(m, 2));
			} else {
				jpUnclassifiedCenter.add(createMediaPane(m, 3));
				// the files from mediaList is matched to its correct category by sorting them out (looking at the 'pattern'
				
			}
		}
		
		pack();
	}
	
	/**
	 * 
	 * @param media 
	 * @param ID is case
	 * @return panel - containing the images and information of the films and music. 
	 */
	public JPanel createMediaPane(Media media, int ID) {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JLabel title = new JLabel();
		JLabel subtitle = new JLabel();
		JLabel mediaImage = new JLabel();
		mediaImage = media.getImage();
		String mediaName = media.getName();
		switch (ID) {
		case 1:// film
			Pattern date = Pattern.compile("(\\()(\\d{4})(\\))");
			Pattern quality = Pattern.compile("(\\()(SD|HD), (1080|720|480)p(\\))");
			Pattern name = Pattern.compile("(\\s?)(.+)(\\..+)");

			matcher = date.matcher(mediaName);
			matcher.find();
			subtitle.setText(matcher.group(2));
			mediaName = matcher.replaceFirst("");
			matcher = quality.matcher(mediaName);
			matcher.find();
			subtitle.setText(subtitle.getText() + " - " + matcher.group(2));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setFont(new Font("Tahoma", Font.BOLD, 10));
			subtitle.setHorizontalAlignment(SwingConstants.CENTER);
			mediaName = matcher.replaceFirst("");
			
			matcher = name.matcher(mediaName);
			matcher.find();
			title.setText(matcher.group(2));

			break;

		case 2://music 
			Pattern pattern = Pattern.compile("(.+)-(\\s)(.+)(\\.)(.+)");
			matcher = pattern.matcher(mediaName);
			matcher.matches();
			title.setText(matcher.group(1));
			title.setFont(new Font("Tahoma", Font.BOLD, 10));
			subtitle.setText(matcher.group(3));
			break;

		case 3://unclassified
			title.setText(mediaName);
			title.setFont(new Font("Tahoma", Font.BOLD, 10));
			subtitle.setText("Unclassified");
			break;
		}

		panel.add(mediaImage);
		panel.add(title);
		panel.add(subtitle);
		map.put(panel, media);
		return panel;

	}

	/**
	 * 
	 * @return JPanel - returns the film panel containing the films 
	 */
	public JPanel getFilms(){
		return jpFilmsCenter;
	}
	
	/**
	 * frame is cleared from current list to show the new sorted list 
	 */
	public void clearFilms(){ 
		jpFilmsCenter.removeAll();
	}

	/**
	 * 
	 * @return JPanel - returns the music panel containing music 
	 */
	public JPanel getMusic(){
		return jpMusicCenter;
	}

	/**
	 *  frame is cleared from current list to show the new sorted list 
	 */
	public void clearMusic(){
		jpMusicCenter.removeAll();
	}
	
	/**
	 * @return HashMap<JPanel, Media> - returns hashmap in order to sort out the name,
	 *  title and artist in alphabetical order
	 */
	public HashMap<JPanel, Media> getMap(){
		return map;
	}
	
	/**
	 * 
	 * @param controller
	 */
	public void setController(MediaController controller){
		this.controller = controller;
	}
	
	/**
	 * sets the size of the frame and makes it un-resizeable 
	 * sets the colours of the border and background of the frame 
	 */
	public void GUIDesign(){
		setSize(new Dimension(900,800));
		setResizable(false);
		
		jpFilms.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jpMusic.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jpUnclassified.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		jpFilmsDropDown.setBackground(Color.gray);
		jpMusicDropDown.setBackground(Color.gray);
		jpUnclassifiedDropDown.setBackground(Color.gray);
		
		jpFilms.setBackground(Color.WHITE);
		jpMusic.setBackground(Color.WHITE);
		jpUnclassified.setBackground(Color.WHITE);
		
		jpFilmsDropDown.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jpMusicDropDown.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jpUnclassifiedDropDown.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
