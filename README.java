# digits
public static void main(String[] args)
throws Exception
{

    // validate methods:
    for (int i = 0; i < 1000; i++)
        if (method1(i) != method2(i))
            System.out.println(i);
    for (int i = 0; i < 1000; i++)
        if (method1(i) != method3(i))
            System.out.println(i + " " + method1(i) + " " + method3(i));
    for (int i = 333; i < 2000000000; i += 1000)
        if (method1(i) != method3(i))
            System.out.println(i + " " + method1(i) + " " + method3(i));
    for (int i = 0; i < 1000; i++)
        if (method1(i) != method4(i))
            System.out.println(i + " " + method1(i) + " " + method4(i));
    for (int i = 333; i < 2000000000; i += 1000)
        if (method1(i) != method4(i))
            System.out.println(i + " " + method1(i) + " " + method4(i));

    // work-up the JVM - make sure everything will be run in hot-spot mode
    allMethod1();
    allMethod2();
    allMethod3();
    allMethod4();

    // run benchmark
    Chronometer c;

    c = new Chronometer(true);
    allMethod1();
    c.stop();
    long baseline = c.getValue();
    System.out.println(c);

    c = new Chronometer(true);
    allMethod2();
    c.stop();
    System.out.println(c + " = " + StringTools.formatDouble((double)baseline / c.getValue() , "0.00") + " times faster than baseline");

    c = new Chronometer(true);
    allMethod3();
    c.stop();
    System.out.println(c + " = " + StringTools.formatDouble((double)baseline / c.getValue() , "0.00") + " times faster than baseline");

    c = new Chronometer(true);
    allMethod4();
    c.stop();
    System.out.println(c + " = " + StringTools.formatDouble((double)baseline / c.getValue() , "0.00") + " times faster than baseline");
}


private static int method1(int n)
{
    return Integer.toString(n).length();
}
private static int method2(int n)
{
    if (n == 0)
        return 1;
    return (int)(Math.log10(n) + 1);
}
private static int method3(int n)
{
    if (n == 0)
        return 1;
    int l;
    for (l = 0 ; n > 0 ;++l)
        n /= 10;
    return l;
}
private static int method4(int n)
{
    if (n < 100000)
    {
        // 5 or less
        if (n < 100)
        {
            // 1 or 2
            if (n < 10)
                return 1;
            else
                return 2;
        }
        else
        {
            // 3 or 4 or 5
            if (n < 1000)
                return 3;
            else
            {
                // 4 or 5
                if (n < 10000)
                    return 4;
                else
                    return 5;
            }
        }
    }
    else
    {
        // 6 or more
        if (n < 10000000)
        {
            // 6 or 7
            if (n < 1000000)
                return 6;
            else
                return 7;
        }
        else
        {
            // 8 to 10
            if (n < 100000000)
                return 8;
            else
            {
                // 9 or 10
                if (n < 1000000000)
                    return 9;
                else
                    return 10;
            }
        }
    }
}


private static int allMethod1()
{
    int x = 0;
    for (int i = 0; i < 1000; i++)
        x = method1(i);
    for (int i = 1000; i < 100000; i += 10)
        x = method1(i);
    for (int i = 100000; i < 1000000; i += 100)
        x = method1(i);
    for (int i = 1000000; i < 2000000000; i += 200)
        x = method1(i);

    return x;
}
private static int allMethod2()
{
    int x = 0;
    for (int i = 0; i < 1000; i++)
        x = method2(i);
    for (int i = 1000; i < 100000; i += 10)
        x = method2(i);
    for (int i = 100000; i < 1000000; i += 100)
        x = method2(i);
    for (int i = 1000000; i < 2000000000; i += 200)
        x = method2(i);

    return x;
}
private static int allMethod3()
{
    int x = 0;
    for (int i = 0; i < 1000; i++)
        x = method3(i);
    for (int i = 1000; i < 100000; i += 10)
        x = method3(i);
    for (int i = 100000; i < 1000000; i += 100)
        x = method3(i);
    for (int i = 1000000; i < 2000000000; i += 200)
        x = method3(i);

    return x;
}
private static int allMethod4()
{
    int x = 0;
    for (int i = 0; i < 1000; i++)
        x = method4(i);
    for (int i = 1000; i < 100000; i += 10)
        x = method4(i);
    for (int i = 100000; i < 1000000; i += 100)
        x = method4(i);
    for (int i = 1000000; i < 2000000000; i += 200)
        x = method4(i);

    return x;
}
Again, benchmark:
