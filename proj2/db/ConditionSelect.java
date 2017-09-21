package db;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static db.Database.strToObj;
public class ConditionSelect {
    private static final Pattern largerAndEqual_CMD = Pattern.compile("([^\\s]+)\\s*>=\\s*([^\\s]+)");
    private static final Pattern smallerAndEqual_CMD = Pattern.compile("([^\\s]+)\\s*<=\\s*([^\\s]+)");
    private static final Pattern equal_CMD = Pattern.compile("([^\\s]+)\\s*==\\s*([^\\s]+)");
    private static final Pattern unequal_CMD = Pattern.compile("([^\\s]+)\\s*!=\\s*([^\\s]+)");
    private static final Pattern larger_CMD = Pattern.compile("([^\\s]+)\\s*>\\s*([^\\s]+)");
    private static final Pattern smaller_CMD = Pattern.compile("([^\\s]+)\\s*<\\s*([^\\s]+)");

    static List<Column> conditionEval(List<Column> cols, String[] conds) throws Exception{
        if(conds == null){
            return cols;
        }
        for(String cond : conds){
            Matcher m;
            if((m=largerAndEqual_CMD.matcher(cond)).matches()){ // condition operation: >=
                cols = conditionMatch("largerAndEqual",m.group(1),strToObj(m.group(2)),cols);
            } else if((m=smallerAndEqual_CMD.matcher(cond)).matches()){ // condition operation: >=
                cols = conditionMatch("smallerAndEqual",m.group(1),strToObj(m.group(2)),cols);
            } else if((m=equal_CMD.matcher(cond)).matches()){  // condition operation: ==
                cols = conditionMatch("equal",m.group(1),strToObj(m.group(2)),cols);
            } else if((m=unequal_CMD.matcher(cond)).matches()){ // condition operation: !=
                cols = conditionMatch("unequal",m.group(1),strToObj(m.group(2)),cols);
            } else if((m=larger_CMD.matcher(cond)).matches()){ // condition operation: >
                cols = conditionMatch("larger",m.group(1),strToObj(m.group(2)),cols);
            } else if((m=smaller_CMD.matcher(cond)).matches()){ // condition operation: <
                cols = conditionMatch("smaller",m.group(1),strToObj(m.group(2)),cols);
            } else {
                throw new Exception("condition Error");
            }
        }
        return cols;
    }

    static List<Column> conditionMatch(String cond, String colName, Comparable compareObj, List<Column> cols)
            throws Exception {
        Boolean matchName = false;
        List<Integer> removeItemIndex = new ArrayList<>();
        for (Column c : cols) {
            if (c.name.equals(colName)) {  // find target column
                matchName = true;
                for (int i = 0; i < c.items.size(); i++) {
                    Comparable com2 = (Comparable) c.items.get(i);
                    int value = (com2.compareTo(compareObj));
                    if (cond.equals("largerAndEqual")) {
                        if  (value < 0) {
                            removeItemIndex.add(i);
                        }
                    } else if (cond.equals("smallerAndEqual")) {
                        if (value > 0) {
                            removeItemIndex.add(i);
                        }
                    } else if (cond.equals("equal")) {
                        if (value != 0) {
                            removeItemIndex.add(i);
                        }
                    } else if (cond.equals("unequal")) {
                        if (value == 0) {
                            removeItemIndex.add(i);
                        }
                    } else if (cond.equals("larger")) {
                        if (value <= 0) {
                            removeItemIndex.add(i);
                        }
                    } else if (cond.equals("smaller")) {
                        if (value >= 0) {
                            removeItemIndex.add(i);
                        }
                    } else{
                        throw new Exception(String.format("undefined condition: %s", cond));
                    }
                }
            }
        }
        if (matchName == false) {
            throw new Exception(String.format("%s doesn't exist", colName));
        }
        for (int colIndex = 0; colIndex < cols.size(); colIndex++) {
            Column c = cols.get(colIndex);
            Column newCol = new Column(c.name, c.type);
            for (int i = 0; i < c.size; i++) {
                if (removeItemIndex.contains(i) == false) {
                    newCol.add((Comparable) c.items.get(i));
                }
            }
            cols.set(colIndex, newCol);
        }

        return cols;
    }
}
