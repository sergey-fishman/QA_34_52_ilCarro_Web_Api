package data_providers;

import dto.UserLombok;
import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class UserDataProvider {

    @DataProvider
    public Iterator<UserLombok> dataProviderWrongData() {
        return null;
    }
}
