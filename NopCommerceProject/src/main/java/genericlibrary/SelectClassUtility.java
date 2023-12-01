package genericlibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectClassUtility {

	public Select select;

	public void selectDropDownByValue(WebElement dropdown, String value) {
		select = new Select(dropdown);
		select.selectByValue(value);
	}

	public void selectDropDownByVisibleText(WebElement dropdown, String visibleText) {
		select = new Select(dropdown);
		select.selectByValue(visibleText);
	}

	public boolean verifyIsDropDownIsMultiSelect(WebElement dropDown) {
		select = new Select(dropDown);
		return select.isMultiple();
	}

	public void deselectAllOptionsFromDropDown(WebElement dropDown) {
		if (verifyIsDropDownIsMultiSelect(dropDown)) {
			select = new Select(dropDown);
			select.deselectAll();
		} else
			System.out.println("Can't Deselect Options from Single Select Dropdown");
	}

	public void deselectDropDownByValue(WebElement dropdown, String value) {
		select = new Select(dropdown);
		select.deselectByValue(value);
	}

	public void deselectDropDownByVisibleText(WebElement dropdown, String visibleText) {
		select = new Select(dropdown);
		select.deselectByVisibleText(visibleText);
	}

}
