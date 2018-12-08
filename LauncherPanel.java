package fr.zormiia.launcher;

import fr.trxyy.account.GameSaver;
import fr.trxyy.authentication.GameAuth;
import fr.trxyy.base.Configuration;
import fr.trxyy.interfaces.components.LauncherButton;
import fr.trxyy.interfaces.components.LauncherLabel;
import fr.trxyy.interfaces.components.LauncherPasswordField;
import fr.trxyy.interfaces.components.LauncherProgressBar;
import fr.trxyy.interfaces.components.LauncherRectangle;
import fr.trxyy.interfaces.components.LauncherTextField;
import fr.trxyy.interfaces.utils.FontLoader;
import fr.trxyy.interfaces.utils.OSUtil;
import fr.trxyy.interfaces.utils.ResourceLocation;
import fr.trxyy.updater.GameParser;
import fr.trxyy.updater.GameUpdater;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LauncherPanel {
	
	private LauncherTextField usernameField;
	private LauncherPasswordField passwordField;
	private LauncherButton loginButton;
	private ResourceLocation resources;
	private LauncherButton closeButton;
	private LauncherButton minimizeButton;
	private LauncherRectangle topRetangle;
	private LauncherLabel titleLabel;
	private GameSaver saver = new GameSaver(OSUtil.getDirectory());
	private LauncherLabel downloadLabel;
	private LauncherProgressBar progressbar; 
	private GameUpdater gameupdater = new GameUpdater();
	private GameParser gameParser = new GameParser();


		public LauncherPanel(Pane root) {
		
		this.topRetangle = new LauncherRectangle(root, 0, 0, Configuration.getWidth(), 24);
		this.topRetangle.setOpacity(0.3);
		
		this.titleLabel = new LauncherLabel(root);
		this.titleLabel.setText("Mon Launcher");
		this.titleLabel.setPosition(1, 0);
		this.titleLabel.setFont(FontLoader.loadFont("Sabado-Regular.otf", "Sabado", 20F));
		this.titleLabel.setTextFill(Color.WHITE);
		
		this.downloadLabel = new LauncherLabel(root);
		this.downloadLabel.setPosition(170, 0);
		this.downloadLabel.setSize(250, 20);
		this.downloadLabel.setFont(FontLoader.loadFont("Sabado-Regular.otf", "Sabado", 20F));
		this.downloadLabel.setOpacity(0.5);
		this.downloadLabel.setTextFill(Color.WHITE);
		
		this.usernameField = new LauncherTextField(root);
		this.usernameField.setText(saver.getUsername(""));
		this.usernameField.setStyle("-fx-background-color: rgba(53, 89, 119, 0.4); -fx-text-fill: white;");
		this.usernameField.setFont(FontLoader.loadFont("Sabado-Regular.otf", "Sabado", 18F));
		this.usernameField.setPosition(5, Configuration.getHeight() - 40);
		this.usernameField.setSize(200, 35);
		
		this.passwordField = new LauncherPasswordField(root);
		this.passwordField.setStyle("-fx-background-color: rgba(53, 89, 119, 0.4); -fx-text-fill: white;");
		this.passwordField.setFont(FontLoader.loadFont("Sabado-Regular.otf", "Sabado", 18F));
		this.passwordField.setPosition(210, Configuration.getHeight() - 40);
		this.passwordField.setSize(200, 35);
		
		this.loginButton = new LauncherButton(root);
		this.loginButton.setText("Jouer");
		this.loginButton.setStyle("-fx-background-color: rgba(53, 89, 119, 0.4); -fx-text-fill: white;");
		this.loginButton.setFont(FontLoader.loadFont("Sabado-Regular.otf", "Sabado", 18F));
		this.loginButton.setPosition(Configuration.getWidth() / 2 - 235, 250);
		this.loginButton.setSize(500, 65);
		this.loginButton.setAction(E -> {
			saver.saveUsername(usernameField.getText());
			
			GameAuth auth = new GameAuth(usernameField.getText(), passwordField.getText(), true);
			auth.tryLogin();
			
			if (auth.isLogged()) {
				this.usernameField.setDisable(true);
				this.passwordField.setDisable(true);
				this.loginButton.setDisable(true);
				this.saver.saveUsername(usernameField.getText());
				
				if (!gameUpdtater.isUpdating()) {
						Timeline tl  = new Timeline(new KeyFrame(Duration.seconds(0), dr -> downloadLabel.setText("" + df2.format(gameUpdater.getProgressBar().getProgress() * 100) + "%")), new KeyFrame(Duration.seconds(0.1)));
						tl.setCycleCount(Animation.INDEFINITE);
						tl.play();
						this.gameupdater.checkForUpdate(gameParser, progressBar);
								
			}
			
		});
		
		this.closeButton = new LauncherButton(root);
		this.closeButton.setSize(38,  22);
		this.closeButton.setPosition(Configuration.getWidth() - 38, 1);
		this.closeButton.setInvisible();
		this.closeButton.setStyle("-fx-padding: 0;");
		ImageView closeIcon = new ImageView(resources.loadImage("close.png"));
		closeIcon.setFitWidth(this.closeButton.getPrefWidth());
		closeIcon.setFitHeight(this.closeButton.getPrefHeight());
		this.closeButton.setGraphic(closeIcon);
		this.closeButton.setAction(e -> {
			System.exit(0);
		});
		
		this.minimizeButton = new LauncherButton(root);
		this.minimizeButton.setSize(38,  22);
		this.minimizeButton.setPosition(Configuration.getWidth() - 84, - 3);
		this.minimizeButton.setInvisible();
		this.closeButton.setStyle("-fx-padding: 0;");
		ImageView minimizeIcon = new ImageView(resources.loadImage("minimize.png"));
		minimizeIcon.setFitWidth(this.minimizeButton.getPrefWidth());
		minimizeIcon.setFitHeight(this.minimizeButton.getPrefHeight());		
		this.minimizeButton.setGraphic(minimizeIcon);
		this.minimizeButton.setAction(e -> {
			Stage stage = (Stage)((LauncherButton)e.getSource()).getScene().getWindow();
			stage.setIconified(true);
		});	
		
		this.progressbar = new LauncherProgressBar(root);
		this.progressbar.setPosition(Configuration.getWidth() - 290, Configuration.getHeight() - 20);
		this.progressbar.setSize(250, 10);
		
		}
		
	}

}