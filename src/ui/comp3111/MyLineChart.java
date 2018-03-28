package ui.comp3111;

import core.comp3111.DataColumn;
import core.comp3111.DataType;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class MyLineChart {
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	private LineChart line;

	public MyLineChart(DataColumn xCol, DataColumn yCol) {
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		line = new LineChart<Number, Number>(xAxis, yAxis);
	// Ensure both columns exist and the type is number
		if (xCol != null && yCol != null && xCol.getTypeName().equals(DataType.TYPE_NUMBER)
			&& yCol.getTypeName().equals(DataType.TYPE_NUMBER)) {

			line.setTitle("Sample Line Chart");
			xAxis.setLabel("X");
			yAxis.setLabel("Y");
			
			// defining a series
			XYChart.Series series = new XYChart.Series();
			String seriesName = "to be specified by user";
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

			// add the new series as the only one series for this line chart
			line.getData().add(series);
		}
	}
	
	public LineChart getLineChart() {
		return line;
	}


}


