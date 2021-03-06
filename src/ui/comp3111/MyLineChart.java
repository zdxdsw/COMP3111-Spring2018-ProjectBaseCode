package ui.comp3111;

import core.comp3111.DataColumn;

import core.comp3111.DataType;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

public class MyLineChart {
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	private LineChart line;
	private Button animate;
	private Button stop;
	private XYChart.Series<Number, Number> series;
	private Timeline timeline;

	public MyLineChart(DataColumn xCol, DataColumn yCol, String seriesName) {
		animate = new Button("animated line chart");
		stop = new Button("stop animation");
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		line = new LineChart<Number, Number>(xAxis, yAxis);
	// Ensure both columns exist and the type is number
		//if (xCol != null && yCol != null && xCol.getTypeName().equals(DataType.TYPE_NUMBER)
		//	&& yCol.getTypeName().equals(DataType.TYPE_NUMBER)) {
		if (xCol != null && yCol != null) {
			line.setTitle("Sample Line Chart");
			xAxis.setLabel("X");
			yAxis.setLabel("Y");
			
			// defining a series
			series = new XYChart.Series<Number, Number>();
			series.setName(seriesName);

			// populating the series with data
			// As we have checked the type, it is safe to downcast to Number[]
			Number[] xValues = (Number[]) xCol.getData();
			Number[] yValues = (Number[]) yCol.getData();

			// In DataTable structure, both length must be the same
			int len = xValues.length;

			for (int i = 0; i < len; i++) {
				series.getData().add(new XYChart.Data(xValues[i], yValues[i]));
			}

			// clear all previous series
			line.getData().clear();
			
			xAxis.setForceZeroInRange(false);
			

			// add the new series as the only one series for this line chart
			line.getData().add(series);
		}
		
		timeline = new Timeline (new KeyFrame(Duration.millis(500),
				e-> {
					
					//ObservableList<XYChart.Data<Number, Number>> newlist = FXCollections.observableArrayList();
					
					// In DataTable structure, both length must be the same
					int len = series.getData().size();
					
					for (int i = 0; i < len; i++) {
						series.getData().get(i).setXValue((int)series.getData().get(i).getXValue()+5);
						series.getData().get(i).setYValue((int)series.getData().get(i).getYValue());
						//int newXValue = (int)series.getData().get(i).getXValue()/2;
						//int newYValue = (int)series.getData().get(i).getYValue();
						//newlist.add(new XYChart.Data(newXValue, newYValue));
					}

					// clear all previous series
					//line.getData().clear();

					// add the new series as the only one series for this line chart
					//series.setData(newlist);
				}
				));
		
		animate.setOnAction(e->{
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
		});
		stop.setOnMouseClicked(e->{
			timeline.pause();
		});
		
		/*animate.setOnAction(e->{
			
			ObservableList<XYChart.Data<Number, Number>> newlist = FXCollections.observableArrayList();
			
			// In DataTable structure, both length must be the same
			int len = series.getData().size();

			for (int i = 0; i < len; i++) {
				int newXValue = (int)series.getData().get(i).getXValue() +2;
				int newYValue = (int)series.getData().get(i).getYValue();
				newlist.add(new XYChart.Data(newXValue, newYValue));
			}

			// clear all previous series
			//line.getData().clear();

			// add the new series as the only one series for this line chart
			series.setData(newlist);
		});*/
	}
	
	public LineChart getLineChart() {
		return line;
	}
	
	public Button getAnimateButton() {
		return animate;
	}
	
	public Button getStopButton() {
		return stop;
	}


}


