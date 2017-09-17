package db;

import java.util.ArrayList;
import java.util.List;

public class TableHelper {


    /*
     * insert items from a row into each column
     */
    static void expandItem(List<Column> cols, Row r){
        for(int i = 0; i < cols.size(); i++){
            cols.get(i).add(r.items.get(i));
        }

    }

    /*
     * insert items from a col into each row
     */
    static void expandItem(List<Row> rows, Column col){
        for(int i = 0; i < rows.size(); i++){
            rows.get(i).add(col.items.get(i));
        }

    }

    /*
     *  Pair Class of Column
     */
    static class ColumnPair{
        private String nameOfPair;
        Column col1;
        Column col2;

        ColumnPair(Column c1, Column c2){
            col1 = c1;
            col2 = c2;
            nameOfPair = c1.name;
        }
    }

    /*
     * Pairs class: store the column pairs with same name
     */
    static class SameNamePairs{
        int RepeatedNum = 0;
        List<ColumnPair> repeatedNameColPair;

        SameNamePairs(Table t1, Table t2){
            repeatedNameColPair = new ArrayList<>();
            List<String> t1Names = t1.columnNames;
            List<String> t2Names = t2.columnNames;
            List<Column> cols1 = t1.columns;
            List<Column> cols2 = t2.columns;
            for(int i1 =0; i1 < t1.columnsNum(); i1+=1){
                for(int i2 =0; i2 < t2.columnsNum(); i2+=1){
                    if(cols1.get(i1).name==cols2.get(i2).name){
                        ColumnPair colPair = new ColumnPair(cols1.get(i1),cols2.get(i2));
                        repeatedNameColPair.add(colPair);
                        RepeatedNum++;
                    }
                }
            }
        }
    }

    /*
     * convert rows to columns
     */
    static List<Column> rowsToColumns(List<Row> rows, List<String> colNames){
        List<Column> cols = new ArrayList<>();
        for(int colIndex = 0; colIndex < colNames.size(); colIndex++){
            String colType = rows.get(0).items.get(0).getClass().toString();
            Column currCol = new Column(colNames.get(colIndex),colType);
            try {
                for (Row r : rows) {
                    currCol.add(r.items.get(colIndex));
                }
            }catch(Exception e) {
                System.out.println("the item type from rows doesn't mach type of column");
            }
            cols.add(currCol);
        }
        return cols;
    }
}
