package fr.zormiia.launcher;

import fr.trxyy.base.Configuration;
import fr.trxyy.base.Initializer;
import fr.trxyy.gamelaunch.Tweaks;
import fr.trxyy.gamelaunch.Versions;
import fr.trxyy.interfaces.LauncherApp;
import fr.trxyy.interfaces.LauncherBase;
import fr.trxyy.interfaces.components.LauncherBackground;
import fr.trxyy.interfaces.components.LauncherLogo;
import fr.trxyy.interfaces.utils.ResourceLocation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SuppressWarnings("static-access")
public class LauncherMAin extends LauncherApp{
	@SuppressWarnings("unused")
	private LauncherBackground backgroundPanel;
	private ResourceLocation resources;
	private LauncherPanel launcherpanel;
	private LauncherLogo logopanel;
	
	public static void main(String[] args) {
		Initializer.registerBasics("Mon Launcher", 880, 550);
		Initializer.registerSpecs("/resources/", "", "http://akraniarp.000webhostapp.com/launcher/monpack/", Versions.V1_7_10, Tweaks.FORGE_1_7_10, false);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		LauncherBase launcher = new LauncherBase(primaryStage, scene, StageStyle.UNDECORATED, true);
		launcher.setIconImage(primaryStage, resources.loadImage("favicon.png"));
	}

	private Parent createContent() {
		Pane root = new Pane();
		root.setPrefSize(Configuration.getWidth(), Configuration.getHeight());
		this.backgroundPanel = new LauncherBackground(this.resources.getMedia("background.mp4"), root);
		this.launcherpanel = new LauncherPanel(root);
		this.logopanel = new LauncherLogo(this.resources.loadImage("logo.png"), 600, 120, root);
		this.logopanel.setImagePos(Configuration.getWidth() / 2 - 280, 130);
		return root;
	}

}
