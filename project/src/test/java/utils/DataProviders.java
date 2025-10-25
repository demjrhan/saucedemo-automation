package utils;
import org.testng.annotations.DataProvider;

public abstract class DataProviders {

    private DataProviders() {}

    @DataProvider(name = "credentials")
    public static Object[][] credentials(){
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"},
        };
    }
}
