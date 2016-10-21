package org.solidopinion;

import org.solidopinion.services.CampaingTargetService;
import org.solidopinion.services.TargetService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) {

        if(args.length > 0){
            String pathToDir = args[0];

            TargetService targetService = new CampaingTargetService();
            try {
                targetService.read(pathToDir);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                while (true) {
                    System.out.print("-> : ");
                    String targetLine = br.readLine();
                    System.out.println("<-:" +targetService.find(targetLine));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Please type as argument 'pathToFile'");
        }
    }
}
