import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.solidopinion.services.CampaingTargetService;
import org.solidopinion.services.TargetService;

import java.io.IOException;
import java.util.*;

/**
 * Created by Iryna on 10/20/2016.
 */
public class CampaingTargetServiceTest {
    private TargetService target;

    @Before
    public void init(){
        target = new CampaingTargetService();
    }

    @Test(expected = IOException.class)
    public void readFileThatIsNotExists() throws Exception {
        target.read("src/test/resources/input2.txt");
    }

    /*
    @Test
    public void read_all() throws Exception {
        Date startTime = new Date();
        Set<String> expectedCampaignsForTarget_2 = new HashSet<String>(Arrays.asList("campaign_a"));
        target.read("src/test/resources/campaign.txt");
        //target.find(Arrays.asList(24489,7190,20256,5141));

        String result = target.find(Arrays.asList(3727,31097,22772,31097,31097,7176,5272));
        System.out.println(new Date().getTime()-startTime.getTime());
    }
    */



    @Test
    public void test_find_algorithm() throws Exception {
        String targets_1 = "3 4 5 10 2 200";
        String targets_4 = "4 10 15";
        String targets_5 = "1024 15 200 21 9 14 15";
        String targets_6 = "9000 29833 65000";
        String targets_7 = "sdfsd ass sss";
        target.read("src/test/resources/input.txt");

        String actualResult_1 = target.find(targets_1);
        Assert.assertEquals("campaign_a",actualResult_1);
        String actualResult_4 = target.find(targets_4);
        Assert.assertEquals("campaign_a",actualResult_4);
        String actualResult_5 = target.find(targets_5);
        Assert.assertEquals("campaign_b",actualResult_5);
        String actualResult_6 = target.find(targets_6);
        Assert.assertEquals("no campaign",actualResult_6);
        String actualResult_7 = target.find(targets_7);
        Assert.assertEquals("no campaign",actualResult_7);
    }
}
