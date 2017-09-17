package db;


import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import static db.Parse.eval;


public class Database {
    private static final String INTEGER = "\\d+";
    private static final String STRING = "\'\\.*\'";
    private static final String FLOAT = "^([+-]?\\d*\\.\\d*)$";
    private static final Pattern INTEGER_TYPE = Pattern.compile(INTEGER);
    private static final Pattern STRING_TYPE = Pattern.compile(STRING);
    private static final Pattern FLOAT_TYPE = Pattern.compile(FLOAT);
    private static Map<String,Table> tableMap = new HashMap<>();

    public Database() {
        // YOUR CODE HERE
    }

    public String transact(String query) {
        String result = eval(query);
        return result;
    }

    static Table createNewTable(String name, String[] colsString) throws Exception {
        List<Column> cols = new ArrayList<>();
        for(String str : colsString){
            String[] args = str.split("\\s+");
            Column col;
            int argsLen = args.length;
            if (argsLen!=2){
                throw new Exception(String.format(
                        "Wrong arguments number for %s, except 2, get %d",args[0],argsLen));
            } else{
                String colName = args[0];
                String colType = args[1];
                if(colType.equals("integer")||colType.equals("int")){
                    col = new Column<Integer>(colName,colType);
                } else if(colType.equals("string")){
                    col = new Column<String>(colName, colType);
                } else if(colType.equals("float")){
                    col = new Column<Float>(colName, colType);
                } else {
                    throw new Exception(String.format(String.format("unknown class type: %s", colType)));
                }
            }
            cols.add(col);
        }
        Table newTable = new Table(name, cols);
        tableMap.put(name,newTable);
        return newTable;
    }
    /*
     * convert string to correspond type object
     */
    private static Object strToObj(String str){
        if(INTEGER_TYPE.matcher(str).matches()){
            return Integer.parseInt(str);
        } else if(FLOAT_TYPE.matcher(str).matches()){
            return Float.parseFloat(str);
        } else{
            return str;
        }
    }

    static String loadTable(String name) {
        BufferedReader br;
        try{
            br  = new BufferedReader(new FileReader("out/production/proj2/examples//"+name+".tbl"));
            String line;
            line = br.readLine();
            String[] colsStr = line.split(",");
            Table newTable;
            try {
                newTable = createNewTable(name, colsStr); // read first line and create table
            } catch (Exception e){
                return ""+e;
            }
            while((line = br.readLine()) != null){
                Row r = new Row();
                String[] rowItemsStr = line.split(",");
                for(String itemStr : rowItemsStr){
                    r.add(strToObj(itemStr));
                }
                newTable.addRow(r);
            }
            return "";
        } catch(IOException e){
            return String.format("ERROR: TBL file not found: %s.tbl", name);
        }
    }

    static String printTable(String name){
        if(tableMap.containsKey(name)){
            return tableMap.get(name).toString();
        } else {
            return String.format("didn't create or load table: %s",name);
        }
    }


}

