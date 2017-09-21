package db;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static db.Database.strToObj;
public class ConditionSelect {
    private static final Pattern largerAndEqual_CMD = Pattern.compile("(\\.+)>=(\\.+)");
    private static final Pattern smallerAndEqual_CMD = Pattern.compile("(\\.+)<=(\\.+)");
    private static final Pattern equal_CMD = Pattern.compile("\\.+==\\.+");
    private static final Pattern unequal_CMD = Pattern.compile("\\.+!=\\.+");
    private static final Pattern larger_CMD = Pattern.compile("([^\\s]+)\\s*>\\s*([^\\s]+)");
    private static final Pattern smaller_CMD = Pattern.compile("\\.+<\\.+");

    static List<Column> conditionEval(List<Column> cols, String[] conds) throws Exception{
        if(conds == null){
            return cols;
        }
        for(String cond : conds){
            Matcher m;
            String colName;
            Comparable compareObj;
            if((m=larger_CMD.matcher(cond)).matches()){
                colName = m.group(1);
                compareObj = strToObj(m.group(2));
                Boolean matchName = false;
                List<Integer> removeItemIndex = new ArrayList<>();
                for(Column c : cols){
                    if(c.name.equals(colName)){  // target column
                        matchName = true;
                        for(int i = 0; i< c.items.size(); i++){
                            if(compareObj.compareTo(c.items.get(i))>0){
                                removeItemIndex.add(i);
                            }
                        }
                    }
                }
                if (matchName == false){
                    throw new Exception(String.format("%s doesn't exist",colName));
                }
                for(int colIndex = 0; colIndex<cols.size();colIndex++) {
                    Column c = cols.get(colIndex);
                    Column newCol = new Column(c.name,c.type);
                    for (int i = 0; i < c.size; i++) {
                        if (removeItemIndex.contains(i)==false){
                            newCol.add((Comparable)c.items.get(i));
                        }
                    }
                    cols.set(colIndex,newCol);
                }
            }
        }
        return cols;
    }

    static List<Column> conditionMatch(String cond, String colName, Comparable compareObj){
        Boolean matchName = false;
        if(cond.equals("larger")){

        }
    }
}
