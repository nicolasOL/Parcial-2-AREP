/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
package edu.escuelaing.arem.parcial.parcialWebServer;
import com.google.gson.Gson;
import edu.escuelaing.arem.parcial.model.Answer;
import edu.escuelaing.arem.parcial.model.BadAnswer;
import edu.escuelaing.arem.parcial.model.Operation;
import static spark.Spark.*;
/**
 *
 * @author Nicolás
 */
public class ParcialWebServer {
    public static void main(String... args){
        port(getPort());
        get("/asin", (req,res) -> {
            BadAnswer badAns =new BadAnswer();
            badAns.setBadAnswer("El valor debe estar entre 1 y -1");
            String respu= new Gson().toJson(badAns);
            Double par= Double.parseDouble(req.queryMap().get("value").value());
            if (Math.abs(par)<=1){
                Double resp= calculateAsin(par);
                Answer ans =new Answer();
                ans.setOperation(Operation.asin.name());
                ans.setInput(par);
                ans.setOutput(resp);
                System.out.println(resp);
                respu = new Gson().toJson(ans);
            }         
            return respu;
        });
        get("/exp", (req,res) -> {
            Double par= Double.parseDouble(req.queryMap().get("value").value());
            Double resp= calculateExp(par);
            Answer ans =new Answer();
            ans.setOperation(Operation.exp.name());
            ans.setInput(par);
            ans.setOutput(resp);
            System.out.println(par);
            return new Gson().toJson(ans);
        });
    }
    /** Permite retornar el puerto que por defecto asigna el entorno.
     * @return el puerto asignado por el entorno.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    /** Permite retornar el puerto que por defecto asigna el entorno.
     * @return el puerto asignado por el entorno.
     */
    private static Double calculateAsin(Double par){
        return Math.asin(par);
    }
    /** Permite retornar el puerto que por defecto asigna el entorno.
     * @return el puerto asignado por el entorno.
     */
    private static Double calculateExp(Double par){
        return Math.exp(par);
    }
}
