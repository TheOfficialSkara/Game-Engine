package net.spencerhaney.engine;

public class ErrorCodes
{
    /**
     * The Error code that occurs when shader complilation cannot be completed successfully
     */
    public static final int SHADER_COMPILATION = 1;
    
    /**
     * The Error code that occurs when attempting to use a texture that has not been located by the Resource manager
     */
    public static final int MISSING_TEXTURE = 2;
    
    /**
     * The Error code that occurs when attempting to create a polygon with too many sides
     */
    public static final int POLYGON_SIDES_TOO_LARGE = 3;
    
    /**
     * The Error code that occurs when attempting to load a .obj file that doesn't exist
     */
    public static final int OBJ_FILE_NOT_FOUND = 4;
    
    /**
     * The Error code that occurs when constructing a face with a mismatching number of vertices and normal
     */
    public static final int VERTEX_NORMAL_MISTMATCH = 5;
    
    /**
     * The Error code that occurs when the GLFW window could not be created with OpenGL 3.2 nor OpenGL 2.1
     */
    public static final int GLFW_CREATE_WINDOW_FAIL = 6;
    
    /**
     * The Error code that occurs when the PNGDecoder cannot recognize the passed texture
     */
    public static final int INCORRECT_TEXTURE = 7;
}