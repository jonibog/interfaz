package org.ejemplo.validations.refactor;
import tu.paquete.util.StringUtil;
public class util {

 
        public static void main(String[] args) {
            String input = "Hola, mundo!";
            Object StringUtil;
            if (StringUtil.validateStringNotEmptyNotNull(input)) {
                System.out.println("La cadena no está vacía ni es nula.");
            } else {
                System.out.println("La cadena está vacía o es nula.");
            }
        }
    }

