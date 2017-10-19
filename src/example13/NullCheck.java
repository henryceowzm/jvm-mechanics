package example13;

import support.Output;
import support.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith({
        "-Xbatch", "-XX:-TieredCompilation", "-XX:+PrintCompilation",
        "-XX:+UnlockDiagnosticVMOptions",
        "-XX:CompileCommand=print,example13/NullCheck::bench"
})
@Output(highlight={"NullCheck::bench", "made not entrant"})

public class NullCheck
{
    private static void bench(List<String> list)
    {
        if (list != null)
        {
            list.contains("foo");
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        List<String> list = new ArrayList<String>();
        list.add("bar");
        for (int i = 0; i < 10000; i++)
        {
            bench(list);
        }
        Thread.sleep(1000);
    }
}