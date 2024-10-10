package dsa.swapnodeskotlin.swapnodeskotlin

// Importing Algorithm
import dsa.swapnodesjava.swapnodesjava.Algorithm1
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.Label
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.scene.canvas.Canvas
import java.io.File
import kotlin.system.exitProcess
import javafx.scene.canvas.GraphicsContext


class ControllerMain {

    // Algorithm Instances
    private lateinit var algorithm1: Algorithm1

    // Need to changes
    //private lateinit var algorithm2: Algorithm1
    //private lateinit var algorithm3: Algorithm1
    //private lateinit var algorithm4: Algorithm1
    //private lateinit var algorithm5: Algorithm1
    //private lateinit var algorithm6: Algorithm1
    //private lateinit var algorithm7: Algorithm1

    @FXML
    private lateinit var CloseButton: Pane

    @FXML
    private lateinit var OpenFileButton: Pane

    @FXML
    private lateinit var FileNameLabel: Label

    @FXML
    private lateinit var TimerLabel: Label

    @FXML
    private lateinit var HelpButton: Pane

    @FXML
    private lateinit var Node1SwapTF: TextField

    @FXML
    private lateinit var Node2SwapTF: TextField

    @FXML
    private lateinit var ResultLabelAREA: Label

    @FXML
    private lateinit var SwappedLabelAREA: Label


    var node1: Int? = null
    var node2: Int? = null

    // Algorithm Radio Button
    @FXML
    lateinit var RadioAlgorithm1: RadioButton
    lateinit var RadioAlgorithm2: RadioButton
    lateinit var RadioAlgorithm3: RadioButton
    lateinit var RadioAlgorithm4: RadioButton
    lateinit var RadioAlgorithm5: RadioButton
    lateinit var RadioAlgorithm6: RadioButton
    lateinit var RadioAlgorithm7: RadioButton

    private lateinit var stage: Stage
    private lateinit var radioToggleGroup: ToggleGroup

    // Common Declaration
    private var integerPairs: List<List<Int>> = emptyList() // Check

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
    private fun handleHelpButtonClicked() {
        showHelpButton("UI - Agsoy\nBackend - Dingal\nImprovements - Pechayco & Palma\n\n -To Make a Text File\nText File must be composed of Integers.")
    }

    @FXML
    private fun handleOpenFileButtonClicked() {
        // Check if an algorithm is selected before opening the file chooser
        if (radioToggleGroup.selectedToggle == null) {
            showAlert("Please select an algorithm before opening a file.")
            return
        } else if (Node1SwapTF.text.isEmpty() && Node2SwapTF.text.isEmpty()) {
            showAlertNodes("Please put the Swapping Nodes.")
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

            // Read the file and store in integerPairs
            integerPairs = readFileAsPairs(selectedFile)

            // Print the array for demonstration
            println("Integer Pairs: ${integerPairs.joinToString()}")

            // Create indexes from integerPairs
            val indexes = convertPairsToIndexes(integerPairs)

            // Get node values from TextFields
            node1 = Node1SwapTF.text.toIntOrNull()
            node2 = Node2SwapTF.text.toIntOrNull()

            // Start the timer using nanoseconds
            val startTime = System.nanoTime()

            // Call the algorithm selection method
            handleAlgorithmSelection()

            // Stop the timer immediately after the algorithm execution
            val endTime = System.nanoTime()

            // Calculate the time taken in nanoseconds and convert to milliseconds for display
            val durationInNanoseconds = endTime - startTime
            val durationInMilliseconds = durationInNanoseconds / 1_000_000.0 // Convert to milliseconds
            val durationFormatted = String.format("%.7f", durationInMilliseconds) // Format to 7 decimal places

            // Display the time taken for the algorithm
            TimerLabel.text = "$durationFormatted ms"
            println("Time taken: $durationFormatted")
        }
    }

    private fun readFileAsPairs(file: File): List<List<Int>> {
        val pairs = mutableListOf<List<Int>>() // Initialize a list to hold the pairs

        file.bufferedReader().use { reader ->
            val content = reader.readText().trim() // Read entire file content

            // Split the content by new lines to handle each line separately
            val lines = content.split("\n").map { it.trim() }

            for (line in lines) {
                // Split by spaces and trim whitespace for each line
                val parts = line.split(" ").map { it.trim() }

                // Convert each part to an integer and add as a list to pairs
                if (parts.size == 2) { // Ensure there are exactly two parts
                    val pair = parts.map { it.toInt() }
                    pairs.add(pair) // Add the pair to the list
                }
            }
        }
        return pairs // Return the list of pairs
    }

    private fun convertPairsToIndexes(pairs: List<List<Int>>): List<List<Int>> {
        // Convert the list of pairs to the required List<List<Integer>> format
        return pairs.map { it.toList() } // Since pairs are already lists, just return them
    }

    private fun handleAlgorithmSelection() {
        // Only supporting Algorithm 1 for now
        if (radioToggleGroup.selectedToggle == RadioAlgorithm1) {
            algorithm1Method()
        } else if (radioToggleGroup.selectedToggle == RadioAlgorithm2){
            algorithm2Method()
        }
    }

    private fun showAlert(message: String) {
        val alert = Alert(AlertType.WARNING)
        alert.title = "Warning"
        alert.headerText = null
        alert.contentText = message
        alert.showAndWait()
    }

    private fun showAlertNodes(message: String) {
        val alert = Alert(AlertType.WARNING)
        alert.title = "Warning"
        alert.headerText = null
        alert.contentText = message
        alert.showAndWait()
    }

    private fun showHelpButton(message: String) {
        val alert = Alert(AlertType.INFORMATION)
        alert.title = "Help"
        alert.headerText = "--Credits & Help--"
        alert.contentText = message
        alert.showAndWait()
    }

    private fun algorithm1Method() {
        val queries: MutableList<Int?> = mutableListOf(node1, node2)

        // Draw the original state
        drawOriginalCanvas()

        // Execute the swapNodes function
        val result = Algorithm1.swapNodes(integerPairs, queries)

        // Draw the changes
        drawChangesCanvas(result)

        // Print the results
        for (inorder in result) {
            println(inorder)
        }
    }

    private fun algorithm2Method(){
        println("This works")
    }
    private fun algorithm3Method(){
        println("This works")
    }
    private fun algorithm4Method(){
        println("This works")
    }
    private fun algorithm5Method(){
        println("This works")
    }
    private fun algorithm6Method(){
        println("This works")
    }
    private fun algorithm7Method(){
        println("This works")
    }

    private fun drawOriginalCanvas() {
        // Clear the previous result
        ResultLabelAREA.text = ""

        // Build a string to display the original nodes
        val originalText = StringBuilder()

        // Example: Draw a simple representation (adjust based on your data structure)
        for ((index, pair) in integerPairs.withIndex()) {
            originalText.append("Node: ${pair.joinToString(", ")}\n")
        }

        // Set the text of ResultLabelAREA
        ResultLabelAREA.text = originalText.toString()
    }

    private fun drawChangesCanvas(result: List<List<Int>>) {
        // Clear the previous swapped results
        SwappedLabelAREA.text = ""

        // Build a string to display the swapped results
        val swappedText = StringBuilder()

        // Example: Display the results after the swap (adjust based on your data structure)
        for ((index, inorder) in result.withIndex()) {
            swappedText.append("Swapped: ${inorder.joinToString(", ")}\n")
        }

        // Set the text of SwappedLabelAREA
        SwappedLabelAREA.text = swappedText.toString()
    }


    fun setStage(stage: Stage) {
        this.stage = stage
        OpenFileButton.setOnMouseClicked { handleOpenFileButtonClicked() }
        CloseButton.setOnMouseClicked { handleCloseButtonClicked() }
        HelpButton.setOnMouseClicked { handleHelpButtonClicked() }
    }
}
