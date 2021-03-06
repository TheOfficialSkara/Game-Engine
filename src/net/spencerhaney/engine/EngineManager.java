package net.spencerhaney.engine;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

import java.io.File;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;

import net.spencerhaney.game.MyGame;
import net.spencerhaney.opengl.Camera;
import net.spencerhaney.opengl.GLUtil;

public class EngineManager
{
    private static GLFWErrorCallback errorCallback;
    private static ScreenManager screen;
    private static Game game;
    
    public static void run(Game game)
    {
        EngineManager.game = game;
        try
        {
            init();
            loop();
        }
        finally
        {
            cleanup();
        }
    }

    private static void init()
    {
        Logging.init();
        Logging.info("Initiating engine.");

        Time.init();

        // Setup error callback. Print to System.err
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));

        // Initialize GLFW
        if (glfwInit() != GL11.GL_TRUE)
        {
            IllegalStateException e = new IllegalStateException("Unable to initialize GLFW");
            Logging.severe(e);
            throw e;
        }

        screen = new ScreenManager();
        screen.createWindow(game.getTitle(), 1920, 1080);
        GLFW.glfwSetCursorPos(screen.getWindow(), 1920 / 2, 1080 / 2); //TODO remove hardcoding
        Camera.init((float)1920 / 1080);
        screen.show();
        game.init();
    }

    private static void loop()
    {
        Logging.info("Starting engine.");
        while (screen.isOpen())
        {
            screen.update();
            Time.update();
            game.gameUpdate();
            game.gameRender();
            Camera.fpsCamera(screen.getWindow());
        }
    }

    private static void cleanup()
    {
        Logging.info("Stopping engine.");
        game.cleanup();
        GLUtil.cleanup();
        glfwTerminate();
        if (errorCallback != null)
        {
            errorCallback.release();
        }
    }

    public static void errorStop(int errorCode)
    {
        cleanup();
        System.exit(errorCode);
    }

    public static void main(String[] args)
    {
        System.setProperty("org.lwjgl.librarypath", new File("lib/native").getAbsolutePath());
        Game game = new MyGame();
        EngineManager.run(game);
    }
}