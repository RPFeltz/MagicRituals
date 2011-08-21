package rpfeltz.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** SLAPI: Saving/Loading API
 * API for Saving and Loading Objects.
 * @author Tomsik68
 */
public class SLAPI
{
    /**
     * Write (save) an object to a file.
     * @param object Object to save.
     * @param path Path of the file.
     * @throws IOException
     */
    public static void save(Object object, String path) throws IOException
	{
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
		objectOutputStream.close();
	}
        /**
         * Load an object from a file.
         * @param path Path of the file.
         * @return Object that was loaded.
         * @throws IOException 
         * @throws ClassNotFoundException
         */
        public static Object load(String path) throws IOException, ClassNotFoundException
	{
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
		Object result = objectInputStream.readObject();
		objectInputStream.close();
		return result;
	}
}
