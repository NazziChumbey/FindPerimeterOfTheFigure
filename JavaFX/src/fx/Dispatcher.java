package fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Dispatcher extends Application {
	Button buttonUpdateImage, buttonCalcData;
	static ImageView imageWeb, imageCanny;
	Label label = new Label("");
	static int cannyValue = 350;
	static int imageValue = 70;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		buttonCalcData = new Button("Calculation");
		buttonUpdateImage = new Button("Update");
		imageWeb = new ImageView();
		imageCanny = new ImageView();
		imageWeb.setImage(new Image("file:imageStart.jpg", 480, 576, true, true));
		imageCanny.setImage(new Image("file:imageFinish.jpg", 480, 576, true, true));
	 
		buttonUpdateImage.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				TakePhoto.Take();
				imageWeb.setImage(new Image("file:camera.jpg", 480, 576,
						true, true));
			}
		});
		buttonCalcData.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {				
				label.setText(Calculation.CalcData());
				imageCanny.setImage(new Image("file:LogoCanny.jpg", 480, 576,
						true, true));
			}
		});

		primaryStage.setTitle("OpenCV");
		BorderPane root = new BorderPane();

		Pane pane = new Pane();
		buttonUpdateImage.setLayoutY(420.0);
		buttonUpdateImage.setLayoutX(225.0);

		imageWeb.setLayoutY(20.0);
		imageWeb.setLayoutX(10.0);

		imageCanny.setLayoutY(20.0);
		imageCanny.setLayoutX(534.0);

		buttonCalcData.setLayoutY(420.0);
		buttonCalcData.setLayoutX(740.0);

		label.setLayoutY(520.0);
		label.setLayoutX(10.0);
		
		pane.getChildren().addAll(buttonUpdateImage, imageWeb, imageCanny,
				buttonCalcData, label);
		root.setTop(pane);
		Scene scene = new Scene(root, 1024, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
