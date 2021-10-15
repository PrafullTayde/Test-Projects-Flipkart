package base;

public interface locators {
    public String USERNAME_INPUT_LOC = "//form[@autocomplete=\"on\"]//input[@type='text']";
    public String PASSWORD_INPUT_LOC = "//form[@autocomplete=\"on\"]//input[@type='password']";
    public String SIGN_IN_INPUT_LOC = "//form[@autocomplete=\"on\"]//button[@type=\"submit\"]";

    public String USER_LOC="//div[contains(text(),'Prafull')]";
    public String LOGOUT_LOC="//div[@class=\"_3vhnxf\" and text()=\"Logout\"]";
    public String SEARCH_BOX="q";
    public String SEARCH_BOX_BUTTON="L0Z3Pu";
    public String LOW_TO_HIGH="//div[contains(text(),'Price -- Low to High')]";
    public String LIST_OF_PRODUCTS="//div[@class=\"col col-7-12\"]/div[1]";
    public String LIST_OF_PRODUCT_PRIZES="//div[@class=\"col col-5-12 nlI3QM\"]/div[1]/div[1]/div[1]";
    public String LIST_OF_RATINGS="//div[@class=\"gUuXy-\"]/span[2]/span/span[1]";
}
