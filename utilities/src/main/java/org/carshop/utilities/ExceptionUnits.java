package org.carshop.utilities;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUnits {

    public static String getMsg(Exception ex) {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
