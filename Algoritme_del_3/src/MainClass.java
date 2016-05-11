/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bamz
 */
public class MainClass {
    public static void main(String[] args) throws Exception {
        Encode.main(new String[]{"Dep.png", "coded"});
        Decode.main(new String[]{"coded", "Dep2.png"});
    }
}
