package ui.comp3111;

import java.util.HashMap;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import core.comp3111.DataColumn;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;

import java.net.URL;
import java.awt.event.MouseEvent;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MyPieChart implements Initializable{
	private PieChart pie;
	private HashMap<String, Integer> map;
	private Button animate;
	private ObservableList<PieChart.Data> pcData;
	public Timeline timeline;
	
	public MyPieChart(DataColumn column1, DataColumn column2) {
		animate = new Button("animated pie chart");
		map = new HashMap();
		String[] keyList = (String[])column1.getData();
		Integer[] valueList = (Integer[])column2.getData();
		int size = column1.getSize();
		pcData = FXCollections.observableArrayList();
		for (int i=0; i<size; i++) {
			map.put(keyList[i], valueList[i]);
			PieChart.Data arg = new PieChart.Data(keyList[i], valueList[i]);
			pcData.add(i, arg);
		}
		pie = new PieChart(pcData);
		
		/*animate.setOnAction(e-> {
			pcData.stream().forEach(pieData -> {
    	        Bounds b1 = pieData.getNode().getBoundsInLocal();
    	        double newX = (b1.getWidth()) / 2 + b1.getMinX();
    	        double newY = (b1.getHeight()) / 2 + b1.getMinY();
    	        // Make sure pie wedge location is reset
    	        pieData.getNode().setTranslateX(0);
    	        pieData.getNode().setTranslateY(0); 
    	        TranslateTransition tt = new TranslateTransition(
    	                Duration.millis(1500), pieData.getNode());
    	        tt.setByX(newX);
    	        tt.setByY(newY);
    	        tt.setAutoReverse(true);
    	        tt.setCycleCount(2);
    	        tt.play();
    	    });

		});*/
		

		timeline = new Timeline(
					new KeyFrame(Duration.millis(500), 
							event->{
								pcData.stream().forEach(pieData ->{
							pieData.getNode().setRotate(20);});
							})
					);
		timeline.setCycleCount(3);

	}
			
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        // Create the observable list and add the data
        pcData = FXCollections.observableArrayList();
        pcData.add(new PieChart.Data("Nokia", 77.3));
        pcData.add(new PieChart.Data("RIM", 51.1));
        pcData.add(new PieChart.Data("Apple", 93.2));
        pcData.add(new PieChart.Data("HTC", 43.5));
        pcData.add(new PieChart.Data("Samsung", 94.0));
        pcData.add(new PieChart.Data("Others", 132.3));
        pie.setData(pcData);
        pie.setTitle("Smart Phone Sales 2011");
        pcData.stream().forEach(pieData -> {
    	    pieData.getNode().setOnMouseClicked(event -> {
    	        Bounds b1 = pieData.getNode().getBoundsInLocal();
    	        double newX = (b1.getWidth()) / 2 + b1.getMinX();
    	        double newY = (b1.getHeight()) / 2 + b1.getMinY();
    	        // Make sure pie wedge location is reset
    	        pieData.getNode().setTranslateX(0);
    	        pieData.getNode().setTranslateY(0);
    	        TranslateTransition tt = new TranslateTransition(
    	                Duration.millis(1500), pieData.getNode());
    	        tt.setByX(newX);
    	        tt.setByY(newY);
    	        tt.setAutoReverse(true);
    	        tt.setCycleCount(2);
    	        tt.play();
    	    });
    	});
    }
	
	
	public PieChart getPieChart() {
		return pie;
	}
	
	public Button getAnimateButton() {
		return animate;
	}
	
}
