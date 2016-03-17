package com.qiktone.util;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 19:14
 */
public class FileUtil
{
    private static final Log logger = LogFactory.getLog(FileUtil.class);

    public static void combineFiles(List<File> inputFiles, File outputFile)
    {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try
        {
            if (outputFile.exists()) {
                outputFile.delete();
            }
            outputFile.createNewFile();
            writer = new BufferedWriter(new FileWriter(outputFile));
            String line = null;
            int count = 0;
            for (File file : inputFiles) {
                reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null) {
                    writer.append(line);
                    writer.newLine();
                }
                count++;
                if (logger.isDebugEnabled()) {
                    logger.debug("combine file {"+Integer.valueOf(count)+"}:{"+file.getAbsolutePath()+"}");
                }
            reader.close();
            }
            writer.flush();
            if (logger.isDebugEnabled())
                logger.debug("combine {"+Integer.valueOf(count)+"} files successfully.");
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null)
                    writer.close();
            }
            catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public static void base64StringToFile(String base64String, File output)
    {
        try
        {
            byte[] bytes = Base64.decodeBase64(base64String);
            FileOutputStream fos = new FileOutputStream(output);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
