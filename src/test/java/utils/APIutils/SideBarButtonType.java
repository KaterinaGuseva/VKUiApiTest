package utils.APIutils;

public enum SideBarButtonType {

    SIDE_BAR_BUTTON_MY_PROFILE("l_pr"),
    SIDE_BAR_BUTTON_NAME("My Page");
    private String typeParameterSideBar;

    SideBarButtonType(String typeParameterSideBar) {
        this.typeParameterSideBar = typeParameterSideBar;
    }

    public String getTypeParameterSideBar() {
        return typeParameterSideBar;
    }
}
