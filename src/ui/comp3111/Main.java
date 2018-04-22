package ui.comp3111;

import core.comp3111.DataColumn;
import core.comp3111.DataTable;
import core.comp3111.DataType;
import core.comp3111.SampleDataGenerator;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import ui.comp3111.MyPieChart;
/**
 * The Main class of this GUI application
 * 
 * @author cspeter
 *
 */
public class Main extends Application {

	// Attribute: DataTable
	// In this sample application, a single data table is provided
	// You need to extend it to handle multiple data tables
	// Hint: Use java.util.List interface and its implementation classes (e.g.
	// java.util.ArrayList)
	private DataTable sampleDataTable = null;

	// Attributes: Scene and Stage
	private static final int SCENE_NUM = 4;
	private static final int SCENE_MAIN_SCREEN = 0;
	private static final int SCENE_LINE_CHART = 1;
	private static final int SCENE_PIE_CHART = 2;
	private static final int SCENE_SineWave_CHART = 3;
	private static final String[] SCENE_TITLES = { "COMP3111 Chart - [Team Name]", "Sample Line Chart Screen", "Sample Pie Chart Screen", "Sine Wave"};
	private Stage stage = null;
	private Scene[] scenes = null;

	// To keep this application more structural,
	// The following UI components are used to keep references after invoking
	// createScene()

	// Screen 1: paneMainScreen
	private Button btSampleLineChartData, btSampleLineChartDataV2, btSampleLineChart;
	private Button btSamplePieChart, btSineWave;
	private Label lbSampleDataTable, lbMainScreenTitle;

	// Screen 2: paneSampleLineChartScreen
	private MyLineChart myLineChart = null;
	private SineWave sine = null;
	private LineChart lineChart = null;
	private NumberAxis xAxis = null;
	private NumberAxis yAxis = null;
	private Button btLineChartBackMain = null;
	private Button btPieChartBackMain = null;
	private Button btSineWaveBackMain = null;

	/**
	 * create all scenes in this application
	 */
	private void initScenes() {
		scenes = new Scene[SCENE_NUM];
		scenes[SCENE_MAIN_SCREEN] = new Scene(paneMainScreen(), 400, 500);
		scenes[SCENE_LINE_CHART] = new Scene(paneLineChartScreen(), 800, 600);
		scenes[SCENE_PIE_CHART] = new Scene(panePieChartScreen(), 800, 600);
		scenes[SCENE_SineWave_CHART] = new Scene(paneSineWaveScreen(), 800, 600);
		for (Scene s : scenes) {
			if (s != null)
				// Assumption: all scenes share the same stylesheet
				s.getStylesheets().add("Main.css");
		}
	}

	/**
	 * This method will be invoked after createScenes(). In this stage, all UI
	 * components will be created with a non-NULL references for the UI components
	 * that requires interaction (e.g. button click, or others).
	 */
	private void initEventHandlers() {
		initMainScreenHandlers();
		initLineChartScreenHandlers();
		initPieChartScreenHandlers();
		initSineWaveScreenHandlers();
	}

	/**
	 * Initialize event handlers of the line chart screen
	 */
	private void initLineChartScreenHandlers() {

		// click handler
		btLineChartBackMain.setOnAction(e -> {
			putSceneOnStage(SCENE_MAIN_SCREEN);
		});
	}
	
	private void initPieChartScreenHandlers() {

		// click handler
		btPieChartBackMain.setOnAction(e -> {
			putSceneOnStage(SCENE_MAIN_SCREEN);
		});
	}
	
	private void initSineWaveScreenHandlers() {

		// click handler
		btSineWaveBackMain.setOnAction(e -> {
			putSceneOnStage(SCENE_MAIN_SCREEN);
		});
	}

	/**
	 * Populate sample data table values to the chart view
	 */
	private void populateSampleDataTableValuesToChart(String seriesName) {

		// Get 2 columns
		//DataColumn xCol = sampleDataTable.getCol("X");
		//DataColumn yCol = sampleDataTable.getCol("Y");
		Integer[] values1 = {10,20,30,40,45,55,60};
		Integer[] values2 = {70,65,55,45,40,30,15};
		DataColumn xCol = new DataColumn("xCol", values1);
		DataColumn yCol = new DataColumn("yCol", values2);
		myLineChart = new MyLineChart(xCol, yCol, seriesName);
		lineChart = myLineChart.getLineChart();
		

	}

	/**
	 * Initialize event handlers of the main screen
	 */
	private void initMainScreenHandlers() {

		// click handler
		btSampleLineChartData.setOnAction(e -> {

			// In this example, we invoke SampleDataGenerator to generate sample data
			sampleDataTable = SampleDataGenerator.generateSampleLineData();
			lbSampleDataTable.setText(String.format("SampleDataTable: %d rows, %d columns", sampleDataTable.getNumRow(),
					sampleDataTable.getNumCol()));

			populateSampleDataTableValuesToChart("Sample 1");

		});

		// click handler
		btSampleLineChartDataV2.setOnAction(e -> {

			// In this example, we invoke SampleDataGenerator to generate sample data
			sampleDataTable = SampleDataGenerator.generateSampleLineDataV2();
			lbSampleDataTable.setText(String.format("SampleDataTable: %d rows, %d columns", sampleDataTable.getNumRow(),
					sampleDataTable.getNumCol()));

			populateSampleDataTableValuesToChart("Sample 2");

		});

		// click handler
		btSampleLineChart.setOnAction(e -> {
			putSceneOnStage(SCENE_LINE_CHART);
		});
		
		btSamplePieChart.setOnAction(e -> {
			putSceneOnStage(SCENE_PIE_CHART);
		});
		
		btSineWave.setOnAction(e -> {
			putSceneOnStage(SCENE_SineWave_CHART);
		});
	}

	/**
	 * Create the line chart screen and layout its UI components
	 * 
	 * @return a Pane component to be displayed on a scene
	 */
	private Pane paneLineChartScreen() {

		btLineChartBackMain = new Button("Back");
		Integer[] values1 = {10,20,30,40,45,55,60};
		Integer[] values2 = {70,65,55,45,40,30,15};
		DataColumn xCol = new DataColumn("xCol", values1);
		DataColumn yCol = new DataColumn("yCol", values2);
		myLineChart = new MyLineChart(xCol, yCol, "to be specified");
		lineChart = myLineChart.getLineChart();
		
		
		Button animate = myLineChart.getAnimateButton();
		Button stop = myLineChart.getStopButton();
		/*lineChart.setTitle("Empty Line Chart");
		xAxis.setLabel("undefined");
		yAxis.setLabel("undefined");*/
		
		// Layout the UI components
		VBox container = new VBox(20);
		container.getChildren().addAll(lineChart, btLineChartBackMain, animate, stop);
		container.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(container);

		// Apply CSS to style the GUI components
		pane.getStyleClass().add("screen-background");

		return pane;
	}
	
	private Pane panePieChartScreen() {

		btPieChartBackMain = new Button("Back");
		
		String[] keys = {"apple", "banana", "pear", "lemon", "orange", "peach", "berry", "mango"};
		DataColumn column1 = new DataColumn("String", keys);
		Integer[] values = {10,10,10,10,15,15,15,15};
		DataColumn column2 = new DataColumn("Integer", values);
		
		MyPieChart myPieChart = new MyPieChart(column1, column2);
		PieChart pie = myPieChart.getPieChart();
		Button animate = myPieChart.getAnimateButton();
		Button stop = myPieChart.getStopButton();
		// Layout the UI components
		VBox container = new VBox(20);
		container.getChildren().addAll(pie, btPieChartBackMain, animate, stop);
		container.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(container);

		// Apply CSS to style the GUI components
		pane.getStyleClass().add("screen-background");

		return pane;
	}

	private Pane paneSineWaveScreen() {

		btSineWaveBackMain = new Button("Back");
		sine = new SineWave("name there?");
		lineChart = sine.getLineChart();
		
		
		Button animate = sine.getAnimateButton();
		Button stop = sine.getStopButton();
		/*lineChart.setTitle("Empty Line Chart");
		xAxis.setLabel("undefined");
		yAxis.setLabel("undefined");*/
		
		// Layout the UI components
		VBox container = new VBox(20);
		container.getChildren().addAll(lineChart, btLineChartBackMain, animate, stop);
		container.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(container);

		// Apply CSS to style the GUI components
		pane.getStyleClass().add("screen-background");

		return pane;
	}

	/**
	 * Creates the main screen and layout its UI components
	 * 
	 * @return a Pane component to be displayed on a scene
	 */
	private Pane paneMainScreen() {

		lbMainScreenTitle = new Label("COMP3111 Chart");
		btSampleLineChartData = new Button("Sample 1");
		btSampleLineChartDataV2 = new Button("Sample 2");
		btSampleLineChart = new Button("Sample Line Chart");
		btSamplePieChart = new Button("Sample Pie Chart");
		btSineWave = new Button("Sine Wave Line Chart");
		lbSampleDataTable = new Label("DataTable: empty");

		// Layout the UI components

		HBox hc = new HBox(20);
		hc.setAlignment(Pos.CENTER);
		hc.getChildren().addAll(btSampleLineChartData, btSampleLineChartDataV2);

		VBox container = new VBox(20);
		container.getChildren().addAll(lbMainScreenTitle, hc, lbSampleDataTable, new Separator(), btSampleLineChart, btSamplePieChart, btSineWave);
		container.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(container);

		// Apply style to the GUI components
		btSampleLineChart.getStyleClass().add("menu-button");
		btSamplePieChart.getStyleClass().add("menu-button");
		btSineWave.getStyleClass().add("menu-button");
		lbMainScreenTitle.getStyleClass().add("menu-title");
		pane.getStyleClass().add("screen-background");

		return pane;
	}

	/**
	 * This method is used to pick anyone of the scene on the stage. It handles the
	 * hide and show order. In this application, only one active scene should be
	 * displayed on stage.
	 * 
	 * @param sceneID
	 *            - The sceneID defined above (see SCENE_XXX)
	 */
	private void putSceneOnStage(int sceneID) {

		// ensure the sceneID is valid
		if (sceneID < 0 || sceneID >= SCENE_NUM)
			return;

		stage.hide();
		stage.setTitle(SCENE_TITLES[sceneID]);
		stage.setScene(scenes[sceneID]);
		stage.setResizable(true);
		stage.show();
	}

	/**
	 * All JavaFx GUI application needs to override the start method You can treat
	 * it as the main method (i.e. the entry point) of the GUI application
	 */
	@Override
	public void start(Stage primaryStage) {
		try {

			stage = primaryStage; // keep a stage reference as an attribute
			initScenes(); // initialize the scenes
			initEventHandlers(); // link up the event handlers
			putSceneOnStage(SCENE_MAIN_SCREEN); // show the main screen

		} catch (Exception e) {

			e.printStackTrace(); // exception handling: print the error message on the console
		}
	}

	/**
	 * main method - only use if running via command line
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
