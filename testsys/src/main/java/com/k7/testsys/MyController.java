package com.k7.testsys;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
public class MyController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/subcode")
    public String test(@RequestParam("code") String code, Model model) {

        new Thread(new Runnable() {
           @Override
            public void run() {
                System.out.println("executing.........");


                // Create .java file with ${code} content
                // Execute javac xxx.java through flowing code
               // Execute cmd cp.bat
               // The bat file content will be following
               // cd C:\\bat ' switch to work folder
               // java Test >> r3.txt  ' run java class and save result to txt file
               // read r3.txt file, and sending the content to frontend through websocket

                BufferedReader br = null;
                try {
                    Process p = Runtime.getRuntime().exec("C:\\bat\\cp.bat");
                   //Thread.sleep(500L);
                    br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = null;
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    System.out.println(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally
                {
                    if (br != null)
                    {
                        try {
                            br.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

           }
       }).start();

        model.addAttribute("msg", "Processing...");
        return "info";
    }


}
