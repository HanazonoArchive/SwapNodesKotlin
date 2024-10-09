package dsa.swapnodeskotlin.swapnodeskotlin

import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.Label
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import javafx.stage.Stage
import kotlin.system.exitProcess
import java.io.File

class ControllerMain {

    @FXML
    private lateinit var CloseButton: Pane

    @FXML
    private lateinit var OpenFileButton: Pane

    @FXML
    private lateinit var FileNameLabel: Label

    // Algorithm Radio Button
    @FXML
    lateinit var RadioAlgorithm1: RadioButton

    @FXML
    lateinit var RadioAlgorithm2: RadioButton

    @FXML
    lateinit var RadioAlgorithm3: RadioButton

    @FXML
    lateinit var RadioAlgorithm4: RadioButton

    @FXML
    lateinit var RadioAlgorithm5: RadioButton

    @FXML
    lateinit var RadioAlgorithm6: RadioButton

    @FXML
    lateinit var RadioAlgorithm7: RadioButton

    private lateinit var stage: Stage
    private lateinit var radioToggleGroup: ToggleGroup

    @FXML
    private fun initialize() {
        // Initialize the ToggleGroup and assign it to the RadioButtons
        radioToggleGroup = ToggleGroup()
        RadioAlgorithm1.toggleGroup = radioToggleGroup
        RadioAlgorithm2.toggleGroup = radioToggleGroup
        RadioAlgorithm3.toggleGroup = radioToggleGroup
        RadioAlgorithm4.toggleGroup = radioToggleGroup
        RadioAlgorithm5.toggleGroup = radioToggleGroup
        RadioAlgorithm6.toggleGroup = radioToggleGroup
        RadioAlgorithm7.toggleGroup = radioToggleGroup
    }

    @FXML
    private fun handleCloseButtonClicked() {
        exitProcess(0)
    }

    @FXML
    private fun handleOpenFileButtonClicked() {
        // Check if an algorithm is selected before opening the file chooser
        if (radioToggleGroup.selectedToggle == null) {
            showAlert("Please select an algorithm before opening a file.")
            return
        }

        val fileChooser = FileChooser()
        fileChooser.title = "Open a Text File"
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Text Files", "*.txt"))

        val desktopPath = System.getProperty("user.home") + "/Desktop"
        fileChooser.initialDirectory = File(desktopPath)

        val selectedFile = fileChooser.showOpenDialog(stage)
        if (selectedFile != null) {
            val fileName = selectedFile.name
            FileNameLabel.text = fileName
            println("Selected file: ${selectedFile.absolutePath}")

            // Read the file and categorize the contents
            val (integers, strings) = readFileAndCategorize(selectedFile)

            // Print the arrays for demonstration
            println("Integer Array: ${integers.joinToString()}")
            println("String Array: ${strings.joinToString()}")

            // Call the method based on the selected algorithm
            handleAlgorithmSelection()
        }
    }

    private fun readFileAndCategorize(file: File): Pair<IntArray, ArrayList<String>> {
        val integers = mutableListOf<Int>()
        val strings = ArrayList<String>()

        file.bufferedReader().use { reader ->
            val content = reader.readText().trim() // Read entire file content

            // Split by commas and trim whitespace
            val parts = content.split(",").map { it.trim() }

            // Check if all parts can be parsed as integers
            if (parts.all { it.isNotEmpty() && it.all { char -> char.isDigit() } }) {
                // Convert to integers if all parts are valid
                integers.addAll(parts.map { it.toInt() })
            } else {
                // Add each part to the string array individually
                strings.addAll(parts) // Use addAll to put each part in the array
            }
        }

        return Pair(integers.toIntArray(), strings)
    }

    private fun handleAlgorithmSelection() {
        // Check which RadioButton is selected
        val selectedToggle = radioToggleGroup.selectedToggle as? RadioButton
        if (selectedToggle != null) {
            val selectedAlgorithm = selectedToggle.text
            println("Selected algorithm: $selectedAlgorithm")
            // Call the corresponding method based on the selected algorithm
            when (selectedAlgorithm) {
                "Algorithm 1" -> algorithm1Method()
                "Algorithm 2" -> algorithm2Method()
                "Algorithm 3" -> algorithm3Method()
                "Algorithm 4" -> algorithm4Method()
                "Algorithm 5" -> algorithm5Method()
                "Algorithm 6" -> algorithm6Method()
                "Algorithm 7" -> algorithm7Method()
            }
        }
    }

    private fun showAlert(message: String) {
        val alert = Alert(AlertType.WARNING)
        alert.title = "Warning"
        alert.headerText = null
        alert.contentText = message
        alert.showAndWait()
    }

    private fun algorithm1Method() {
        // Implement logic for Algorithm 1
        println("Running Algorithm 1...")
    }

    private fun algorithm2Method() {
        // Implement logic for Algorithm 2
        println("Running Algorithm 2...")
    }

    private fun algorithm3Method() {
        // Implement logic for Algorithm 3
        println("Running Algorithm 3...")
    }

    private fun algorithm4Method() {
        // Implement logic for Algorithm 4
        println("Running Algorithm 4...")
    }

    private fun algorithm5Method() {
        // Implement logic for Algorithm 5
        println("Running Algorithm 5...")
    }

    private fun algorithm6Method() {
        // Implement logic for Algorithm 6
        println("Running Algorithm 6...")
    }

    private fun algorithm7Method() {
        // Implement logic for Algorithm 7
        println("Running Algorithm 7...")
    }

    fun setStage(stage: Stage) {
        this.stage = stage
        OpenFileButton.setOnMouseClicked { handleOpenFileButtonClicked() }
        CloseButton.setOnMouseClicked { handleCloseButtonClicked() }
    }
}
