import java.util.Scanner;
import java.util.Stack;
// 
public class App {
    public static void main(String[] args) throws Exception {
        Scanner prm=new Scanner(System.in);
        System.out.println("진행하시겠습니까? (y/n)");
        String ys=prm.nextLine();
       
            String address=null;
            
            System.out.println("input");
            address=prm.nextLine();
            String[] arr=address.split(" ");
            
            String opcode,p1,p2,p3;//write a 0111 0001
        
            opcode=arr[0];
            p1=arr[1];
            p2=arr[2];
            p3=arr[3];
            Stack<String> stack = new Stack<String>();
            if(opcode=="Write")
            {
                //stack p3 값을 넣습니다
                stack.push(p3);
                String result1="0011"+" "+p3;
                String[] memory_address=new String[16];
                int i= Integer.parseInt(p2,2);
                //메모리 주소에 스택값 넣습니다.
                memory_address[i]=stack.pop();
                String result2="1000"+" "+p2;
                String result3="0101";
                while(!stack.empty()){
                }
                System.out.println(result1+""+result2+""+result3);
            }
        
        prm.close();
    }
}
