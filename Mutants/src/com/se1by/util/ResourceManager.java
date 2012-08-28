package com.se1by.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.loading.LoadingList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResourceManager {
	private static ResourceManager instance = new ResourceManager();
	private Map<Integer, HashMap<String, Image>> imageMap;
	private Map<Integer, HashMap<String, Sound>> soundMap;
	private Map<Integer, HashMap<String, String>> textMap;
	private Font georgia;

	private ResourceManager() {
		imageMap = new HashMap<>();
		soundMap = new HashMap<>();
		try {
			setFont(new UnicodeFont("res/fonts/georgia.ttf", 10, false, false));
		} catch (SlickException e) {
			System.out.println("Unable to load font georgia!");
			e.printStackTrace();
		}
	}

	public final static ResourceManager getInstance() {
		return instance;
	}

	public void loadResources(InputStream is) throws SlickException {
		loadResources(is, false);
	}

	public void loadResources(InputStream is, boolean deferred)
			throws SlickException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new SlickException("Could not load resources", e);
		}
		Document doc = null;
		try {
			doc = docBuilder.parse(is);
		} catch (SAXException e) {
			throw new SlickException("Could not load resources", e);
		} catch (IOException e) {
			throw new SlickException("Could not load resources", e);
		}

		doc.getDocumentElement().normalize();

		NodeList listResources = doc.getElementsByTagName("resource");

		int totalResources = listResources.getLength();

		if (deferred) {
			LoadingList.setDeferredLoading(true);
		}

		for (int resourceIdx = 0; resourceIdx < totalResources; resourceIdx++) {

			Node resourceNode = listResources.item(resourceIdx);

			if (resourceNode.getNodeType() == Node.ELEMENT_NODE) {
				Element resourceElement = (Element) resourceNode;

				String type = resourceElement.getAttribute("type");
				int level = Integer.parseInt(resourceElement
						.getAttribute("level"));

				if (type.equals("image")) {
					addElementAsImage(resourceElement, level);
				} else if (type.equals("sound")) {
					addElementAsSound(resourceElement, level);
				} else if (type.equals("text")) { // For translations
					addElementAsText(resourceElement, level);
				} else if (type.equals("font")) {
				}
			}
		}
	}

	private final void addElementAsImage(Element resourceElement, int level)
			throws SlickException {
		loadImage(resourceElement.getAttribute("id"),
				resourceElement.getTextContent(), level);
	}

	public Image loadImage(String id, String path, int level) throws SlickException {
		if (path == null || path.length() == 0)
			throw new SlickException("Image resource [" + id
					+ "] has invalid path");

		Image image = null;
		try {
			image = new Image(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load image", e);
		}

		this.imageMap.get(level).put(id, image);

		return image;
	}

	public final Image getImage(String ID, int level) {
		return imageMap.get(level).get(ID);
	}

	private void addElementAsText(Element resourceElement, int level)
			throws SlickException {
		loadText(resourceElement.getAttribute("id"),
				resourceElement.getTextContent(), level);
	}

	public String loadText(String id, String value, int level) throws SlickException {
		if (value == null)
			throw new SlickException("Text resource [" + id
					+ "] has invalid value");

		textMap.get(level).put(id, value);

		return value;
	}

	public String getText(String ID, int level) {
		return textMap.get(level).get(ID);
	}

	private void addElementAsSound(Element resourceElement, int level)
			throws SlickException {
		loadSound(resourceElement.getAttribute("id"),
				resourceElement.getTextContent(), level);
	}

	public Sound loadSound(String id, String path, int level) throws SlickException {
		if (path == null || path.length() == 0)
			throw new SlickException("Sound resource [" + id
					+ "] has invalid path");

		Sound sound = null;

		try {
			sound = new Sound(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load sound", e);
		}

		this.soundMap.get(level).put(id, sound);

		return sound;
	}

	public final Sound getSound(String ID, int level) {
		return soundMap.get(level).get(ID);
	}

	public Font getFont() {
		return georgia;
	}

	public void setFont(Font georgia) {
		this.georgia = georgia;
	}
}
