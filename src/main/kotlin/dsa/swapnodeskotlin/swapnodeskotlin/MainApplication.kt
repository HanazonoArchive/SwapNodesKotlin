package dsa.swapnodeskotlin.swapnodeskotlin

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.ImageView
import javafx.stage.Stage
import javafx.stage.StageStyle

class HelloApplication : Application() {

    private var xOffset: Double = 0.0
    private var yOffset: Double = 0.0

    private lateinit var stage: Stage

    override fun start(stage: Stage) {
        this.stage = stage // Assign the stage parameter to the class variable

        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("MainUI.fxml"))
        val scene = Scene(fxmlLoader.load(), 600.0, 400.0)
        this.stage.title = "Swap Nodes (Kotlin)"
        this.stage.scene = scene
        this.stage.isResizable = false
        this.stage.initStyle(StageStyle.UNDECORATED)
        this.stage.show()

        val controller: ControllerMain = fxmlLoader.getController()
        controller.setStage(this.stage)

        val resourceUrl = javaClass.getResource("/dsa/swapnodeskotlin/swapnodeskotlin/image/icon.png")
        if (resourceUrl != null){
            val logoIcons = ImageView(resourceUrl.toExternalForm())
            stage.icons.add(logoIcons.image)
        }

        scene.setOnMousePressed { event -> handleMousePressed(event) }
        scene.setOnMouseDragged { event -> handleMouseDragged(event) }

    }

    private fun handleMousePressed(event: javafx.scene.input.MouseEvent) {
        xOffset = event.screenX - stage.x
        yOffset = event.screenY - stage.y
    }

    private fun handleMouseDragged(event: javafx.scene.input.MouseEvent) {
        stage.x = event.screenX - xOffset
        stage.y = event.screenY - yOffset
    }

}

fun main() {
    Application.launch(HelloApplication::class.java)
}
