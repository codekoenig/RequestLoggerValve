package codekoenig.reqlogvalve.capture;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * Copies to an internal buffer the contents of a servlet input stream for later
 * retrieval after the stream has been read and/or closed.
 *
 * see http://www.mastertheboss.com/jboss-web/jbosswebserver/using-web-valves-with-jboss-7
 * see http://architects.dzone.com/articles/debugging-soap-logging
 * see http://javaevangelist.blogspot.com/2012/12/tomcat-7-custom-valve.html
 */
public class LoggingInputStream extends ServletInputStream {
    private static final Logger logger = Logger.getLogger(LoggingInputStream.class.getName());

    private StringBuilder sb = new StringBuilder();
    private String charsetName;
    private ByteArrayOutputStream bytes;
    private ServletInputStream is;

    public LoggingInputStream(ServletInputStream inputStream, int length, String charset) {
        super();
        logger.info("LoggingInputStream length: " + length);
        is = inputStream;
        bytes = new ByteArrayOutputStream(length);
        charsetName = (charset == null ? "UTF-8" : charset);
    }

    /*
     * Since we are not sure which method will be used just override all 4 of them:
     */
    @Override
    public int read() throws IOException {
        logger.info("LoggingInputStream.read()");
        int ch = is.read();
        if (ch != -1) {
            bytes.write(ch);
            //            logger.info("read:" + ch);
            //            logger.info("bytes.size()=" + bytes.size());
        }
        return ch;
    }

    @Override
    public int read(byte[] b) throws IOException {
        logger.info("LoggingInputStream.read(byte[] b)");
        //        logger.info("byte[].length=" + b.length);
        //        logger.info("byte[]=" + b);
        int numBytesRead = is.read(b);
        if (numBytesRead != -1) {
            for (int i = 0; i < numBytesRead; i++) {
                bytes.write(b[i]);
            }
        }
        return numBytesRead;
    }

    @Override
    public int read(byte[] b, int o, int l) throws IOException {
        logger.info("LoggingInputStream.read(byte[] b, int o, int l)");
        int numBytesRead = is.read(b, o, l);
        if (numBytesRead != -1) {
            for (int i = o; i < numBytesRead; i++) {
                bytes.write(b[i]);
            }
        }
        return numBytesRead;
    }

    @Override
    public int readLine(byte[] b, int o, int l) throws IOException {
        logger.info("LoggingInputStream.readLine(byte[] b, int o, int l)");
        int numBytesRead = is.readLine(b, o, l);
        if (numBytesRead != -1) {
            for (int i = o; i < numBytesRead; i++) {
                bytes.write(b[i]);
            }
        }
        return numBytesRead;
    }

    @Override
    public boolean isFinished() {
        logger.info("isFinished");
        try {
            return is.available() == 0;
        }
        catch (IOException ioe) {
            return false;
        }
    }

    @Override
    public boolean isReady() {
        logger.info("isReady");
        return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
        throw new RuntimeException("Not implemented");
    }

    public String getPayload() {
        logger.info("getPayload size: " + bytes.size());
        if (bytes.size() > 0) {
            try {
                sb.append(bytes.toString(charsetName));
            } catch (UnsupportedEncodingException e) {
                sb.append("Error occurred when attempting to read request body with charset '").append(charsetName).append("': ");
                sb.append(e.getMessage());
            }
        }

        logger.info("getPayload result: " + sb.toString());

        return sb.toString();
    }
}
