package dsa.swapnodeskotlin.swapnodeskotlin

// Importing Algorithm
import dsa.swapnodesjava.swapnodesjava.Algorithm1
import dsa.swapnodesjava.swapnodesjava.BufferedReader
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Label
import javafx.scene.control.RadioButton
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.io.FileReader
import kotlin.system.exitProcess

class ControllerMain {

    // Algorithm Instance
    private lateinit var algorithm1: Algorithm1
    private lateinit var bufferedreader: BufferedReader

    private var startTime: Long = 0
    private var endTime: Long = 0

    // Writer
    lateinit var queries: MutableList<Int?>
    lateinit var result: List<List<Int>>

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

    @FXML
    private lateinit var ManualInputTextArea: TextArea

    @FXML
    private lateinit var ManualButtonSubmit: Pane

    var node1: Int? = null
    var node2: Int? = null

    // Algorithm Radio Button
    @FXML
    lateinit var RadioAlgorithm1: RadioButton

    private lateinit var stage: Stage

    // Common Declaration
    private var integerPairs: List<List<Int>> = emptyList()

    @FXML
    private fun initialize() {
        // Initial setup if needed
    }

    @FXML
    private fun handleCloseButtonClicked() {
        exitProcess(0)
    }

    @FXML
    private fun handleHelpButtonClicked() {
        showHelpMessage("UI - Agsoy\nBackend - Dingal\nImprovements - Pechayco & Palma\n\n - To Make a Text File\nText File must be composed of Integers.")
    }



    private fun readFileAsPairs(file: File): List<List<Int>> {
        val pairs = mutableListOf<List<Int>>()

        val bufferedReader = java.io.BufferedReader(FileReader(file))

        val javaPairs: List<IntArray> = BufferedReader().readAndParseFile(bufferedReader)

        for (pair in javaPairs) {
            pairs.add(pair.toList())
        }

        return pairs
    }

    private fun algorithm1Method() {
        queries = mutableListOf(node1, node2)
        result = Algorithm1.swapNodes(integerPairs, queries)
        result.forEach { println(it) }
    }

    private fun showAlert(message: String) {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Warning"
        alert.contentText = message
        alert.showAndWait()
    }

    private fun showAlertAlgorithm(message: String) {
        val alert = Alert(Alert.AlertType.WARNING)
        alert.title = "Warning"
        alert.contentText = message
        alert.showAndWait()
    }

    private fun showHelpMessage(message: String) {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Help"
        alert.headerText = "--Credits & Help--"
        alert.contentText = message
        alert.showAndWait()
    }

    private fun drawOriginalCanvas() {
        ResultLabelAREA.text = ""

        val originalText = StringBuilder()

        integerPairs.forEach { pair ->
            originalText.append("Node: ${pair.joinToString(", ")}\n")
        }

        ResultLabelAREA.text = originalText.toString()
    }

    private fun drawChangesCanvas(result: List<List<Int>>) {
        SwappedLabelAREA.text = ""

        val swappedText = StringBuilder()

        result.forEach { inorder ->
            swappedText.append("Swapped: ${inorder.joinToString(", ")}\n")
        }

        SwappedLabelAREA.text = swappedText.toString()
    }

    private fun handleManualInputButtonClicked() {
        if (Node1SwapTF.text.isEmpty() && Node2SwapTF.text.isEmpty()) {
            showAlert("Please input the Swapping Nodes.")
            return
        }

        if (RadioAlgorithm1.isSelected){
            println("Algorithm 1 Selected")
        } else {
            showAlertAlgorithm("Please select the algorithm.")
            return
        }
        startTime = System.nanoTime()
        val manualInput = ManualInputTextArea.text
        if (manualInput.isNotEmpty()) {
            val tempFile = File.createTempFile("manualInput", ".txt")
            tempFile.writeText(manualInput)

            node1 = Node1SwapTF.text.toIntOrNull()
            node2 = Node2SwapTF.text.toIntOrNull()

            integerPairs = readFileAsPairs(tempFile)
            endTime = System.nanoTime()

            tempFile.deleteOnExit()

            algorithm1Method()
            drawOriginalCanvas()
            drawChangesCanvas(result)

            val durationInMilliseconds = (endTime - startTime) / 1_000_000.0
            TimerLabel.text = String.format("%.7f ms", durationInMilliseconds)
            println("Time taken: $durationInMilliseconds ms")
        } else {
            showAlert("Please enter some input.")
        }



    }

    @FXML
    private fun handleOpenFileButtonClicked() {
        if (Node1SwapTF.text.isEmpty() && Node2SwapTF.text.isEmpty()) {
            showAlert("Please input the Swapping Nodes.")
            return
        }

        if (RadioAlgorithm1.isSelected){
            println("Algorithm 1 Selected")
        } else {
            showAlertAlgorithm("Please select the algorithm.")
            return
        }

        val fileChooser = FileChooser()
        fileChooser.title = "Open a Text File"
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Text Files", "*.txt"))

        val desktopPath = System.getProperty("user.home") + "/Desktop"
        fileChooser.initialDirectory = File(desktopPath)

        val selectedFile = fileChooser.showOpenDialog(stage)
        if (selectedFile != null) {
            FileNameLabel.text = selectedFile.name
            println("Selected file: ${selectedFile.absolutePath}")

            node1 = Node1SwapTF.text.toIntOrNull()
            node2 = Node2SwapTF.text.toIntOrNull()

            startTime = System.nanoTime()
            integerPairs = readFileAsPairs(selectedFile)
            endTime = System.nanoTime()

            algorithm1Method()

            drawOriginalCanvas()
            drawChangesCanvas(result)

            val durationInMilliseconds = (endTime - startTime) / 1_000_000.0
            TimerLabel.text = String.format("%.7f ms", durationInMilliseconds)
            println("Time taken: $durationInMilliseconds ms")
        }
    }

    fun setStage(stage: Stage) {
        this.stage = stage
        OpenFileButton.setOnMouseClicked { handleOpenFileButtonClicked() }
        CloseButton.setOnMouseClicked { handleCloseButtonClicked() }
        HelpButton.setOnMouseClicked { handleHelpButtonClicked() }
        ManualButtonSubmit.setOnMouseClicked { handleManualInputButtonClicked() }
    }
}
