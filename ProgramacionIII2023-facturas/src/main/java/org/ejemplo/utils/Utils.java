package org.ejemplo.utils;

public class Utils {
    public static Boolean validateStringNotEmptyAndNotNull (String string){
        return string == null  || string.isBlank();

    }

}
