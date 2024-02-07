package Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.*;
import main.ConnectFour;

public class winDetectionTests {
    public static void main(String[] args) {
        
    }
    
    @Test
    public void horizontalHvHWinTest(){
        String result = "";
        String[] outputArr = {"1\n","1\n","1\n","0\n","0\n","1\n","1\n","2\n","2\n","3\n"};
        
        for (String i : outputArr) {
            System.setIn(new java.io.ByteArrayInputStream(i.getBytes()));
            
            try{
                wait(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            result = read.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);


        ConnectFour game = new ConnectFour();
    } 
    
}
