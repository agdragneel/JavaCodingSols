
import java.util.*;
class StackOverflowException extends Exception
{
    StackOverflowException(String msg)
    {
        super(msg);
    }
}

class StackUnderflowException extends Exception
{
    StackUnderflowException(String msg)
    {
        super(msg);
    }
}

class STACK
{
    int stackPointer;
    Object stackData[];
    int size;

    STACK()
    {
        stackPointer=-1;
        size=5;
        stackData=new Object[size];
    }
    STACK(int n)
    {
        size=n;
        stackPointer=-1;
        stackData=new Object[size];
    }

    void push(Object a) throws StackOverflowException
    {
        try
        {
            stackData[++stackPointer]=a;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new StackOverflowException("Stack is full.");
        }
    }

    Object pop() throws StackUnderflowException
    {
        try{
        Object a=stackData[stackPointer--];
        return a;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            throw new StackUnderflowException("Stack is empty.");
        }

    }

    void print()
    {
        for(int i=0;i<=stackPointer;i++)
        {
            System.out.print(stackData[i]+" ");
        }
        System.out.println();
    }
}

class test
{    public static void main(String args[])
    {
        
        
        STACK s1=new STACK(5);
        
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            try
            {
            System.out.println("1.Push\n2.Pop\n3.Display\n4.Exit\n5.Push Defaults");
            int ch=sc.nextInt();

            switch(ch)
            {
                case 1:
                    System.out.println("Enter data to be pushed");
                    Integer dat=Integer.valueOf(sc.nextInt());
                    s1.push(dat);
                break;

                case 2:
                    System.out.println("Popped Data is"+s1.pop());
                break;

                case 3:
                    s1.print();
                break;

                case 4:
                    System.exit(0);
                break;

                case 5:  
                s1.push("Hi");
                s1.push(123);
                s1.push(25.14);
                break;

                default:
                    System.out.println("Invalid Choice.");
            }
            }
            catch(StackOverflowException e)
            {
                System.out.println(e.getMessage());
            }
            catch(StackUnderflowException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}

