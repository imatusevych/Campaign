package org.solidopinion.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CampaingTargetService implements TargetService {
    private final Integer INDEX_CAMPAING_NAME = 0;
    private final String TARGET_SEPARATOR = " ";
    private Map<Integer, Set<String>> targetsCampaigns = new HashMap<Integer, Set<String>>();

    public void read(String path) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String targetsLine = null;
        while ((targetsLine = br.readLine()) != null) {
            String[] targetsItems = targetsLine.trim().split(TARGET_SEPARATOR);
            for (int i = 1; i < targetsItems.length; i++) {
                if (targetsCampaigns.containsKey(new Integer(targetsItems[i]))) {
                    targetsCampaigns.get(new Integer(targetsItems[i])).add(targetsItems[INDEX_CAMPAING_NAME]);
                } else {
                    targetsCampaigns.put(new Integer(targetsItems[i]), new HashSet<String>(Arrays.asList(targetsItems[INDEX_CAMPAING_NAME])));
                }
            }
        }
        br.close();
    }

    public String find(String targetsLine) {
        SortedSet<Map.Entry<String, Integer>> sortedEntries = new TreeSet<Map.Entry<String, Integer>>(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                int res = e1.getValue().compareTo(e2.getValue());
                return res != 0 ? res : 1;
            }
        }
        );
        sortedEntries.addAll(buildCampaignsCandidates(buildTargetsList(targetsLine)).entrySet());
        return sortedEntries.size() == 0 ? "no campaign" : sortedEntries.last().getKey();
    }

    private Map<String, Integer> buildCampaignsCandidates(List<Integer> targets) {

        Map<String, Integer> companyRange = new HashMap<String, Integer>();

        for (Integer target : targets) {
            if (targetsCampaigns.containsKey(target)) {
                for (String campaign : targetsCampaigns.get(target)) {
                    if (companyRange.containsKey(campaign)) {
                        companyRange.put(campaign, companyRange.get(campaign) + 1);
                    } else {
                        companyRange.put(campaign, 1);
                    }
                }
            }
        }

        return companyRange;
    }

    private List<Integer> buildTargetsList(String targetsLine) {
        List<Integer> targets = new ArrayList<Integer>();
        String[] targetsItems = targetsLine.trim().split(TARGET_SEPARATOR);
        for (int i = 0; i < targetsItems.length; i++) {
            try {
                targets.add(Integer.parseInt(targetsItems[i]));
            } catch (NumberFormatException e) {
                System.out.println("not valid target:" + targetsItems[i]);
            }
        }
        return targets;
    }

}
