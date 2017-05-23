package zehramarian.Controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import generator.Media;
import zehramarian.View.MediaView;

public class MediaController implements ActionListener{
	private MediaView view;
	
	/**
	 * The constructor initializes the view. 
	 * It makes the view's comboBox controlled in this class
	 */
	public MediaController(){
		view = new MediaView(generator.MediaGenerator.getMedia(), this);
		view.setVisible(true);
	}
	
	/**
	 * This method deals with the drop down menu which sorts by Title, Release year and Quality. 
	 * Title-the updated version of the title is mapped to cases of film. 
	 * Release year-the date is now mapped to the film - in order, most recent.
	 * Quality - get the components and add 1080 to the film panel (case), then 720 and 480p
	 */
	public void actionPerformed(ActionEvent arg) {
		Object source = arg.getSource();
		TreeMap<String, Media> map = new TreeMap<String, Media>();
		TreeMap<Integer, Media> mapDate = new TreeMap<Integer, Media>();
		HashMap<JPanel, Media> getMap = view.getMap();
		switch (((JComboBox) source).getSelectedItem().toString()) {
			case ("Title"):// if there title begins with 'The' or space, its ignored.
				for (Component component : view.getFilms().getComponents()) {
					String getMediaText = ((JLabel) ((JPanel) component).getComponent(1)).getText();
					Pattern pattern = Pattern.compile("The|\\s+");
					Matcher matcher = pattern.matcher(getMediaText);
					matcher.find();
					getMediaText = matcher.replaceAll("");
					map.put(getMediaText, getMap.get(component));
				}
				view.clearFilms(); // frame is cleared from current list to show the new sorted list
				for (Entry<String, Media> entry : map.entrySet()) {
					view.getFilms().add(view.createMediaPane(entry.getValue(), 1));
				}
	
				break;
			
			case ("Release Year"):// gets the date and converts it into integer. 
				for (Component component : view.getFilms().getComponents()) {
					String getMediaText = ((JLabel) ((JPanel) component).getComponent(2)).getText();
					Pattern pattern = Pattern.compile("\\d{4}");
					Matcher matcher = pattern.matcher(getMediaText);
					matcher.find();
					int i = -Integer.parseInt(matcher.group());
					mapDate.put(i, getMap.get(component));
					 
				}
				view.clearFilms();// frame is cleared from current list to show the new sorted list
				for (Entry<Integer, Media> entry : mapDate.entrySet()) {
					view.getFilms().add(view.createMediaPane(entry.getValue(), 1));
				}
				break;
			
			case ("Quality"):
				JPanel temp = new JPanel();
				for (Component component : view.getFilms().getComponents()) {
					temp.add(component);
				}
				view.clearFilms();// frame is cleared from current list to show the new sorted list
				for (int i = 0; i < 3; i++) {
					Pattern pattern = Pattern.compile("1080|720|480");
					for (Component component : temp.getComponents()) {
						Matcher matcher = pattern.matcher(getMap.get(component).getName());
						matcher.find();
						String group = matcher.group();
						switch (i) {
						case 0:
							if (group.equals("1080")) {
								view.getFilms().add(component);
							}
							break;
						case 1:
							if (group.equals("720")) {
								view.getFilms().add(component);
							}
							break;
						case 2:
							if (group.equals("480")) {
								view.getFilms().add(component);
							}
							break;
						}
					}
				}
				break;
			
			case ("Track Name"):
				for (Component component : view.getMusic().getComponents()) {
					String getMediaText = ((JLabel) ((JPanel) component).getComponent(1)).getText();
					Pattern pattern = Pattern.compile("The|\\s+");
					Matcher matcher = pattern.matcher(getMediaText);
					matcher.find();
					getMediaText = matcher.replaceAll("");
					map.put(getMediaText, getMap.get(component));
				}
				view.clearMusic();// frame is cleared from current list to show the new sorted list
				for (Entry<String, Media> entry : map.entrySet()) {
					view.getMusic().add(view.createMediaPane(entry.getValue(), 2));
				}
	
				break;
			
			case ("Artist"):
				for (Component component : view.getMusic().getComponents()) {
					String getMediaText = ((JLabel) ((JPanel) component).getComponent(2)).getText();
					Pattern pattern = Pattern.compile("The|\\s+");
					Matcher matcher = pattern.matcher(getMediaText);
					matcher.find();
					getMediaText = matcher.replaceAll("");
					map.put(getMediaText, getMap.get(component));
				}
				view.clearMusic();// frame is cleared from current list to show the new sorted list
				for (Entry<String, Media> entry : map.entrySet()) {
					view.getMusic().add(view.createMediaPane(entry.getValue(), 2));
				}
		}

		view.getFilms().repaint(); 
		view.getFilms().revalidate();

		view.getMusic().repaint();
		view.getMusic().revalidate();

	}
}
