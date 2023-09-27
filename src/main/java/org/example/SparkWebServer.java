package org.example;

import static spark.Spark.*;

public class SparkWebServer {
    public static void main(String... args){
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
        get("/", (req, res) -> getIndexResponse());
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
                if(palabra.charAt(i) != palabra.charAt(palabra.length() - (i) - 1)){
                    respuesta = false;
                    break;
                }
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
            return "La magnitud es" + magnitud;
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    public static String getIndexResponse() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>SparkWebApp</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        form {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        label {\n" +
                "            display: block;\n" +
                "            margin-bottom: 5px;\n" +
                "        }\n" +
                "        input[type='number'] {\n" +
                "            width: 100%;\n" +
                "            padding: 8px;\n" +
                "            margin-bottom: 10px;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "        button {\n" +
                "            padding: 10px 20px;\n" +
                "            background-color: pink;\n" +
                "            color: #fff;\n" +
                "            border: none;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        .result {\n" +
                "            margin-top: 10px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>BONO PARCIAL</h1>\n" +
                "<form id=\"sinForm\">\n" +
                "    <label for=\"sinInput\">Calcular Seno:</label>\n" +
                "    <input type=\"number\" id=\"sinInput\" step=\"any\" required>\n" +
                "    <button type=\"button\" onclick=\"calculateSin()\">Calcular</button>\n" +
                "    <div class=\"result\" id=\"sinResult\"></div>\n" +
                "</form>\n" +
                "\n" +
                "<form id=\"cosForm\">\n" +
                "    <label for=\"cosInput\">Calcular Coseno:</label>\n" +
                "    <input type=\"number\" id=\"cosInput\" step=\"any\" required>\n" +
                "    <button type=\"button\" onclick=\"calculateCos()\">Calcular</button>\n" +
                "    <div class=\"result\" id=\"cosResult\"></div>\n" +
                "</form>\n" +
                "\n" +
                "<form id=\"palindromeForm\">\n" +
                "    <label for=\"palindromeInput\">Verificar Palíndromo:</label>\n" +
                "    <input type=\"text\" id=\"palindromeInput\" required>\n" +
                "    <button type=\"button\" onclick=\"checkPalindrome()\">Verificar</button>\n" +
                "    <div class=\"result\" id=\"palindromeResult\"></div>\n" +
                "</form>\n" +
                "\n" +
                "<form id=\"magnitudeForm\">\n" +
                "    <label for=\"xInput\">Valor X:</label>\n" +
                "    <input type=\"number\" id=\"xInput\" step=\"any\" required>\n" +
                "    <label for=\"yInput\">Valor Y:</label>\n" +
                "    <input type=\"number\" id=\"yInput\" step=\"any\" required>\n" +
                "    <button type=\"button\" onclick=\"calculateMagnitude()\">Calcular Magnitud</button>\n" +
                "    <div class=\"result\" id=\"magnitudeResult\"></div>\n" +
                "</form>\n" +
                "\n" +
                "<script>\n" +
                "    function calculateSin() {\n" +
                "        const input = document.getElementById(\"sinInput\").value;\n" +
                "        fetch(`/sin/${input}`)\n" +
                "            .then(response => response.text())\n" +
                "            .then(result => {\n" +
                "                document.getElementById(\"sinResult\").textContent = `Sin(${input}) = ${result}`;\n" +
                "            });\n" +
                "    }\n" +
                "\n" +
                "    function calculateCos() {\n" +
                "        const input = document.getElementById(\"cosInput\").value;\n" +
                "        fetch(`/cos/${input}`)\n" +
                "            .then(response => response.text())\n" +
                "            .then(result => {\n" +
                "                document.getElementById(\"cosResult\").textContent = `Cos(${input}) = ${result}`;\n" +
                "            });\n" +
                "    }\n" +
                "\n" +
                "    function checkPalindrome() {\n" +
                "        const input = document.getElementById(\"palindromeInput\").value;\n" +
                "        fetch(`/palindromo/${input}`)\n" +
                "            .then(response => response.text())\n" +
                "            .then(result => {\n" +
                "                document.getElementById(\"palindromeResult\").textContent = result;\n" +
                "            });\n" +
                "    }\n" +
                "\n" +
                "    function calculateMagnitude() {\n" +
                "        const x = document.getElementById(\"xInput\").value;\n" +
                "        const y = document.getElementById(\"yInput\").value;\n" +
                "        fetch(`/vector/${x}/${y}`)\n" +
                "            .then(response => response.text())\n" +
                "            .then(result => {\n" +
                "                document.getElementById(\"magnitudeResult\").textContent = `Magnitud: ${result}`;\n" +
                "            });\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n";
    }
    
}