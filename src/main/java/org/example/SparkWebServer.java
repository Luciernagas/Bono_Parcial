package org.example;

import static spark.Spark.*;

public class SparkWebServer {
    public static void main(String... args){
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
        get("/sin/:numero", (req,res) -> {
            double numero = Double.parseDouble(req.params("numero"));
            double resultado = Math.sin(numero);
            return "El seno de " + numero + " es " + resultado;
        });
        get("/cos/:numero", (req,res) -> {
            double numero = Double.parseDouble(req.params("numero"));
            double resultado = Math.cos(numero);
            return "El cos de " + numero + " es " + resultado;
        });
        get("/palindromo/:palabra", (req,res) -> {
            String palabra = req.params("palabra");
            Boolean respuesta = true;
            for(int i = 0; i < palabra.length()/2; i++){
                if(palabra.charAt(i) == palabra.charAt(palabra.length() - (i) - 1)){
                    respuesta = true;
                }
                respuesta = false;
            }
            if(respuesta == true){
                return palabra + " si es un palíndromo";
            }
            return palabra + " no es un palíndromo";
        });
        get("/vector/:parametro1/:paramero2", (req,res) -> {
            double parametro1 = Double.parseDouble(req.params("parametro1"));
            double parametro2 = Double.parseDouble(req.params("parametro2"));
            double magnitud = Math.sqrt(Math.pow(parametro1, 2) + Math.pow(parametro2, 2));
            return magnitud;
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
