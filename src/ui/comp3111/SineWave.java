package ui.comp3111;

import core.comp3111.DataColumn;

import core.comp3111.DataType;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;

public class SineWave {
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	private LineChart line;
	private Button animate;
	private Button stop;
	private XYChart.Series<Number, Number> series;
	private Timeline timeline;
	private double start_x;
	private double start_y;
	private double end_x;
	private double end_y;
	private double scale_y;
	private Number[] xValues;
	private Number[] yValues;
	private boolean isAnimated;
	private static int x_num_points = 300;
	
	public SineWave(String seriesName) {
		isAnimated = false;
		animate = new Button("animated line chart");
		stop = new Button("stop animation");
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		line = new LineChart<Number, Number>(xAxis, yAxis);
			line.setTitle("Sample Line Chart");
			xAxis.setLabel("X");
			yAxis.setLabel("Y");
			xAxis.setAutoRanging(true);
			xAxis.setMinorTickVisible(true);
			xAxis.setMinorTickLength(1);
			xAxis.setTickUnit(1);
			
			// defining a series
			series = new XYChart.Series<Number, Number>();
			series.setName(seriesName);

			// populating the series with data
			// As we have checked the type, it is safe to downcast to Number[]
			start_x = 0;
			start_y = -10;
			end_x = 30;
			end_y = 10;
			scale_y = 10;
			
			xValues = new Number[x_num_points];
			yValues = new Number[x_num_points];
			
			for (int i=0; i<x_num_points; i++) {
				xValues[i] = start_x + 0.1*i;
				yValues[i] = Math.sin(start_x + 0.1*i) * scale_y;
			}
			

			for (int i = 0; i < x_num_points; i++) {
				series.getData().add(new XYChart.Data(xValues[i], yValues[i]));
			}

			// clear all previous series
			line.getData().clear();
			
			xAxis.setForceZeroInRange(false);
			

			// add the new series as the only one series for this line chart
			line.getData().add(series);
		
		
		timeline = new Timeline (new KeyFrame(Duration.millis(100),
				e-> {
					
					//ObservableList<XYChart.Data<Number, Number>> newlist = FXCollections.observableArrayList();
					xAxis.setLowerBound(start_x+1);
					xAxis.setUpperBound(end_x+1);
					// In DataTable structure, both length must be the same
					series.getData().remove(0, 10);
					for (int i = 0; i < 10; i++) {
						series.getData().add(new XYChart.Data(end_x+0.1*i, Math.sin(end_x + 0.1*i) * scale_y));
					}
					start_x += 1;
					end_x += 1;
					
					// add the new series as the only one series for this line chart
					
						//int newXValue = (int)series.getData().get(i).getXValue()/2;
						//int newYValue = (int)series.getData().get(i).getYValue();
						//newlist.add(new XYChart.Data(newXValue, newYValue));
				}
				));
		animate.setOnMouseClicked(e->{
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
