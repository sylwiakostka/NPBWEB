package utilities;

import org.testng.annotations.DataProvider;

public class ManageDataProvider {

    @DataProvider(name="correctDataToAddMPK")
    public Object[][] correctDataMPK() throws Exception{
        Object[][] testObjArray = ExcelUtils.getData("C://Users//user//Desktop//NPB//src//test//java//NPB//tests//Excels//manage.xlsx", "MPK_correctData");
        return (testObjArray);
    }

    @DataProvider(name="correctDataToAddProfiles")
    public Object[][] correctDataProfiles() throws Exception{
        Object[][] testObjArray = ExcelUtils.getData("C://Users//user//Desktop//NPB//src//test//java//NPB//tests//Excels//manage.xlsx", "Profile_correctData");
        return (testObjArray);
    }
}
