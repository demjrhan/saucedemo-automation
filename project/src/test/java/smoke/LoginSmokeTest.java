package smoke;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSmokeTest extends BaseTest {


    @Test
    public void testLogin() {
        var result = loginPage.open().loginHappyPath();
    }
}
