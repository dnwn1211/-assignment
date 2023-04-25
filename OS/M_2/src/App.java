import java.util.Scanner;
import java.util.Stack;
// 
public class App {
	static String[] memory_address=new String[16];
	static String DR;
	static String[][] m_A= {
			{"null","null"},
			{"null","null"},
			{"null","null"},
			{"null","null"},
			{"null","null"},
	};
	public static void memory(String a,String v) {
		for(int i=0;i<5;i++)
		{
			if(m_A[i][0].equals("null"))
			{
				m_A[i][0]=a;
				m_A[i][1]=v;
				break;
			}
		}
	}
	public static String find(String a) {
		for(int i=0;i<5;i++)
		{
			if(m_A[i][0].equals(a))
			{
				return m_A[i][1];
			}
		}
		return "not";
	}
    public static void main(String[] args){
        Scanner prm=new Scanner(System.in);
        System.out.println("진행하시겠습니까? (y/n)");
        String yn=prm.nextLine();
        while(yn.equals("y")) {
     
            String address=null;
            
            System.out.println("input");
            address=prm.nextLine();
            String[] arr=address.split(" ");
            
            String opcode,p1,p2,p3;//write a 0111 0001
        
            opcode=null;
            p1=null;
            p2=null;
            p3=null;
            opcode=arr[0];
            p1=arr[1];
            if (arr.length > 2) {
                p2=arr[2];
            }
            else if (arr.length > 3) {
                p3=arr[3];
            }
            
            Stack<String> stack = new Stack<String>();
            String result1=null,result2=null,result3=null;
            if(opcode.equals("Write"))
            {
                //stack p3 값을 넣습니다
            	result1="0011"+" "+p3+"\n";
            	stack.push(p3);
                
                //String[] memory_address=new String[16];
                int i= Integer.parseInt(p2,2);
                //메모리 주소에 스택값 넣습니다.
                memory(p1,stack.pop());
                //memory_address[i]=stack.pop();
                result2="1000"+" "+p2+"\n";
                result3="0101";
                while(!stack.empty()){
                }
                //System.out.println(result1+result2+result3);
            }
            else if(opcode.equals("Add")) {
            	String mem1 = find(p1);
    String mem2 = find(p2);
    if(mem1 == null || mem2 == null) {
        System.out.println("Invalid memory address(es). Please input again.");
        continue;
    }
    DR = mem1;
    stack.push(DR);
    DR = mem2;
    int DR_v= Integer.parseInt(mem1,2);
    int stack_v= Integer.parseInt(mem2,2);
    int sum=DR_v+stack_v;
    String sum_v=Integer.toBinaryString(sum);
    stack.push(sum_v);
    DR=stack.pop();
            }
            else if(opcode.equals("Store")){
            	memory(p1,DR);
            }
            else if(opcode.equals("Print")){
            	find(p1);
            	System.out.println(find(p1));
            }

            System.out.println("more input?? (y/n)");
            yn=prm.nextLine();
            if(yn.equals("y")) {
            	
            	continue;
            }
            else{
            	break;
            }
        }
            
        prm.close();
        
    }
}