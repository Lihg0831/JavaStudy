import java.util.ArrayList;
import java.util.List;

public class back {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder str=new StringBuilder();
        backtrack(res,str,0,0,n);
        return res;
    }
    private void backtrack(List<String> res,StringBuilder str,int left,int right,int n){
        if(str.length()==2*n){
            res.add(str.toString());
            return;
        }
        if(left<n){
            str.append("(");
            backtrack(res,str,left+1,right,n);
            str.setLength(str.length()-1);
        }
        if(right<left){
            str.append(")");
            backtrack(res,str,left,right+1,n);
            str.setLength(str.length()-1);
        }
    }

}
