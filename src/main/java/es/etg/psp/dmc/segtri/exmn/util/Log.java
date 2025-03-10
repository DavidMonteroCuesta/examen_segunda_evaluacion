package es.etg.psp.dmc.segtri.exmn.util;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public interface Log {
    public static final String EXTENSION = "_.log";

    static void log(String info, String name) throws Exception{
        Logger logger = Logger.getLogger(name);
        FileHandler fh = new FileHandler(name + EXTENSION, true);
        logger.addHandler(fh);
        fh.setFormatter(new SimpleFormatter());
        logger.log(Level.INFO, info);
    }
}
