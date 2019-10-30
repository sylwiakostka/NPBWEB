package utilities;

import org.testng.annotations.DataProvider;

public class LogUsersDataProvider {

    @DataProvider(name = "correctDataLogin")
    public Object[][] correctDataLogIn() {
        return new Object[][]{{"jolakama666@gmail.com", "123456"}};
    }


    @DataProvider(name = "incorrectDataLogin")
    public Object[][] incorrectDataLogIn() {
        return new Object[][]{
                {"", ""},
                {"jolakama666@gmail.com", "123"},
                {"jola","123456"},
                {"jolakama666@gmail.com", ""},
                {"", "123456"}};
    }

    @DataProvider(name="correctDataFromExcel")
    public Object[][] correctDataFromExcel() throws Exception{
        Object[][] testObjArray = ExcelUtils.getData("C://Users//user//Desktop//NPBWEB//src//test//java//tests//Excels//users_employes.xlsx", "users");
        return (testObjArray);
    }




//
//    @DataProvider(name = "incorrectCompanyName")
//    public Object[][] incorrectCompanyName() {
//        return new Object[][]{
//                {""},
//                {"qwe"}
//        };
//    }
//
//    @DataProvider(name = "correctCompanyName")
//    public Object[][] correctCompanyName() {
//        return new Object[][]{{"ABC"}};
//    }
//
//
//    @DataProvider (name ="combineCorrectDataLogInAndWrongCompanyName")
//    public Object [][] combineCorrectDataLogInAndWrongCompanyName (){
//        return combine (correctDataLogIn(), incorrectCompanyName());
//    }
//
//    @DataProvider (name = "combineCorrectDataLogInAndCorrectCompanyName")
//    public Object [][] combineCorrectDataLogInAndCorrectCompanyName (){
//        return combine(correctDataLogIn(), correctCompanyName());
//    }
//
//    private Object[][] combine(Object[][] objects1, Object[][] objects2) {
//        List<Object[]> objectCodesList = new LinkedList<>();
//        for(Object[] o1 : objects1){
//            for(Object[] o2 : objects2){
//                objectCodesList.add(concatAll(o1, o2));
//            }
//        }
//        return objectCodesList.toArray(new Object[0][0]);
//    }
//
//    private Object[] concatAll(Object[] o, Object[] o2) {
//        return ArrayUtils.addAll(o, o2);
//    }

}
 