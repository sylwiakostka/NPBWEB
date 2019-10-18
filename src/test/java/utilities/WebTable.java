package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTable {

    private WebElement _webTable;

    public WebTable (WebElement webTable) {
        set_webTable(webTable);
    }

    private void set_webTable(WebElement _webTable) {
        this._webTable = _webTable;
    }

    public WebElement get_webTable(){
        return _webTable;
    }

    public int getRowCount(){
        List<WebElement> tableRows = _webTable.findElements(By.tagName("tr"));
        return tableRows.size();
    }

    public int getColumntCount(){
        List<WebElement> tableRows=_webTable.findElements(By.tagName("tr"));
        WebElement headerRow = tableRows.get(0);

        List<WebElement> tableCols = headerRow.findElements(By.tagName("td"));
        return tableCols.size();
    }

    public String getCellData (int rowIdx, int colIdx) {
        List<WebElement> tableRows = _webTable.findElements(By.tagName("tr"));
        WebElement currentRow = tableRows.get(rowIdx-1);
        List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
        WebElement cell = tableCols.get(colIdx-1);
        return cell.getText();
    }


}
